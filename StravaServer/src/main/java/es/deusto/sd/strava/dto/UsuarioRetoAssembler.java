package es.deusto.sd.strava.dto;

import es.deusto.sd.strava.entity.UsuarioReto;

public class UsuarioRetoAssembler {

    public static UsuarioRetoDTO toDTO(UsuarioReto usuarioReto) {
        return new UsuarioRetoDTO(
            UserAssembler.toDTO(usuarioReto.getUsuario()),
            RetoAssembler.toDTO(usuarioReto.getReto()),
            usuarioReto.isCompletado()
        );
    }

    public static UsuarioReto toEntity(UsuarioRetoDTO usuarioRetoDTO) {
        UsuarioReto usuarioReto = new UsuarioReto();
        usuarioReto.setUsuario(UserAssembler.toEntity(usuarioRetoDTO.getUsuario()));
        usuarioReto.setReto(RetoAssembler.toEntity(usuarioRetoDTO.getReto()));
        usuarioReto.setCompletado(usuarioRetoDTO.isCompletado());
        return usuarioReto;
    }
}
