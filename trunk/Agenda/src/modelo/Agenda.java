package modelo;

import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.conexion.Config;
import persistencia.conexion.Encrypter;
import persistencia.dao.LocalidadesDAO;
import persistencia.dao.PersonaDAO;
import persistencia.dao.TContactosDAO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TPersonaDTO;


public class Agenda 
{
	private PersonaDAO persona;	
	// Entrega 15/03 - Dario Rick INI
	private LocalidadesDAO localidades; 
	private TContactosDAO tipos_contactos;
	// Entrega 15/03 - Dario Rick FIN
	
	public Agenda()
	{
		persona = new PersonaDAO();
		// Entrega 15/03 - Dario Rick INI
		localidades = new LocalidadesDAO();
		tipos_contactos = new TContactosDAO();
		// Entrega 15/03 - Dario Rick FIN
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return persona.readAll();		
	}
	
	// Entrega 15/03 - Dario Rick INI
	public List<LocalidadDTO> obtenerLocalidades()
	{
		return localidades.readAll();		
	}
	
	public List<TPersonaDTO> obtenerTiposContactos() // Parametrizado como TPersonaDTO
	{
		return tipos_contactos.readAll();
	}
	
	public void agregarLocalidad(String localidad) {
		localidades.insert(localidad);
		
	}
	
	public void agregarLocalidad(LocalidadDTO nuevaLocalidad)
	{
		localidades.insert(nuevaLocalidad);
	}
	// Entrega 15/03 - Dario Rick FIN

	public void agregarTContacto(String tipo) {
		tipos_contactos.insert(tipo);
	}

	public void eliminarLocalidad(LocalidadDTO localidadDTO) {
		// Verificar la posibilidad de tener en memoria una entidad localidad
		//int idLocalidad = localidades.getIdLocalidad(localidadDTO);
		localidades.delete(localidadDTO.getIdLocalidad());
		//localidadDTO.setFecha_Baja(ZonedDateTime.now());
	}
	
	public void eliminarTContacto (TPersonaDTO tPersonaDTO) {
		// Verificar la posibilidad de tener en memoria una entidad tipo_contacto
		//int idTipoContacto = tipos_contactos.getIdTipoContacto(tPersonaDTO);
		tipos_contactos.delete(tPersonaDTO.getIdTipoPersona());
	}

	public boolean editarLocalidad(String text, String text2) {
		return localidades.modify(text,text2);
		
	}

	public boolean editarTContacto(String text, String text2) {
		return tipos_contactos.modify(text,text2);
		
	}

	public boolean editarPersona(PersonaDTO nuevaPersona) {
		System.out.println("agenda.modificarPersona");
		return persona.modify(nuevaPersona);
		
	}
	
	public boolean sistemaIniciado(){
		String inicio = "iniciado";
		try {
			inicio = Encrypter.Desencriptar(Config.getValorParametro("inicio"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (inicio.equals("")){
			return false;
		}
		return true;
	}

	public void escribirInicio() {
		Config.setValorParametro("inicio", Encrypter.Encriptar("iniciado"));
	}

	public void setUserAndPass(String userNEW, String pass) {
		Conexion.setUserAndPass(userNEW, pass);
		
	}
}
