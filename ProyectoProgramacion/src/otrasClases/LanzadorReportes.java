package otrasClases;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.sql.Connection;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;

public class LanzadorReportes extends JDialog{

	private static final long serialVersionUID = 308251665095677784L;
	public HashMap<String, Object> parametros;
	public String nombreArchivo; 

	JasperPrint archivoImprimir;
	JRViewer panelVisor;
	
	
	public LanzadorReportes(final Frame padre, final String titulo){
		
		super(padre, titulo,true);
		this.setResizable(true);
		
	}
	public LanzadorReportes(){
		
		super();
		
	}
	
	

	public void cargarReporte(String nombreArchivo, HashMap<String, Object> parametros, Connection datos){

		try {
	        JasperReport jasperReport =  JasperCompileManager.compileReport(nombreArchivo);
			System.out.println("LLege aqui");
	        this.parametros = parametros;
	        
			this.archivoImprimir = JasperFillManager.fillReport(jasperReport, this.parametros, datos);
			this.panelVisor= new JRViewer(this.archivoImprimir);
			this.getContentPane().add(this.panelVisor,BorderLayout.CENTER);
			
		} catch (final JRException e) {		
			
			e.printStackTrace();
			
		}
		
	}

	public void setVisible(final boolean ver){

		if(this.panelVisor!=null)
			super.setVisible(ver);
		
		else{

			JOptionPane.showMessageDialog(this, // padre
					"Se debe llenar el reporte antes de llamar al visor", // mensajito
					"Reporte Vacio", //titulo
					JOptionPane.ERROR_MESSAGE);

			this.dispose();
			
		}
	}
}

