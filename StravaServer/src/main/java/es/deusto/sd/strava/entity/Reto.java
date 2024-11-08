package es.deusto.sd.strava.entity;

import java.util.Date;

public class Reto {
	
	private int idReto;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private float distancia;
	private float tiempo;
	private Sport deporte;
	
	public Reto(int idReto, String nombre, Date fechaInicio, Date fechaFin, float distancia, float tiempo,
			Sport deporte) {
		super();
		this.idReto = idReto;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.distancia = distancia;
		this.tiempo = tiempo;
		this.deporte = deporte;
	}

	public Reto() {
		super();
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	public float getTiempo() {
		return tiempo;
	}

	public void setTiempo(float tiempo) {
		this.tiempo = tiempo;
	}

	public Sport getDeporte() {
		return deporte;
	}

	public void setDeporte(Sport deporte) {
		this.deporte = deporte;
	}

	@Override
	public String toString() {
		return "Reto [idReto=" + idReto + ", nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + ", distancia=" + distancia + ", tiempo=" + tiempo + ", deporte=" + deporte + "]";
	}
}
