package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.mysql.jdbc.CallableStatement;
//import com.mysql.jdbc.Statement;




import persistencia.conexion.Conexion;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TPersonaDTO;

public class PersonaDAO {
	/*private static final String insert = "INSERT INTO personas"
			+ "(idPersona, Nombre, Telefono, idDomicilio, Email, Fecha_Nacimiento, idTipoPersona) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?)";*/
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final Conexion conexion = Conexion.getConexion();

	public boolean insert(PersonaDTO persona) {	
		try {
			
			System.out.println("idtipopersona: " + persona.getTipo_Persona().getIdTipoPersona());
			
			int idDomicilio = this.insertarDomicilio(persona.getDomicilio());
			persona.getDomicilio().setIdDomicilio(idDomicilio);
			
			String query = "{ call insertarPersona(?,?,?,?,?,?,?) }";
			java.sql.CallableStatement cs = conexion.getSQLConexion().prepareCall(query);
			
			cs.setString(1, persona.getNombre());
			cs.setString(2, persona.getTelefono());
			cs.setInt(3,persona.getDomicilio().getIdDomicilio());
			cs.setString(4, persona.getEmail());
			cs.setDate(5, new java.sql.Date(persona
					.getFecha_Nacimiento().getTime()));
			cs.setInt(6, persona.getTipo_Persona().getIdTipoPersona());
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			
			cs.executeUpdate();
			
			int idPersona = cs.getInt("retIdPersona");		
			persona.setIdPersona(idPersona);
			
			System.out.println("Inserte la persona!");
			System.out.println("idPersona: " + idPersona);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	private int insertarDomicilio(DomicilioDTO domicilio) {
		try
		{
			String query = "{ call insertarDomicilio(?,?,?,?,?,?) }";
			java.sql.CallableStatement cs = conexion.getSQLConexion().prepareCall(query);
			
			cs.setString(1, domicilio.getCalle());
			cs.setInt(2, domicilio.getAltura());
			cs.setInt(3, domicilio.getPiso());
			cs.setString(4, domicilio.getDepto());		
			cs.setInt(5, domicilio.getLocalidad().getIdLocalidad());
			cs.registerOutParameter(6, java.sql.Types.INTEGER);
			
			cs.executeUpdate();
			
			int idDomicilio = cs.getInt("retIdDomicilio");		
			domicilio.setIdDomicilio(idDomicilio);
			
			System.out.println("Inserté el domicilio!" );
			System.out.println("idDomicilio: " + idDomicilio);
			
			return idDomicilio;
			
		}
		 catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return -1; // Fallo
	}

	public int getIdLocalidad(String localidad) {
		String query = "SELECT idLocalidad " + "FROM localidades "
				+ "WHERE Nombre_Localidad = (?)";

		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		try {

			statement = conexion.getSQLConexion().prepareStatement(query);
			statement.setString(1, localidad);

			System.out.println(statement.toString());

			resultSet = statement.executeQuery();
			int resultado = -1;

			while (resultSet.next()) {
				resultado = resultSet.getInt("IdLocalidad");
			}

			if (resultado != -1)
				return resultado;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return -1; // Fallo la consulta

	}
	
	public int getIdDomcilio(String calle, int altura, int piso, String depto,
			int idLocalidad) {
		String query = "SELECT idDomicilio "
				+ "FROM domicilios "
				+ "WHERE Calle = ? AND Altura = ? AND Piso = ? AND Depto = ? AND idLocalidad = ?";

		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		try {

			statement = conexion.getSQLConexion().prepareStatement(query);
			statement.setString(1, calle);
			statement.setInt(2, altura);
			statement.setInt(3, piso);
			statement.setString(4, depto);
			statement.setInt(5, idLocalidad);

			System.out.println(statement.toString());

			resultSet = statement.executeQuery();
			int resultado = -1;

			while (resultSet.next()) {
				resultado = resultSet.getInt("IdDomicilio");
			}

			if (resultado != -1)
				return resultado;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return -1; // Fallo la consulta

	}

	public boolean delete(PersonaDTO persona_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1,
					Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecutï¿½ devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	/*
	 * // Se comenta método ya que se utilizará la variante hecha mediante un SP
	 * public List<PersonaDTO> readAll() { PreparedStatement statement;
	 * ResultSet resultSet; //Guarda el resultado de la query
	 * ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>(); try {
	 * statement = conexion.getSQLConexion().prepareStatement(readall);
	 * resultSet = statement.executeQuery();
	 * 
	 * while(resultSet.next()) { personas.add(new
	 * PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"),
	 * resultSet.getString("Telefono"))); } } catch (SQLException e) {
	 * e.printStackTrace(); } finally //Se ejecuta siempre {
	 * conexion.cerrarConexion(); } return personas; }
	 */

	public List<PersonaDTO> readAll() {
		// método readAll usando Stored Procedure

		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try {
			String query = "{ call getPersonas() }";
			statement = conexion.getSQLConexion().prepareStatement(query);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				
				LocalidadDTO localidad = new LocalidadDTO(
						resultSet.getInt("idLocalidad"), 
						resultSet.getString("Nombre_Localidad"),
						null
						);
				
				DomicilioDTO domicilio = new DomicilioDTO(
						resultSet.getInt("idDomicilio"), 
						resultSet.getString("Calle"),
						resultSet.getInt("Altura"), 
						resultSet.getInt("Piso"),
						resultSet.getString("Depto"), 
						localidad
						);
				
				TPersonaDTO tipo_persona = new TPersonaDTO(
						resultSet.getInt("idTipoPersona"), 
						resultSet.getString("Tipo"),
						null
						);
				
				
				personas.add(
						new PersonaDTO
						(
						resultSet.getInt("idPersona"),
						resultSet.getString("Nombre"), 
						resultSet.getString("Telefono"),
						domicilio,
						resultSet.getString("Email"), 
						resultSet.getDate("Fecha_Nacimiento"), 
						tipo_persona
						)
					);
			}
			System.out.println(personas.size());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return personas;
	}

	public boolean modify(PersonaDTO persona) {
		int idLocalidad = persona.getDomicilio().getLocalidad().getIdLocalidad();
		int idDomicilio = persona.getDomicilio().getIdDomicilio();

		System.out.println("idlocalidad: " + idLocalidad);
		System.out.println("idDomicilio: " + idDomicilio);
		System.out.println("IdPersona: " + persona.getIdPersona());
		
		String query1 = "UPDATE domicilios SET Calle = ?, Altura = ?, Piso = ?,"
				+ " Depto = ?, idLocalidad = ? WHERE idDomicilio = ?;";
		String query2 = "UPDATE personas SET Nombre = ?, Telefono = ?, idDomicilio = ?, Email = ?,"
				+ " Fecha_Nacimiento = ?, idTipoPersona = ? WHERE idPersona = ?;";
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(query1);
			statement.setString(1, persona.getDomicilio().getCalle());
			statement.setInt(2, persona.getDomicilio().getAltura());
			statement.setInt(3, persona.getDomicilio().getPiso());
			statement.setString(4, persona.getDomicilio().getDepto());
			statement.setInt(5, idLocalidad);
			statement.setInt(6, idDomicilio);
			statement.executeUpdate() ;
			//if (statement.executeUpdate() > 0)
			//	return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Entre en finnaly");
			conexion.cerrarConexion();
		}

		System.out.println("idtipopersona: " + persona.getTipo_Persona().getIdTipoPersona());

		try {
			statement = conexion.getSQLConexion().prepareStatement(query2);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setInt(3, idDomicilio);
			statement.setString(4, persona.getEmail());
			statement.setDate(5, new java.sql.Date(persona
					.getFecha_Nacimiento().getTime()));
			statement.setInt(6, persona.getTipo_Persona().getIdTipoPersona());
			statement.setInt(7, persona.getIdPersona());
			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Entre en finnaly");
			conexion.cerrarConexion();
		}

		return false;
	}
}
