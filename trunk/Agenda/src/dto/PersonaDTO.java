package dto;

import java.util.Date;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private DomicilioDTO domicilio;
	private String Email;
	private Date fecha_Nacimiento;
	private TPersonaDTO tipo_Persona;
	
	
	/*
	public PersonaDTO(int idPersona, String nombre, String telefono, int idDomicilio, String calle, int altura,
			int piso, String depto, int idLocalidad, String nombre_Localidad, String email, Date fecha_Nacimiento,
			int idTipoPersona, String tipoPersona) 
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.idDomicilio = idDomicilio;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.depto = depto;
		this.idLocalidad = idLocalidad;
		this.nombre_Localidad = nombre_Localidad;
		this.Email = email;
		this.fecha_Nacimiento = fecha_Nacimiento;
		this.idTipoPersona = idTipoPersona;
		//this.tipoPersona = tipoPersona;
	}
	*/
	// Llamar a este constructor
	public PersonaDTO(String nombre, String telefono, DomicilioDTO domicilio, String email, Date fecha_Nacimiento, TPersonaDTO tipo_Persona) 
	{
		this.nombre = nombre;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.Email = email;
		this.fecha_Nacimiento = fecha_Nacimiento;
		this.setTipo_Persona(tipo_Persona);
	}
	
	public PersonaDTO(int idPersona, String nombre, String telefono, DomicilioDTO domicilio, String email, Date fecha_Nacimiento, TPersonaDTO tipo_Persona) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.Email = email;
		this.fecha_Nacimiento = fecha_Nacimiento;
		this.setTipo_Persona(tipo_Persona);
	}


	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
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
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getFecha_Nacimiento() {
		return fecha_Nacimiento;
	}
	public void setFecha_Nacimiento(Date fecha_Nacimiento) {
		this.fecha_Nacimiento = fecha_Nacimiento;
	}
	public TPersonaDTO getTipoPersona() {
		return tipo_Persona;
	}

	public DomicilioDTO getDomicilio() {
		return this.domicilio;
	}

	public TPersonaDTO getTipo_Persona() {
		return tipo_Persona;
	}

	private void setTipo_Persona(TPersonaDTO tipo_Persona) {
		this.tipo_Persona = tipo_Persona;
	}

	
}
