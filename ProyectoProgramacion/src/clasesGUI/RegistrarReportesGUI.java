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
	private JLabel vendedor;
	private JLabel tipoProducto;
	private JLabel producto;
	private JLabel reporte;

	private JDateChooser fechaDesde;
	private JDateChooser fechaHasta;

	private JComboBox<String> clienteBox;
	private JComboBox<String> vendedorBox;
	private JComboBox<String> tipoProductoBox;
	private JComboBox<String> productoBox;
	private JComboBox<String> reporteBox;

	private DefaultComboBoxModel<String> modeloClienteBox;
	private DefaultComboBoxModel<String> modeloVendedorBox;
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
		mainFrame.setSize(500, 300);
		mainFrame.setLayout(new GridLayout(1, 1));
		mainFrame.setAlwaysOnTop(true);
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
		reporteBox.addItem("Elija una opcion ...");
		reporteBox.addItem("GTDDVSV");	//Generar Tabla Diaria de Ventas Segun Vendedor
		reporteBox.addItem("GTDDVSC");	//Generar Tabla Diaria de Ventas Segun Cliente
		reporteBox.addItem("GLDCVSP");	//Generar Lista de Cantidad Vendida Segun Provincia
		reporteBox.addActionListener(new ButtonClickListener());
		reporteBox.setActionCommand("ReporteBox");
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

		vendedor = new JLabel("Vendedor");
		constraints.gridx = 0;
		constraints.gridy++;
		panel1.add(vendedor, constraints);

		modeloVendedorBox = new DefaultComboBoxModel<String>();
		vendedorBox = new JComboBox<String>();
		consultarVendedor(modeloVendedorBox);
		vendedorBox.setPreferredSize(box);
		constraints.gridx++;
		panel1.add(vendedorBox, constraints);

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
			
			Boolean valido = true;

			if(command.equals("tipoProductoSeleccionado")) 
				consultarProducto(modeloProductoBox);

			if(command.equals("LanzarReporte")) {

				try {
					
					fechaInicioStr = fechaInicio.format(fechaDesde.getDate());
					fechaFinStr = fechaFin.format(fechaHasta.getDate());
				
				}catch(NullPointerException error) {
					
					JOptionPane.showMessageDialog(mainFrame, "Elija un rango de fecha", "Datos Insuficientes", 3);
					valido = false;
					
				}

				if(reporteBox.getSelectedItem().toString().equals("GTDDVSV") && valido) {

					String nombre = "";
					if(!vendedorBox.getSelectedItem().toString().equals("Elija una opcion ...")) 
						nombre = " AND per.nombrePer='" + vendedorBox.getSelectedItem().toString() + "' ";

					String producto = "";
					if(!productoBox.getSelectedItem().toString().equals("Elija una opccion ..."))
						producto = " AND pro.descripcionPro='" + productoBox.getSelectedItem().toString() + "' ";

					String query = "SELECT cab.fechaPed, per.nombrePer, pro.descripcionPro, SUM(det.cantidadDetPed) "
							+ "FROM cab_pedido cab, vendedor ven, persona per, det_pedido det, producto pro "
							+ "WHERE cab.idVendedor=ven.idVendedor AND per.idPersona=ven.idPersona AND cab.idCabPedido=det.idCabPedido AND det.idProducto=pro.idProducto " + nombre + producto + "AND cab.fechaPed BETWEEN '" + fechaInicioStr + "' AND '" + fechaFinStr + "' "
							+ "GROUP BY pro.descripcionPro, cab.fechaPed "
							+ "ORDER BY cab.fechaPed ASC";

					hmParametros = new HashMap<String, Object>();
					hmParametros.put("query", query);
					System.out.println(query);
					LanzadorReportes lanzadorReportes = new LanzadorReportes(new JFrame(),"Reporte");
					lanzadorReportes.cargarReporte("/home/tkhacker/git/Estructura-de-Negocio/ProyectoProgramacion/Reportes/ventasSegunVendedor.jrxml", hmParametros, conexion.getConeccion());
					lanzadorReportes.setSize(new Dimension(820,800));
					lanzadorReportes.show(true);

				}

				if(reporteBox.getSelectedItem().toString().equals("GTDDVSC") && valido) {

					String nombre = "";
					if(!clienteBox.getSelectedItem().toString().equals("Elija una opcion ...")) 
						nombre = " AND per.nombrePer='" + clienteBox.getSelectedItem().toString() + "' ";

					String producto = "";
					if(!productoBox.getSelectedItem().toString().equals("Elija una opccion ..."))
						producto = " AND pro.descripcionPro='" + productoBox.getSelectedItem().toString() + "' ";

					String query ="SELECT cab.fechaPed, per.nombrePer AS Cliente, pro.descripcionPro AS Producto, SUM(det.cantidadDetPed) AS Cantidad  "
							+ "FROM persona per, cliente cli, cab_pedido cab, det_pedido det, producto pro "
							+ "WHERE per.idPersona=cli.idPersona AND cli.idCliente=cab.idCliente AND cab.idCabPedido=det.idCabPedido AND det.idProducto=pro.idProducto " + nombre + producto + "AND cab.fechaPed BETWEEN '" + fechaInicioStr + "' AND '" + fechaFinStr + "' "
							+ "GROUP BY cab.fechaPed ASC, per.nombrePer, pro.descripcionPro";

					hmParametros = new HashMap<String, Object>();
					hmParametros.put("query", query);
					System.out.println(query);
					LanzadorReportes lanzadorReportes = new LanzadorReportes(new JFrame(),"Reporte");
					lanzadorReportes.cargarReporte("/home/tkhacker/git/Estructura-de-Negocio/ProyectoProgramacion/Reportes/ventasSegunCliente.jrxml", hmParametros, conexion.getConeccion());
					lanzadorReportes.setSize(new Dimension(820,800));
					lanzadorReportes.show(true);

				}

				if(reporteBox.getSelectedItem().toString().equals("GLDCVSP") && !productoBox.getSelectedItem().toString().equals("Elija una opccion ...") && valido) {

					String producto = "";
					if(!productoBox.getSelectedItem().toString().equals("Elija una opccion ..."))
						producto = " AND pro.descripcionPro='" + productoBox.getSelectedItem().toString() + "' ";

					String query =" SELECT lug3.descripcionLugGeo, SUM(det.cantidadDetPed) "
							+ "FROM cab_pedido cab, lugar_geo lug, lugar_geo lug2, lugar_geo lug3, cliente cli, det_pedido det, producto pro "
							+ "WHERE lug.idLugarGeoPadre=lug2.idLugarGeo AND lug2.idLugarGeoPadre=lug3.idLugarGeo AND cli.idLugarGeo=lug.idLugarGeo AND cab.idCliente=cli.idCliente AND det.idCabPedido=cab.idCabPedido AND det.idProducto =pro.idProducto " + producto + "AND cab.fechaPed BETWEEN '" + fechaInicioStr + "' AND '" + fechaFinStr + "' "
							+ "GROUP BY lug3.descripcionLugGeo ASC, pro.descripcionPro";

					hmParametros = new HashMap<String, Object>();
					hmParametros.put("query", query);
					hmParametros.put("subtitulo", "Producto: " + productoBox.getSelectedItem().toString().toUpperCase());
					hmParametros.put("fecha", "Desde: " + fechaInicioStr + " Hasta: " + fechaFinStr);
					System.out.println(query);
					LanzadorReportes lanzadorReportes = new LanzadorReportes(new JFrame(),"Reporte");
					lanzadorReportes.cargarReporte("/home/tkhacker/git/Estructura-de-Negocio/ProyectoProgramacion/Reportes/ventasSegunProvincia.jrxml", hmParametros, conexion.getConeccion());
					lanzadorReportes.setSize(new Dimension(820,800));
					lanzadorReportes.show(true);

				}

				else if(reporteBox.getSelectedItem().toString().equals("GLDCVSP") && productoBox.getSelectedItem().toString().equals("Elija una opccion ...")) 
						JOptionPane.showMessageDialog(mainFrame, "Elija un producto", "Datos Insuficientes", 3);



			}

			if(command.equals("ReporteBox")) {

				if(reporteBox.getSelectedItem().toString().equals("GTDDVSV")) {

					clienteBox.setEnabled(true);
					vendedorBox.setEnabled(true);
					tipoProductoBox.setEnabled(true);
					productoBox.setEnabled(true);
					clienteBox.setEnabled(false);

				}

				if(reporteBox.getSelectedItem().toString().equals("GTDDVSC")) {

					clienteBox.setEnabled(true);
					vendedorBox.setEnabled(true);
					tipoProductoBox.setEnabled(true);
					productoBox.setEnabled(true);
					vendedorBox.setEnabled(false);

				}

				if(reporteBox.getSelectedItem().toString().equals("GLDCVSP")) {

					clienteBox.setEnabled(true);
					vendedorBox.setEnabled(true);
					tipoProductoBox.setEnabled(true);
					productoBox.setEnabled(true);
					clienteBox.setEnabled(false);
					vendedorBox.setEnabled(false);

				}

			}

		}
	}



	public void consultarCliente(DefaultComboBoxModel<String> modeloClienteBox) {


		String query = "SELECT persona.nombrePer FROM persona, cliente WHERE persona.idPersona=cliente.idPersona ORDER BY nombrePer ASC";
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

	public void consultarVendedor(DefaultComboBoxModel<String> modeloVendedorBox) {

		String query = "SELECT persona.nombrePer FROM persona, vendedor WHERE persona.idPersona=vendedor.idPersona ORDER BY nombrePer ASC;";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Vendedores: " + query);

		modeloVendedorBox.addElement("Elija una opcion ...");

		try {

			while(result.next()) {

				objPersona.setNombrePer(result.getString("nombrePer"));
				modeloVendedorBox.addElement(objPersona.getNombrePer());

			}

		}catch(SQLException error) {

			System.out.println(error);

		}

		vendedorBox.setModel(modeloVendedorBox);

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

