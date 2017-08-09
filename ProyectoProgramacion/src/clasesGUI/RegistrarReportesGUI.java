package clasesGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import clasesBean.PersonaBean;
import clasesBean.ProductoBean;
import clasesBean.TipoProductoBean;
import mysql.ConexionMySql;
import otrasClases.LanzadorReportes;

public class RegistrarReportesGUI {

	private JFrame mainFrame;

	private JPanel panel1;

	private JLabel fecha;
	private JLabel desdeFecha;
	private JLabel hastaFecha;
	private JLabel cliente;
	private JLabel tipoProducto;
	private JLabel producto;
	private JLabel reporte;

	private JDateChooser fechaDesde;
	private JDateChooser fechaHasta;

	private JComboBox<String> clienteBox;
	private JComboBox<String> tipoProductoBox;
	private JComboBox<String> productoBox;
	private JComboBox<String> reporteBox;

	private DefaultComboBoxModel<String> modeloClienteBox;
	private DefaultComboBoxModel<String> modeloTipoProductoBox;
	private DefaultComboBoxModel<String> modeloProductoBox;

	private JButton lanzarReporte;

	private ConexionMySql conexion = new ConexionMySql();
	private PersonaBean objPersona = new PersonaBean();
	private TipoProductoBean objTipoProducto = new TipoProductoBean();
	private ProductoBean objProducto = new ProductoBean();
	
	private DateFormat fechaInicio = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat fechaFin = new SimpleDateFormat("yyyy-MM-dd");
	private String fechaInicioStr;
	private String fechaFinStr;
	
	private HashMap<String, Object> hmParametros;

	public RegistrarReportesGUI() {

		showGUI();

	}

	public static void main (String[] args) {

		new RegistrarReportesGUI();

	}

