package es.deusto.sd.strava.dto;

import java.util.Date;

public class SesionDTO {

	private static final long serialVersionUID = 1L;
	private String titulo;
	private int distancia;
	private Date fecha_ini;
	private String deporte;
	private int duracion;
	
	public SesionDTO(String titulo, int distancia, Date fecha_ini, String deporte, int duracion) {
		super();
		this.titulo = titulo;
		this.distancia = distancia;
		this.fecha_ini = fecha_ini;
		this.deporte = deporte;
		this.duracion = duracion;
	}
	
	public SesionDTO() {
		super();
		this.titulo = "";
		this.distancia = 0;
		this.fecha_ini = null;
		this.deporte = "";
		this.duracion = 0;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Date getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "SesionDTO [titulo=" + titulo + ", distancia=" + distancia + ", fecha_ini=" + fecha_ini + ", deporte="
				+ deporte + ", duracion=" + duracion + "]";
	}

	
	
	
	
	
}
