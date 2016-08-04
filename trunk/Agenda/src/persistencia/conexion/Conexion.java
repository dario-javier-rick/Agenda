package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion 
{
	public static Conexion instancia;
	private final static String driver = "com.mysql.jdbc.Driver";
	private Connection conexion;
	
	public Conexion()
	{
		try
		{
			Class.forName(driver).newInstance();
			
			//TODO: Datos hardcodeados. Utilizar cfg y encriptador
			
			String port = "3306";//Encrypter.Desencriptar(Config.getValorParametro("mysql_port"));
			String user = "root";//Encrypter.Desencriptar(Config.getValorParametro("mysql_user"));
			String pass = "root";//Encrypter.Desencriptar(Config.getValorParametro("mysql_pass"));
			String dbname = "agenda";//Encrypter.Desencriptar(Config.getValorParametro("mysql_dbname"));
			String host = "localhost";//Encrypter.Desencriptar(Config.getValorParametro("mysql_host"));
			
			conexion = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbname+"?allowMultiQueries=true",user,pass);
			System.out.println("Conexion exitosa");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Conexion fallida");
		}
	}
	
	public static boolean setUserAndPass(String userNEW, String pass)
	{
		Conexion conexion = Conexion.getConexion();
		String user = "";
		try {
			user = Encrypter.Desencriptar(Config.getValorParametro("mysql_user"));
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
		 
		//-----
		String query1 = "SET SQL_SAFE_UPDATES = 0; "+
			"UPDATE mysql.USER SET USER='"+userNEW+"' WHERE USER='"+user+"'; "+
			"UPDATE mysql.proc SET DEFINER = '"+userNEW+"@localhost' WHERE DEFINER = '"+user+"@localhost'; "+
			"FLUSH PRIVILEGES; "+
			"SET PASSWORD FOR '"+userNEW+"'@'localhost' = '"+pass+"';";
		
		boolean ret = false;
		PreparedStatement statement;
		boolean hasMoreResultSets = false;
		try {
			statement = conexion.getSQLConexion().prepareStatement(query1);
			hasMoreResultSets = statement.execute();
			READING_QUERY_RESULTS:
			while (hasMoreResultSets || statement.getUpdateCount() != -1) {
				ret = true;
				System.out.println("Se ejecuto TODO");
				if ( hasMoreResultSets ) {  
		        }
				else {
		            int queryResult = statement.getUpdateCount();  
		            if ( queryResult == -1 ) {
		                break READING_QUERY_RESULTS;  
		            } 
				}
				hasMoreResultSets = statement.getMoreResults();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		} finally 
		{
			conexion.cerrarConexion();
		}
		if (ret){
			Config.setValorParametro("mysql_user", Encrypter.Encriptar(userNEW));
			Config.setValorParametro("mysql_pass", Encrypter.Encriptar(pass));
		}
		return ret;
	}
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return conexion;
	}
	
	public void cerrarConexion()
	{
		instancia = null;
	}
}
