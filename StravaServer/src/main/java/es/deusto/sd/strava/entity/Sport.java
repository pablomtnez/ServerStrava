package es.deusto.sd.strava.entity;

public enum Sport {
	CICLISMO, CICLISMO_AND_RUNNING, RUNNING;
	
	public static Sport parse(String text){
        if(CICLISMO.name().equalsIgnoreCase(text)){
            return CICLISMO ;
        }else if (RUNNING.name().equalsIgnoreCase(text)) {
        	return RUNNING;
        }else {
            return CICLISMO_AND_RUNNING;
        }
    }

}
