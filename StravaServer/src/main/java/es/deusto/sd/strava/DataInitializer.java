package es.deusto.sd.strava;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.deusto.sd.strava.entity.Deportes;
import es.deusto.sd.strava.entity.Reto;
import es.deusto.sd.strava.entity.Sesion;
import es.deusto.sd.strava.entity.TipoUsuario;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.services.AuthService;
import es.deusto.sd.strava.services.StravaService;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner initData(StravaService stravaService, AuthService authService) {
        return args -> {

            // Crear usuarios
            User user1 = new User(TipoUsuario.GOOGLE, "user1@gmail.com", "Alice", createDate(1990, 5, 15), 60.5f, 165f, 180f, 70f);
            User user2 = new User(TipoUsuario.META, "user2@gmail.com", "Bob", createDate(1985, 2, 10), 75f, 178f, 190f, 80f);
            User user3 = new User(TipoUsuario.GOOGLE, "user3@gmail.com", "Charlie", createDate(2000, 7, 25), 68f, 172f, 185f, 75f);

            try {
                authService.register(user1);
                logger.info("User registered: {}", user1.getEmail());
            } catch (Exception e) {
                logger.error("Failed to register user {}: {}", user1.getEmail(), e.getMessage());
            }

            try {
                authService.register(user2);
                logger.info("User registered: {}", user2.getEmail());
            } catch (Exception e) {
                logger.error("Failed to register user {}: {}", user2.getEmail(), e.getMessage());
            }

            try {
                authService.register(user3);
                logger.info("User registered: {}", user3.getEmail());
            } catch (Exception e) {
                logger.error("Failed to register user {}: {}", user3.getEmail(), e.getMessage());
            }

            // Crear sesiones de entrenamiento
            Sesion sesion1 = new Sesion("Morning Run", 5.0f, createDate(2024, 10, 10), createTime(2024, 10, 10, 6, 30), 30.0f, Deportes.RUNNING);
            Sesion sesion2 = new Sesion("Evening Cycling", 20.0f, createDate(2024, 10, 9), createTime(2024, 10, 9, 18, 0), 60.0f, Deportes.CICLISMO);
            Sesion sesion3 = new Sesion("Weekend Hike", 10.0f, createDate(2024, 10, 8), createTime(2024, 10, 8, 8, 0), 120.0f, Deportes.CICLISMO_AND_RUNNING);
            Sesion sesion4 = new Sesion("Night Run", 7.0f, createDate(2024, 10, 7), createTime(2024, 10, 7, 20, 30), 45.0f, Deportes.RUNNING);
            Sesion sesion5 = new Sesion("Hill Cycling", 25.0f, createDate(2024, 10, 6), createTime(2024, 10, 6, 9, 0), 90.0f, Deportes.CICLISMO);

            logger.info("Sessions created: {}, {}, {}, {}, {}", sesion1.getTitulo(), sesion2.getTitulo(), sesion3.getTitulo(), sesion4.getTitulo(), sesion5.getTitulo());

            // Agregar sesiones al servicio
            try {
                stravaService.crearSesion(sesion1.getTitulo(), sesion1.getDistancia(), sesion1.getFechaInicio(), sesion1.getHoraInicio(), sesion1.getDuracion(), sesion1.getDeporte());
                logger.info("Session added: {}", sesion1.getTitulo());
            } catch (Exception e) {
                logger.error("Failed to add session {}: {}", sesion1.getTitulo(), e.getMessage());
            }

            try {
                stravaService.crearSesion(sesion2.getTitulo(), sesion2.getDistancia(), sesion2.getFechaInicio(), sesion2.getHoraInicio(), sesion2.getDuracion(), sesion2.getDeporte());
                logger.info("Session added: {}", sesion2.getTitulo());
            } catch (Exception e) {
                logger.error("Failed to add session {}: {}", sesion2.getTitulo(), e.getMessage());
            }
            
            try {
                stravaService.crearSesion(sesion3.getTitulo(), sesion3.getDistancia(), sesion3.getFechaInicio(), sesion3.getHoraInicio(), sesion3.getDuracion(), sesion3.getDeporte());
                logger.info("Session added: {}", sesion3.getTitulo());
            } catch (Exception e) {
                logger.error("Failed to add session {}: {}", sesion3.getTitulo(), e.getMessage());
            }
            
            try {
                stravaService.crearSesion(sesion4.getTitulo(), sesion4.getDistancia(), sesion4.getFechaInicio(), sesion4.getHoraInicio(), sesion4.getDuracion(), sesion4.getDeporte());
                logger.info("Session added: {}", sesion4.getTitulo());
            } catch (Exception e) {
                logger.error("Failed to add session {}: {}", sesion4.getTitulo(), e.getMessage());
            }
            
            try {
                stravaService.crearSesion(sesion5.getTitulo(), sesion5.getDistancia(), sesion5.getFechaInicio(), sesion5.getHoraInicio(), sesion5.getDuracion(), sesion5.getDeporte());
                logger.info("Session added: {}", sesion5.getTitulo());
            } catch (Exception e) {
                logger.error("Failed to add session {}: {}", sesion5.getTitulo(), e.getMessage());
            }

            // Crear retos
            Reto reto1 = new Reto("Run 50km in November", createDate(2024, 10, 1), createDate(2025, 10, 30), 50.0f, 0.0f, Deportes.RUNNING);
            Reto reto2 = new Reto("Cycle 100km this month", createDate(2024, 10, 1), createDate(2024, 10, 30), 100.0f, 0.0f, Deportes.CICLISMO);
            Reto reto3 = new Reto("Triathlon Prep", createDate(2024, 10, 5), createDate(2025, 10, 20), 30.0f, 300.0f, Deportes.CICLISMO_AND_RUNNING);
            Reto reto4 = new Reto("Morning Challenge", createDate(2024, 9, 1), createDate(2024, 9, 30), 20.0f, 60.0f, Deportes.RUNNING);
            Reto reto5 = new Reto("Weekend Warrior", createDate(2024, 10, 7), createDate(2025, 10, 14), 15.0f, 120.0f, Deportes.CICLISMO);

            logger.info("Challenges created: {}, {}, {}, {}, {}", reto1.getNombre(), reto2.getNombre(), reto3.getNombre(), reto4.getNombre(), reto5.getNombre());

            // Agregar retos al servicio
            try {
                stravaService.crearReto(reto1.getNombre(), reto1.getFechaInicio(), reto1.getFechaFin(), reto1.getDistancia(), reto1.getTiempo(), reto1.getDeporte());
                logger.info("Challenge added: {}", reto1.getNombre());
            } catch (Exception e) {
                logger.error("Failed to add challenge {}: {}", reto1.getNombre(), e.getMessage());
            }

            try {
                stravaService.crearReto(reto2.getNombre(), reto2.getFechaInicio(), reto2.getFechaFin(), reto2.getDistancia(), reto2.getTiempo(), reto2.getDeporte());
                logger.info("Challenge added: {}", reto2.getNombre());
            } catch (Exception e) {
                logger.error("Failed to add challenge {}: {}", reto2.getNombre(), e.getMessage());
            }
            
            try {
                stravaService.crearReto(reto3.getNombre(), reto3.getFechaInicio(), reto3.getFechaFin(), reto3.getDistancia(), reto3.getTiempo(), reto3.getDeporte());
                logger.info("Challenge added: {}", reto3.getNombre());
            } catch (Exception e) {
                logger.error("Failed to add challenge {}: {}", reto3.getNombre(), e.getMessage());
            }
            
            try {
                stravaService.crearReto(reto4.getNombre(), reto4.getFechaInicio(), reto4.getFechaFin(), reto4.getDistancia(), reto4.getTiempo(), reto4.getDeporte());
                logger.info("Challenge added: {}", reto4.getNombre());
            } catch (Exception e) {
                logger.error("Failed to add challenge {}: {}", reto4.getNombre(), e.getMessage());
            }
            
            try {
                stravaService.crearReto(reto5.getNombre(), reto5.getFechaInicio(), reto5.getFechaFin(), reto5.getDistancia(), reto5.getTiempo(), reto5.getDeporte());
                logger.info("Challenge added: {}", reto5.getNombre());
            } catch (Exception e) {
                logger.error("Failed to add challenge {}: {}", reto5.getNombre(), e.getMessage());
            }

            // Aceptar retos
            try {
                stravaService.aceptarReto(reto1.getNombre(), user1);
                logger.info("Challenge accepted by {}: {}", user1.getEmail(), reto1.getNombre());
            } catch (Exception e) {
                logger.error("Failed to accept challenge {}: {}", reto1.getNombre(), e.getMessage());
            }
        };
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    private Date createTime(int year, int month, int day, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute);
        return calendar.getTime();
    }
}
