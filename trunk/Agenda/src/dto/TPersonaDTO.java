package dto;

import java.util.Date;

public class TPersonaDTO {
	private int idTipoPersona;
	private String Tipo;
	private Date Fecha_Baja;
	
	public TPersonaDTO(int idTipoPersona, String Tipo, Date Fecha_Baja)
	{
		this.idTipoPersona = idTipoPersona;
		this.Tipo = Tipo;
		this.Fecha_Baja = Fecha_Baja;
	}
	
	public int getIdTipoPersona() {
		return idTipoPersona;
	}
	public void setIdTipoPersona(int idTipoPersona) {
		this.idTipoPersona = idTipoPersona;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		this.Tipo = tipo;
	}
	public Date getFecha_Baja() {
		return Fecha_Baja;
	}
	public void setFecha_Baja(Date fecha_Baja) {
		this.Fecha_Baja = fecha_Baja;
	}
	
	@Override public String toString()
	{
		return Tipo;
	}
}
