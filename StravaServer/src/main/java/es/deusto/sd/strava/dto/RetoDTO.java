package es.deusto.sd.strava.dto;

import java.util.Date;

public class RetoDTO {
	
	private static final long serialVersionUD = 1L;
	private int idReto;
	private String nombre;
	private String deporte;
	private Date fecha_ini;
	private Date fecha_fin;
	private int distancia;
	public RetoDTO(int idReto, String nombre, String deporte, Date fecha_ini, Date fecha_fin, int distancia) {
		super();
		this.idReto = idReto;
		this.nombre = nombre;
		this.deporte = deporte;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
		this.distancia = distancia;
	}
	
	public RetoDTO() {
		super();
		this.idReto = 0;
		this.nombre = "";
		this.deporte = "";
		this.fecha_ini = null;
		this.fecha_fin = null;
		this.distancia = 0;
	}

	public int getIdReto() {
		return idReto;
	}

	public void setIdReto(int idReto) {
		this.idReto = idReto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public Date getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	

	@Override
	public String toString() {
		return "RetoDTO [idReto=" + idReto + ", nombre=" + nombre + ", deporte=" + deporte + ", fecha_ini=" + fecha_ini
				+ ", fecha_fin=" + fecha_fin + ", distancia=" + distancia + "]";
	}
	
	
}
