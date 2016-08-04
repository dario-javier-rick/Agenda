package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LocalidadDTO;
import persistencia.conexion.Conexion;

public class LocalidadesDAO {

	private static final String insert = "INSERT INTO localidades(Nombre_Localidad) VALUES(?)";
	private static final String readall = "SELECT * FROM localidades WHERE Fecha_Baja IS NULL";
	private static final String delete = "UPDATE localidades SET Fecha_Baja = NOW() WHERE idLocalidad = ?;";
	private static final String modify = "UPDATE localidades SET Nombre_Localidad = ? WHERE idLocalidad = ?;";
	private static final Conexion conexion = Conexion.getConexion();

	public List<LocalidadDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				LocalidadDTO nuevaLocalidad = new LocalidadDTO(
						resultSet.getInt("idLocalidad"),
						resultSet.getString("Nombre_Localidad"),
						resultSet.getDate("Fecha_Baja")
						);
				localidades.add(nuevaLocalidad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally // Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return localidades;
	}

	/*
	 * public int ObtenerIdLocalidad (String nombre){ PreparedStatement
	 * statement; ResultSet resultSet; //Guarda el resultado de la query int
	 * id=-1; try { statement =
	 * conexion.getSQLConexion().prepareStatement(obtenerId);
	 * statement.setString(1, nombre); resultSet = statement.executeQuery(); id
	 * = resultSet.getInt("idLocalidad"); } catch (SQLException e) {
	 * e.printStackTrace(); } finally //Se ejecuta siempre {
	 * conexion.cerrarConexion(); } return id;
	 * 
	 * /* public int ObtenerIdLocalidad (String nombre){ PreparedStatement
	 * statement; ResultSet resultSet; //Guarda el resultado de la query int
	 * id=-1; try { statement =
	 * conexion.getSQLConexion().prepareStatement(obtenerId);
	 * statement.setString(1, nombre); resultSet = statement.executeQuery(); id
	 * = resultSet.getInt("idLocalidad"); } catch (SQLException e) {
	 * e.printStackTrace(); } finally //Se ejecuta siempre {
	 * conexion.cerrarConexion(); } return id; }
	 */

	public boolean existe(String localidad) {
		// Este método se puede hacer mas performante filtrando en la base
		List<LocalidadDTO> localidades = this.readAll();

		for(LocalidadDTO localidad2 : localidades)
		{
			if (localidad2.getNombre_Localidad() == localidad){
				return true;
			}
		}
		return false;
	}

	public boolean insert(String localidad) {
		if (!this.existe(localidad)) {
			PreparedStatement statement;
			try {
				statement = conexion.getSQLConexion().prepareStatement(insert);
				statement.setString(1, localidad);
				if (statement.executeUpdate() > 0) // Si se ejecutï¿½ devuelvo
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

	public boolean delete(int idLocalidad) {
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, idLocalidad);
			if (statement.executeUpdate() > 0) 
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	

		

	public int getIdLocalidad(String localidad) {
		String query = "SELECT idLocalidad "
				+ "FROM localidades "
				+ "WHERE Nombre_Localidad = (?) "
				+ "AND Fecha_Baja IS NULL";
		
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		try {
			
			statement = conexion.getSQLConexion().prepareStatement(query);
			statement.setString(1, localidad);
			
			System.out.println(statement.toString());
			
			resultSet = statement.executeQuery();
			int resultado = -1;
			
			while(resultSet.next())
			{
				resultado = resultSet.getInt("IdLocalidad");
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

	public void insert(LocalidadDTO nuevaLocalidad) {
		// TODO Auto-generated method stub
		
	}

	public boolean modify(String text, String text2) {
		if (!this.existe(text2))
		{
			int id1 = this.getIdLocalidad(text);
			
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