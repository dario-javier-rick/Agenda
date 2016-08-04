package dto;

import java.util.Date;

public class LocalidadDTO {
	private int idLocalidad;
	private String Nombre_Localidad;
	private Date Fecha_Baja;
	
	
	public LocalidadDTO(int idLocalidad, String Nombre_Localidad, Date Fecha_Baja) {
		this.idLocalidad = idLocalidad;
		this.Nombre_Localidad = Nombre_Localidad;
		this.Fecha_Baja = Fecha_Baja;
	}
	

	public int getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public String getNombre_Localidad() {
		return Nombre_Localidad;
	}
	public void setNombre_Localidad(String nombre_Localidad) {
		this.Nombre_Localidad = nombre_Localidad;
	}
	public Date getFecha_Baja() {
		return Fecha_Baja;
	}
	public void setFecha_Baja(Date fecha_Baja) {
		this.Fecha_Baja = fecha_Baja;
	}
	
	@Override public String toString()
	{
		return Nombre_Localidad;
	}
}
