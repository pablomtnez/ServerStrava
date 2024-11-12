package es.deusto.sd.strava.entity;

public enum TipoUsuario {
	GOOGLE, FACEBOOK;

    public static TipoUsuario parse(String text) {
        if (GOOGLE.name().equalsIgnoreCase(text)) {
            return GOOGLE;
        } else if (FACEBOOK.name().equalsIgnoreCase(text)) {
            return FACEBOOK;
        } else {
            return null;
        }
    }

}