	public void showGUI() {

		mainFrame = new JFrame("Administrar filtro de Reporte");
		mainFrame.setSize(500, 270);
		mainFrame.setLayout(new GridLayout(1, 1));
		centrarFrame(mainFrame);

		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel1.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(0, 0, 10, 0);
		Dimension box = new Dimension(150, 20);

		fecha = new JLabel("Fecha:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel1.add(fecha, constraints);

		desdeFecha = new JLabel("Desde: ");
		constraints.gridy++;
		panel1.add(desdeFecha, constraints);

		fechaDesde = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		fechaDesde.getJCalendar().setWeekOfYearVisible(false);
		fechaDesde.getJCalendar().setMaxDayCharacters(1);
		constraints.gridx++;
		panel1.add(fechaDesde, constraints);

		hastaFecha = new JLabel("Hasta: ");
		constraints.gridx++;
		panel1.add(hastaFecha, constraints);

		fechaHasta = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		fechaHasta.getJCalendar().setWeekOfYearVisible(false);
		fechaHasta.getJCalendar().setMaxDayCharacters(1);
		constraints.gridx++;
		panel1.add(fechaHasta, constraints);

		reporte = new JLabel("Reporte");
		constraints.gridx = 0;
		constraints.gridy++;
		panel1.add(reporte, constraints);

		reporteBox = new JComboBox<String>();
		reporteBox.addItem("GLDDVSV");
		reporteBox.setPreferredSize(box);
		constraints.gridx++;
		panel1.add(reporteBox, constraints);
		
		
		cliente = new JLabel("Cliente");
		constraints.gridx = 0;
		constraints.gridy++;
		panel1.add(cliente, constraints);

		modeloClienteBox = new DefaultComboBoxModel<String>();
		clienteBox = new JComboBox<String>();
		consultarCliente(modeloClienteBox);
		clienteBox.setPreferredSize(box);
		constraints.gridx++;
		panel1.add(clienteBox, constraints);

		tipoProducto = new JLabel("Tipo Producto");
		constraints.gridx = 0;
		constraints.gridy++;
		panel1.add(tipoProducto, constraints);

		modeloTipoProductoBox = new DefaultComboBoxModel<String>();
		tipoProductoBox = new JComboBox<String>();
		consultarTipoProducto(modeloTipoProductoBox);
		tipoProductoBox.addActionListener(new ButtonClickListener());
		tipoProductoBox.setActionCommand("tipoProductoSeleccionado");
		tipoProductoBox.setPreferredSize(box);
		constraints.gridx++;
		panel1.add(tipoProductoBox, constraints);

		producto = new JLabel("Producto");
		constraints.gridx = 0;
		constraints.gridy++;
		panel1.add(producto, constraints);

		modeloProductoBox = new DefaultComboBoxModel<String>();
		productoBox = new JComboBox<String>();
		consultarProducto(modeloProductoBox);
		productoBox.setPreferredSize(box);
		constraints.gridx++;
		panel1.add(productoBox, constraints);

		lanzarReporte = new JButton("Lanzar Reporte");
		lanzarReporte.addActionListener(new ButtonClickListener());
		lanzarReporte.setActionCommand("LanzarReporte");
		constraints.gridx = 0;
		constraints.gridy++;

		panel1.add(lanzarReporte, constraints);

		mainFrame.add(panel1, constraints);

		mainFrame.setVisible(true);

	}

	public class ButtonClickListener implements ActionListener{

		public void actionPerformed (ActionEvent e) {

			String command = e.getActionCommand();

				if(command.equals("tipoProductoSeleccionado")) 
					consultarProducto(modeloProductoBox);
				
				if(command.equals("LanzarReporte")) {
					
					fechaInicioStr = fechaInicio.format(fechaDesde.getDate());
					fechaFinStr = fechaFin.format(fechaHasta.getDate());
					
					if(reporteBox.getSelectedItem().toString().equals("GLDDVSV")) {
						
						String query = "SELECT cab.fechaPed, per.nombrePer, pro.descripcionPro, SUM(det.cantidadDetPed) "
								+ "FROM cab_pedido cab, vendedor ven, persona per, det_pedido det, producto pro "
								+ "WHERE cab.idVendedor=ven.idVendedor AND per.idPersona=ven.idPersona AND cab.idCabPedido=det.idCabPedido AND det.idProducto=pro.idProducto AND cab.fechaPed BETWEEN '" + fechaInicioStr + "' AND '" + fechaFinStr + "' "
								+ "GROUP BY pro.descripcionPro, cab.fechaPed "
								+ "ORDER BY cab.fechaPed ASC";
						
						hmParametros = new HashMap<String, Object>();
						hmParametros.put("query", query);
						System.out.println(query);
						LanzadorReportes lanzadorReportes = new LanzadorReportes(new JFrame(),"Reporte");
						lanzadorReportes.cargarReporte("/home/tkhacker/git/Estructura-de-Negocio/ProyectoProgramacion/Reportes/ventasSegunCliente.jrxml", hmParametros, conexion.getConeccion() );
						lanzadorReportes.setSize(new Dimension(820,600));
						lanzadorReportes.show(true);
						
					}
					
				}

		}
	}
	
	

	public void consultarCliente(DefaultComboBoxModel<String> modeloClienteBox) {

		
		String query = "SELECT persona.nombrePer FROM persona, cliente WHERE persona.idPersona=cliente.idPersona";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Clientes: " + query);

		modeloClienteBox.addElement("Elija una opcion ...");

		try {

			while(result.next()) {

				objPersona.setNombrePer(result.getString("nombrePer"));
				modeloClienteBox.addElement(objPersona.getNombrePer());

			}

		}catch(SQLException error) {

			System.out.println(error);

		}

		clienteBox.setModel(modeloClienteBox);

	}

	public void consultarTipoProducto(DefaultComboBoxModel<String> modeloTipoProductoBox) {

		String query = "SELECT descripcionTipPro FROM  tipo_producto";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Tipo Producto: " + query);

		modeloTipoProductoBox.addElement("Elija una opcion ...");

		try {

			while(result.next()) {

				objTipoProducto.setDescripcionTipPro(result.getString("descripcionTipPro"));
				modeloTipoProductoBox.addElement(objTipoProducto.getDescripcionTipPro());
			}

		}catch(SQLException error) {

			System.out.println(error);

		}

		tipoProductoBox.setModel(modeloTipoProductoBox);

	}

	public void consultarProducto(DefaultComboBoxModel<String> modeloProductoBox) {

		String query = "SELECT proa.descripcionPro FROM producto proa, tipo_producto a WHERE a.idTipoProducto=proa.idTipoProducto AND a.descripcionTipPro='" + tipoProductoBox.getSelectedItem().toString() + "'";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Producto: " + query);

		modeloProductoBox.removeAllElements();
		modeloProductoBox.addElement("Elija una opccion ...");

		try {

			while(result.next()) {

				objProducto.setDescripcionPro(result.getString("descripcionPro"));
				modeloProductoBox.addElement(objProducto.getDescripcionPro());

			}

		}catch(SQLException error) {

			System.out.println(error);

		}

		productoBox.setModel(modeloProductoBox);

	}



	public void centrarFrame(JFrame framePrincipal){

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - framePrincipal.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - framePrincipal.getHeight()) / 2);
		framePrincipal.setLocation(x, y);

	}

}

