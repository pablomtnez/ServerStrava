package es.deusto.sd.strava.dto;

import es.deusto.sd.strava.entity.Sesion;
import es.deusto.sd.strava.entity.Deportes;

public class SesionAssembler {

    public static SesionDTO toDTO(Sesion sesion) {
        Deportes deporte;
        if (sesion.getDeporte() == Deportes.CICLISMO) {
            deporte = Deportes.CICLISMO;
        } else if (sesion.getDeporte() == Deportes.RUNNING) {
            deporte = Deportes.RUNNING;
        } else {
            deporte = Deportes.CICLISMO_AND_RUNNING;
        }

        return new SesionDTO(
            sesion.getTitulo(),
            sesion.getDistancia(),
            sesion.getFechaInicio(),
            sesion.getHoraInicio(),
            sesion.getDuracion(),
            deporte
        );
    }

    public static Sesion toEntity(SesionDTO sesionDTO) {
        Deportes deporte;
        if (sesionDTO.getDeporte() == Deportes.CICLISMO) {
            deporte = Deportes.CICLISMO;
        } else if (sesionDTO.getDeporte() == Deportes.RUNNING) {
            deporte = Deportes.RUNNING;
        } else {
            deporte = Deportes.CICLISMO_AND_RUNNING;
        }

        return new Sesion(
            sesionDTO.getTitulo(),
            sesionDTO.getDistancia(),
            sesionDTO.getFechaInicio(),
            sesionDTO.getHoraInicio(),
            sesionDTO.getDuracion(),
            deporte
        );
    }
}
