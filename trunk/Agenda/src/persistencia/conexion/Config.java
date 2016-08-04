package persistencia.conexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Config {
	
	public static String getValorParametro(String key) {
		Properties parametros = new Properties();
		String ret = "";
		InputStream is = null;
		try
		{
	    	//is = Config.class.getResourceAsStream("/cfg/cfg.ini");
			is= new FileInputStream("./cfg.ini");
			parametros.load(is);
			ret = parametros.getProperty(key);
		}catch(FileNotFoundException ex){
			System.out.println("No se pudo leer cfg.ini");
			try
			{
				parametros.load(new FileInputStream("cfg//cfg.ini"));
				ret = parametros.getProperty(key);
			}catch(FileNotFoundException ex2){
				ex2.printStackTrace();
				System.out.println("No se pudo leer cfg.ini");
			}catch(IOException ex2){
				ex2.printStackTrace();
				System.out.println("Error al leer cfg.ini");
			}
		}catch(IOException ex){
			ex.printStackTrace();
			System.out.println("Error al leer cfg.ini");
		}finally
    	{
    		try 
    		{
    			if (is!=null){
    				is.close();
    			}
    		}
    		catch(Exception exp)
    		{
    			exp.printStackTrace();
    		}
    	}
		return ret;
	}
	
	public static void setValorParametro(String key, String value) {
		Properties parametros = new Properties();
		InputStream is = null;
		try
		{
			//JOptionPane.showInputDialog("asd");
	    	//is = Config.class.getResourceAsStream("/cfg/cfg.ini");
			is= new FileInputStream("./cfg.ini");
			//parametros.load(new FileInputStream("/cfg/cfg.ini"));
			parametros.load(is);
			parametros.put(key, value);
            parametros.store( new FileOutputStream("./cfg.ini"), "Modify");
			
		}catch(FileNotFoundException ex){
			//ex.printStackTrace();
			System.out.println("No se pudo leer cfg.ini");
			try
			{
				parametros.load(new FileInputStream("cfg//cfg.ini"));
				parametros.put(key, value);
	            parametros.store( new FileOutputStream("cfg//cfg.ini"), "Modify");
			}catch(FileNotFoundException ex2){
				ex2.printStackTrace();
				System.out.println("No se pudo leer cfg.ini");
			}catch(IOException ex2){
				ex2.printStackTrace();
				System.out.println("Error al leer cfg.ini");
			}
		}catch(IOException ex){
			ex.printStackTrace();
			System.out.println("Error al leer cfg.ini");
		}finally
    	{
    		try 
    		{
    			if (is!=null){
    				is.close();
    			}
    		}
    		catch(Exception exp)
    		{
    			exp.printStackTrace();
    		}
    	}
	}
}
