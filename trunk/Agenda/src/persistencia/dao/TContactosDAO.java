package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TPersonaDTO;
import persistencia.conexion.Conexion;

public class TContactosDAO {

	private static final String insert = "INSERT INTO tipo_personas(Tipo) VALUES(?)";
	private static final String readall = "SELECT * FROM tipo_personas WHERE Fecha_Baja IS NULL";
	private static final String delete = "UPDATE tipo_personas SET Fecha_Baja = NOW() WHERE idTipoPersona = ?;";
	private static final String modify = "UPDATE tipo_personas SET Tipo = ? WHERE idTipoPersona = ?;";
	private static final Conexion conexion = Conexion.getConexion();

	public List<TPersonaDTO> readAll() { //Parametrizado como TPersonaDTO
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TPersonaDTO> tipo_personas = new ArrayList<TPersonaDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) { 
				TPersonaDTO nuevoTipo = new TPersonaDTO(
						resultSet.getInt("idTipoPersona"),
						resultSet.getString("Tipo"),
						resultSet.getDate("Fecha_Baja")
						);
				tipo_personas.add(nuevoTipo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return tipo_personas;
	}

	public boolean existe(String Tipo) {
		List<TPersonaDTO> tipos = this.readAll();

		for(TPersonaDTO personas: tipos)
		{
			if (personas.getTipo() == Tipo){
				return true;
			}
		}

		return false;
	}

	public boolean insert(String Tipo) {
		if (!this.existe(Tipo)) {
			PreparedStatement statement;
			try {
				statement = conexion.getSQLConexion().prepareStatement(insert);
				statement.setString(1, Tipo);
				if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo
													// true
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally // Se ejecuta siempre
			{
				conexion.cerrarConexion();
			}
		}
		return false;
	}

	public boolean delete(int idTipoContacto) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, idTipoContacto);
			if (statement.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conexion.cerrarConexion();
		}
		return false;
	}

	public int getIdTipoContacto(String tipo) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		try {
			String query = "SELECT IdTipoPersona "
					+ "FROM tipo_personas "
					+ "WHERE Tipo = (?) "
					+ "AND Fecha_Baja IS NULL";
			
			statement = conexion.getSQLConexion().prepareStatement(query);
			statement.setString(1, tipo);
			
			resultSet = statement.executeQuery();
			int resultado = -1;
			
			while(resultSet.next())
			{
				resultado = resultSet.getInt("IdTipoPersona");
			}
			
			if (resultado != -1)
				return resultado;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally 
		{
			conexion.cerrarConexion();
		}
		return -1; // Fallo la consulta
	}

	public boolean modify(String text, String text2) {
		if (!this.existe(text2))
		{
			int id1 = this.getIdTipoContacto(text);
			
			PreparedStatement statement;
			try {
				statement = conexion.getSQLConexion().prepareStatement(modify);
				statement.setString(1, text2);
				statement.setInt(2, id1);
				if (statement.executeUpdate() > 0) 
					return true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally 
			{
				conexion.cerrarConexion();
			}
		}
		return false;
	}

}
