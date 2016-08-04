package dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReporteDTO implements Comparable<ReporteDTO>{
	
	private String nombre;
	private String telefono;
	private String calle;
	private String nombre_Localidad;
	private String Email;
	private String fecha_Nacimiento;
	private String tipoPersona;
	private String signoZodiacal;
	
	public ReporteDTO(String nombre, String telefono, String calle, String nombre_Localidad, String email,
			String fecha_Nacimiento, String tipoPersona, String signoZodiacal) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.calle = calle;
		this.nombre_Localidad = nombre_Localidad;
		Email = email;
		this.fecha_Nacimiento = fecha_Nacimiento;
		this.tipoPersona = tipoPersona;
		this.signoZodiacal = signoZodiacal;
	}
	
	public String getSignoZodiacal() {
		return signoZodiacal;
	}

	public void setSignoZodiacal(String signoZodiacal) {
		this.signoZodiacal = signoZodiacal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNombre_Localidad() {
		return nombre_Localidad;
	}

	public void setNombre_Localidad(String nombre_Localidad) {
		this.nombre_Localidad = nombre_Localidad;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getFecha_Nacimiento() {
		return fecha_Nacimiento;
	}

	public void setFecha_Nacimiento(String fecha_Nacimiento) {
		this.fecha_Nacimiento = fecha_Nacimiento;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	@Override
	public int compareTo(ReporteDTO reporte) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		int ret = this.getSignoZodiacal().compareTo(reporte.getSignoZodiacal());
		try {

			Date date1 = formatter.parse(reporte.getFecha_Nacimiento());
			Date date2 = formatter.parse(this.getFecha_Nacimiento());
			if (ret != 0){
				return ret;
			}
			ret = date1.compareTo(date2);
			if(ret != 0){
				return ret;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}
