/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */

package es.deusto.sd.strava.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.deusto.sd.strava.entity.Sesion;
import es.deusto.sd.strava.entity.Deportes;
import es.deusto.sd.strava.entity.Reto;
import es.deusto.sd.strava.entity.UsuarioReto;
import es.deusto.sd.strava.entity.User;

@Service
public class StravaService {

    // Simulando repositorios en memoria para sesiones, retos y usuarios-retos
    private static List<Sesion> sesiones = new ArrayList<>();
    private static List<Reto> retos = new ArrayList<>();
    private static List<UsuarioReto> usuariosRetos = new ArrayList<>();

    // --- Sesiones ---

    // Crear una sesión de entrenamiento manualmente
    public void crearSesion(String titulo, float distancia, Date fechaInicio, Date horaInicio, float duracion, Deportes deporte) {
        if (titulo == null || titulo.isEmpty() || distancia <= 0 || fechaInicio == null || horaInicio == null || duracion <= 0 || deporte == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios y deben tener valores válidos.");
        }

        Sesion sesion = new Sesion(titulo, distancia, fechaInicio, horaInicio, duracion, deporte);
        sesiones.add(sesion);
    }

    // Consultar las últimas 5 sesiones de entrenamiento realizadas
    public List<Sesion> consultarUltimasSesiones() {
        return sesiones.stream()
                .sorted((s1, s2) -> s2.getFechaInicio().compareTo(s1.getFechaInicio()))
                .limit(5)
                .collect(Collectors.toList());
    }

    // Consultar todas las sesiones de entrenamiento realizadas entre dos fechas
    public List<Sesion> consultarSesionesPorFechas(Date fechaInicio, Date fechaFin) {
        if (fechaInicio == null || fechaFin == null || fechaInicio.after(fechaFin)) {
            throw new IllegalArgumentException("Las fechas deben ser válidas y fechaInicio no puede ser posterior a fechaFin.");
        }

        return sesiones.stream()
                .filter(s -> !s.getFechaInicio().before(fechaInicio) && !s.getFechaInicio().after(fechaFin))
                .collect(Collectors.toList());
    }

    // --- Retos ---

    // Crear un nuevo reto
    public void crearReto(String nombre, Date fechaInicio, Date fechaFin, float distancia, float tiempo, Deportes deporte) {
        if (nombre == null || nombre.isEmpty() || fechaInicio == null || fechaFin == null || fechaInicio.after(fechaFin) || (distancia <= 0 && tiempo <= 0) || deporte == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios y deben tener valores válidos.");
        }

        Reto reto = new Reto(nombre, fechaInicio, fechaFin, distancia, tiempo, deporte);
        retos.add(reto);
    }

    // Consultar los retos activos (aquellos que no han finalizado)
    public List<Reto> consultarRetosActivos() {
        Date hoy = new Date();
        return retos.stream()
                .filter(r -> !r.getFechaFin().before(hoy))
                .sorted((r1, r2) -> r2.getFechaInicio().compareTo(r1.getFechaInicio()))
                .limit(5)
                .collect(Collectors.toList());
    }

    // Consultar retos filtrados por fecha y deporte
    public List<Reto> consultarRetosPorFiltro(Date fechaInicio, Date fechaFin, Deportes deporte) {
        return retos.stream()
                .filter(r -> (fechaInicio == null || !r.getFechaInicio().before(fechaInicio)) &&
                             (fechaFin == null || !r.getFechaFin().after(fechaFin)) &&
                             (deporte == null || r.getDeporte() == deporte))
                .collect(Collectors.toList());
    }

    // Aceptar un reto
    public void aceptarReto(String nombreReto, User usuario) {
        Reto reto = retos.stream()
                .filter(r -> r.getNombre().equals(nombreReto))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));

        UsuarioReto usuarioReto = new UsuarioReto(usuario, reto);
        usuariosRetos.add(usuarioReto);

        System.out.println("El usuario " + usuario.getNombre() + " ha aceptado el reto: " + reto.getNombre());
    }

    // Consultar los retos aceptados por un usuario
    public List<Reto> consultarRetosAceptados(User usuario) {
        return usuariosRetos.stream()
                .filter(ur -> ur.getUsuario().equals(usuario))
                .map(UsuarioReto::getReto)
                .collect(Collectors.toList());
    }

    // Consultar el progreso de un reto aceptado
    public float consultarProgresoReto(String nombreReto, List<Sesion> sesionesUsuario) {
        Reto reto = retos.stream()
                .filter(r -> r.getNombre().equals(nombreReto))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));

        float progresoDistancia = 0;
        float progresoTiempo = 0;

        for (Sesion sesion : sesionesUsuario) {
            if (sesion.getDeporte() == reto.getDeporte() &&
                !sesion.getFechaInicio().before(reto.getFechaInicio()) &&
                !sesion.getFechaInicio().after(reto.getFechaFin())) {
                progresoDistancia += sesion.getDistancia();
                progresoTiempo += sesion.getDuracion();
            }
        }

        float porcentajeDistancia = reto.getDistancia() > 0 ? (progresoDistancia / reto.getDistancia()) * 100 : 0;
        float porcentajeTiempo = reto.getTiempo() > 0 ? (progresoTiempo / reto.getTiempo()) * 100 : 0;

        return Math.max(porcentajeDistancia, porcentajeTiempo);
    }
}