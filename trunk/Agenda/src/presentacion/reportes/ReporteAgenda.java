package presentacion.reportes;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import dto.ReporteDTO;

public class ReporteAgenda
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	
	//Recibe la lista de personas para armar el reporte
    public ReporteAgenda(List<ReporteDTO> personas)
    {
    	//Hardcodeado
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		String reportSource = "/reportes/ReporteAgenda.jasper";
		InputStream is = null;
    	try		{
    		is = getClass().getResourceAsStream(reportSource);
			this.reporte = (JasperReport) JRLoader.loadObject(is);
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap, 
					new JRBeanCollectionDataSource(personas));
		}
		catch( JRException ex ) 
		{
			ex.printStackTrace();
		}
    	catch ( NullPointerException ex )
    	{
    		try		{
	    		this.reporte = (JasperReport) JRLoader.loadObjectFromFile( "reportes//ReporteAgenda.jasper" );
				this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap, 
						new JRBeanCollectionDataSource(personas));
    		}catch( JRException ex2 ) 
    		{
    			ex2.printStackTrace();
    		}
    	}
    	finally
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
    
    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
   
}	