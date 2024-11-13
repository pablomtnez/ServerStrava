package es.deusto.sd.strava.dto;

import es.deusto.sd.strava.entity.Reto;
import es.deusto.sd.strava.entity.Deportes;

public class RetoAssembler {

    public static RetoDTO toDTO(Reto reto) {
        Deportes deporte;
        if (reto.getDeporte() == Deportes.CICLISMO) {
            deporte = Deportes.CICLISMO;
        } else if (reto.getDeporte() == Deportes.RUNNING) {
            deporte = Deportes.RUNNING;
        } else {
            deporte = Deportes.CICLISMO_AND_RUNNING;
        }

        return new RetoDTO(
            reto.getNombre(),
            reto.getFechaInicio(),
            reto.getFechaFin(),
            reto.getDistancia(),
            reto.getTiempo(),
            deporte
        );
    }

    public static Reto toEntity(RetoDTO retoDTO) {
        Deportes deporte;
        if (retoDTO.getDeporte() == Deportes.CICLISMO) {
            deporte = Deportes.CICLISMO;
        } else if (retoDTO.getDeporte() == Deportes.RUNNING) {
            deporte = Deportes.RUNNING;
        } else {
            deporte = Deportes.CICLISMO_AND_RUNNING;
        }

        return new Reto(
            retoDTO.getNombre(),
            retoDTO.getFechaInicio(),
            retoDTO.getFechaFin(),
            retoDTO.getDistancia(),
            retoDTO.getTiempo(),
            deporte
        );
    }
}
