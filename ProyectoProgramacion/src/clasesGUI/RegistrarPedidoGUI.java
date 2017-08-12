package clasesGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//import org.jdatepicker.impl.*;

import com.toedter.calendar.JDateChooser;
import mysql.ConexionMySql;
import clasesBean.*;

import java.util.ArrayList;
import java.util.HashMap;

public class RegistrarPedidoGUI {

	JFrame mainFrame;

	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panelIDCab;
	private JPanel panelElementosCab;
	private JPanel panelIDDet;
	private JPanel panelElementosDet;

	private JLabel idCabeceraPedido;
	private JLabel idCliente;
	private JLabel idVendedor;
	//private JLabel idTipoCobro;
	private JLabel numPedido;
	private JLabel estado;
	private JLabel fechaPedido;
	private JLabel cliente;
	private JLabel vendedor;
	private JLabel idProducto;
	private JLabel tipoProducto;
	private JLabel producto;
	private JLabel cantidad;
	private JLabel precioVenta;
	//private JLabel tipoCobro; 
	private JLabel disponible;
	private JLabel subTotal;
	private JLabel IVA;
	private JLabel total;

	private JTextField idCabeceraPedidoTxt;
	private JTextField idClienteTxt;
	private JTextField idVendedorTxt;
	//private JTextField idTipoCobroTxt;
	private JTextField numPedidoTxt;
	private JTextField estadoTxt;
	private JTextField idProductoTxt;
	private JTextField cantidadTxt;
	private JTextField precioVentaTxt;
	private JTextField disponibleTxt;
	private JTextField subTotalTxt;
	private JTextField IVATxt;
	private JTextField totalTxt;

	private JComboBox<String> clienteBox;
	private JComboBox<String> vendedorBox;
	private JComboBox<String> tipoProductoBox;
	private JComboBox<String> productoBox;
	//private JComboBox<String> tipoCobroBox;

	private JButton aceptarCab;
	private JButton cancelarCab;
	private JButton aceptarDet;
	private JButton cancelarDet;
	private JButton guardar;
	private JButton cancelar;

	private JTable tabla;
	private JScrollPane tablaScrl;
	
	private JDateChooser date;

	private DefaultTableModel modelo;
	private DefaultComboBoxModel<String> modeloClienteBox;
	private DefaultComboBoxModel<String> modeloVendedorBox;
	//private DefaultComboBoxModel<String> modeloCobroBox;
	private DefaultComboBoxModel<String> modelotipoProductoBox;
	private DefaultComboBoxModel<String> modeloProductoBox;

	private ConexionMySql conexion = new ConexionMySql();
	private PersonaBean objPersona = new PersonaBean();
	private CapPedidoBean objCabPedidoBean = new CapPedidoBean();
	private TipoProductoBean objTipoProducto = new TipoProductoBean();
	private ProductoBean objProducto = new ProductoBean();
	private DetPedidoBean objDetPedido = new DetPedidoBean();
	//ClienteBean objCliente = new ClienteBean();
	//VendedorBean objVendedor = new VendedorBean();
	//Tipo_CobroBean objCobro = new Tipo_CobroBean();

	private ArrayList<DetPedidoBean> listObjDetPed = new ArrayList<DetPedidoBean>();
	private HashMap <Integer, Integer> hmSaldoPro = new HashMap<Integer, Integer>();
	private HashMap <Integer, Integer> hmComprometidoPro = new HashMap<Integer, Integer>();
	
	private DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
	private String fechaStr;

	private int saldo, comprometido;
	
	private double subtotal;

	public RegistrarPedidoGUI() {

		showGUI();

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

	/*public void consultarCobro(DefaultComboBoxModel<String> modeloCobroBox) {

		String query = "SELECT descripcionTipCob FROM tipo_cobro";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Cobro: " + query);

		try {

			while(result.next()) {

				objCobro.setDescripcionTipCob(result.getString("descripcionTipCob"));
				modeloCobroBox.addElement(objCobro.getDescripcionTipCob());

			}

		}catch(SQLException error) {

			System.out.println(error);

		}

		tipoCobroBox.setModel(modeloCobroBox);

	}*/

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

	public void consultarPrecio(String productoSeleccionado) {

		String query = "SELECT deta.precioVenDetPed FROM det_pedido deta, producto proa WHERE proa.idProducto=deta.idProducto AND proa.descripcionPro='" + productoBox.getSelectedItem().toString() + "'";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Precio: " + query);

		try {

			result.next();
			precioVentaTxt.setText(result.getString("precioVenDetPed"));

		}catch(SQLException error) {

			System.out.println(error);

		}


	}

	public void consultarIDCliente(String clienteSeleccionado) {

		String query = "SELECT cliente.idCliente FROM persona, cliente WHERE persona.idPersona=cliente.idPersona AND persona.nombrePer='" + clienteSeleccionado + "'";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Cliente seleccionado: " + query);

		try {

			result.next();
			objCabPedidoBean.setIdCliente(result.getInt("idCliente"));

		}catch(SQLException error) {

			System.out.println(error);

		}

		idClienteTxt.setText(Integer.toString(objCabPedidoBean.getIdCliente()));

	}

	public void consultarIDVendedor(String vendedorSeleccionado) {

		String query = "SELECT vendedor.idVendedor FROM persona, vendedor WHERE persona.idPersona=vendedor.idPersona AND persona.nombrePer='" + vendedorSeleccionado + "'";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Vendedor seleccionado: " + query);

		try {

			result.next();
			objCabPedidoBean.setIdVendedor(result.getInt("idVendedor"));

		}catch(SQLException error) {

			System.out.println(error);

		}

		idVendedorTxt.setText(Integer.toString(objCabPedidoBean.getIdVendedor()));

	}

	/*public void consultarIDCobro(String cobroSeleccionado) {

		String query = "SELECT idTipoCobro FROM tipo_cobro WHERE descripcionTipCob='" + cobroSeleccionado + "'";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Cobro seleccionado: " + query);

		try {

			result.next();
			objCobro.setIdTipoCobro(result.getInt("idTipoCobro"));

		}catch(SQLException error) {

			System.out.println(error);

		}

		idTipoCobroTxt.setText(Integer.toString(objCobro.getIdTipoCobro()));

	}*/

	public void consultarIDCabPedido() {

		String query = "SELECT idCabPedido FROM cab_pedido WHERE numeroPed='" + numPedidoTxt.getText() + "'";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Id Cabecera pedido: " + query);

		try {

			result.next();
			objCabPedidoBean.setIdCabPedido(result.getInt("idCabPedido"));

		}catch(SQLException error) {

			System.out.println(error);

		}

		idCabeceraPedidoTxt.setText(Integer.toString(objCabPedidoBean.getIdCabPedido()));

	}

	public void consultarIDproducto(String productoSeleccionado) {

		String query = "SELECT idProducto FROM producto WHERE descripcionPro='" + productoSeleccionado + "'";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Id Producto: " + query);

		try {

			result.next();
			objProducto.setIdProducto(result.getInt("idProducto"));

		}catch(SQLException error) {

			System.out.println(error);

		}

		idProductoTxt.setText(Integer.toString(objProducto.getIdProducto()));
		consultarPrecio(productoSeleccionado);

		saldo = hmSaldoPro.get(Integer.parseInt(idProductoTxt.getText()));
		comprometido = hmComprometidoPro.get(Integer.parseInt(idProductoTxt.getText()));

		disponibleTxt.setText(Integer.toString(saldo - comprometido));



	}

	public void consultarDisponible() {

		String query = "SELECT producto.idProducto, producto.saldoPro, producto.comprometidoPro FROM producto";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Disponible: " + query);

		try {

			while(result.next()) {

				objProducto.setIdProducto(result.getInt("idProducto"));
				objProducto.setSaldoPro(result.getInt("saldoPro"));
				objProducto.setComprometidoPro(result.getInt("comprometidoPro"));

				hmSaldoPro.put(objProducto.getIdProducto(), objProducto.getSaldoPro());
				hmComprometidoPro.put(objProducto.getIdProducto(), objProducto.getComprometidoPro());
				
			}

		}catch(SQLException error) {

			System.out.println(error);

		}

	}

	public void obtenerUltimoNumPed() {

		String query = "SELECT MAX(numeroPed) AS UltimoPedido FROM cab_pedido";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Ultimo Pedido: " + query);

		try {

			result.next();
			objCabPedidoBean.setNumeroPed(result.getString("UltimoPedido"));

		}catch(SQLException error) {

			System.out.println(error);

		}

		int i = Integer.parseInt(objCabPedidoBean.getNumeroPed().substring(4)) + 1;
		System.out.println("Numero de pedido: " + i);

		numPedidoTxt.setText(objCabPedidoBean.getNumeroPed().substring(0, 3) + "-" + "0000".substring(Integer.toString(i).length()) + i);
		System.out.println(numPedidoTxt.getText());	

	}

	public void insertarUltimoNumPed() {

		objCabPedidoBean.setNumeroPed(numPedidoTxt.getText());

		String insertarNumPed = "INSERT INTO ventas2017a.cab_pedido (estadoPed, fechaPed, numeroFacturaPed, numeroPed, idCliente, idVendedor) VALUES ('" + objCabPedidoBean.getEstadoPed() + "', '" + fechaStr + "', '', '" + objCabPedidoBean.getNumeroPed() + "', '" + objCabPedidoBean.getIdCliente() + "', '" + objCabPedidoBean.getIdVendedor() + "')";
		System.out.println("Insertar cabecera: " + insertarNumPed);

		conexion.insertar(insertarNumPed);

	}

	public void insertarDetPedido() {

		objCabPedidoBean.setIdCabPedido(Integer.parseInt(idCabeceraPedidoTxt.getText()));

		for(int i = 0 ; i < listObjDetPed.size() ; i++) {

			String insertarDetPedido = "INSERT INTO ventas2017a.det_pedido (cantidadDetPed, precioVenDetPed, idCabPedido, idProducto) VALUES ('" + listObjDetPed.get(i).getCantidadDetPed() +"', '" + listObjDetPed.get(i).getPrecioVtaDetPed() + "', '" + idCabeceraPedidoTxt.getText() + "', '" + listObjDetPed.get(i).getIdProducto() + "')";
			System.out.println("Insertar Detalle Pedido: " + insertarDetPedido);

			conexion.insertar(insertarDetPedido);
		}
	}

	public void modelarTabla(DefaultTableModel modelo) {

		modelo.addColumn("idProducto");
		modelo.addColumn("Tipo Producto");
		modelo.addColumn("Producto");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Precio");

		tabla.setModel(modelo);

	}

	public void consultarSaldo(String productoSeleccionado) {

		String query = "SELECT saldoPro FROM producto WHERE descripcionPro='" + productoSeleccionado + "'";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Saldo: " + query);

		try {

			result.next();
			objProducto.setSaldoPro(result.getInt("saldoPro"));

		}catch(SQLException error) {

			System.out.println(error);

		}

	}

	public void showGUI() {

		mainFrame = new JFrame("Registrar Pedido");
		mainFrame.setSize(900, 580);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.setAlwaysOnTop(true);
		centrarFrame(mainFrame);

		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel1.setLayout(new GridBagLayout());
		mainFrame.add(panel1);

		GridBagConstraints constraintsPanel1 = new GridBagConstraints();
		constraintsPanel1.anchor = GridBagConstraints.LINE_START;
		Dimension idTxtDimen = new Dimension(50, 20);
		Dimension TxtDimen = new Dimension(75, 20);

		panelIDCab = new JPanel();
		//panelIDCab.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelIDCab.setLayout(new GridBagLayout());
		constraintsPanel1.gridx = 0;
		constraintsPanel1.gridy = 0;
		panel1.add(panelIDCab, constraintsPanel1);

		GridBagConstraints constraintsPanelIDCab = new GridBagConstraints();

		idCabeceraPedido = new JLabel("idCabPedido");
		constraintsPanelIDCab.gridx = 0;
		constraintsPanelIDCab.gridy = 0;
		constraintsPanelIDCab.insets = new Insets(0, 0, 0, 5);
		panelIDCab.add(idCabeceraPedido, constraintsPanelIDCab);

		idCabeceraPedidoTxt = new JTextField();
		idCabeceraPedidoTxt.setPreferredSize(idTxtDimen);
		idCabeceraPedidoTxt.setEnabled(false);
		constraintsPanelIDCab.gridx++;
		panelIDCab.add(idCabeceraPedidoTxt, constraintsPanelIDCab);

		idCliente = new JLabel("idCliente");
		constraintsPanelIDCab.gridx++;
		panelIDCab.add(idCliente, constraintsPanelIDCab);

		idClienteTxt = new JTextField();
		idClienteTxt.setPreferredSize(idTxtDimen);
		idClienteTxt.setEnabled(false);
		constraintsPanelIDCab.gridx++;
		panelIDCab.add(idClienteTxt, constraintsPanelIDCab);

		idVendedor = new JLabel("idVendedor");
		constraintsPanelIDCab.gridx++;
		panelIDCab.add(idVendedor, constraintsPanelIDCab);

		idVendedorTxt = new JTextField();
		idVendedorTxt.setPreferredSize(idTxtDimen);
		idVendedorTxt.setEnabled(false);
		constraintsPanelIDCab.gridx++;
		panelIDCab.add(idVendedorTxt, constraintsPanelIDCab);

		/*idTipoCobro = new JLabel("idTipoCobro");
		constraintsPanel1.gridx++;
		panel1.add(idTipoCobro, constraintsPanel1);

		idTipoCobroTxt = new JTextField();
		idTipoCobroTxt.setPreferredSize(idTxtDimen);
		idTipoCobroTxt.setEnabled(false);
		constraintsPanel1.gridx++;
		panel1.add(idTipoCobroTxt, constraintsPanel1);*/

		panelElementosCab = new JPanel();
		//panelElemetosCab.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelElementosCab.setLayout(new GridBagLayout());
		constraintsPanel1.gridy++;
		constraintsPanel1.insets = new Insets(15, 0, 0, 0);
		panel1.add(panelElementosCab, constraintsPanel1);

		GridBagConstraints constraintsPanelElementoCab = new GridBagConstraints();
		constraintsPanelElementoCab.anchor  = GridBagConstraints.LINE_START;

		numPedido = new JLabel("Numero de Pedido");
		constraintsPanelElementoCab.gridx = 0;
		constraintsPanelElementoCab.gridy = 0;
		constraintsPanelElementoCab.insets = new Insets(0, 0, 4, 7);
		panelElementosCab.add(numPedido, constraintsPanelElementoCab);

		numPedidoTxt = new JTextField();
		numPedidoTxt.setPreferredSize(TxtDimen);
		numPedidoTxt.setEnabled(false);
		constraintsPanelElementoCab.gridx++;
		panelElementosCab.add(numPedidoTxt, constraintsPanelElementoCab);

		estado = new JLabel("Estado");
		constraintsPanelElementoCab.gridx++;
		panelElementosCab.add(estado, constraintsPanelElementoCab);

		estadoTxt = new JTextField();
		estadoTxt.setPreferredSize(TxtDimen);
		constraintsPanelElementoCab.gridx++;
		panelElementosCab.add(estadoTxt, constraintsPanelElementoCab);

		fechaPedido = new JLabel("Fecha");
		constraintsPanelElementoCab.gridx++;
		panelElementosCab.add(fechaPedido, constraintsPanelElementoCab);

		date = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
		date.getJCalendar().setWeekOfYearVisible(false);
		date.getJCalendar().setMaxDayCharacters(1);
		
		constraintsPanelElementoCab.gridx++;

		panelElementosCab.add(date, constraintsPanelElementoCab);
		

		cliente = new JLabel("Cliente");
		constraintsPanelElementoCab.gridx = 0;
		constraintsPanelElementoCab.gridy++;
		panelElementosCab.add(cliente, constraintsPanelElementoCab);

		modeloClienteBox = new DefaultComboBoxModel<String>();
		clienteBox = new JComboBox<String>();
		consultarCliente(modeloClienteBox);
		clienteBox.addActionListener(new ButtonClickListener());
		clienteBox.setActionCommand("clienteSeleccionado");
		constraintsPanelElementoCab.gridx++;
		panelElementosCab.add(clienteBox, constraintsPanelElementoCab);

		vendedor = new JLabel("Vendedor");
		constraintsPanelElementoCab.gridx++;
		panelElementosCab.add(vendedor, constraintsPanelElementoCab);

		modeloVendedorBox = new DefaultComboBoxModel<String>();
		vendedorBox = new JComboBox<String>();
		consultarVendedor(modeloVendedorBox);
		vendedorBox.addActionListener(new ButtonClickListener());
		vendedorBox.setActionCommand("vendedorSeleccionado");
		constraintsPanelElementoCab.gridx++;
		panelElementosCab.add(vendedorBox, constraintsPanelElementoCab);

		/*tipoCobro = new JLabel("Cobro");
		constraintsPanel1.gridx++;
		panel1.add(tipoCobro, constraintsPanel1);

		modeloCobroBox = new DefaultComboBoxModel<String>();
		tipoCobroBox = new JComboBox<String>();
		consultarCobro(modeloCobroBox);
		tipoCobroBox.addActionListener(new ButtonClickListener());
		tipoCobroBox.setActionCommand("tipoCobroSeleccionado");
		constraintsPanel1.gridx++;
		panel1.add(tipoCobroBox, constraintsPanel1);*/

		aceptarCab = new JButton("Aceptar");
		aceptarCab.addActionListener(new ButtonClickListener());
		aceptarCab.setActionCommand("aceptarCab");
		constraintsPanelElementoCab.gridx = 0;
		constraintsPanelElementoCab.gridy++;
		panelElementosCab.add(aceptarCab, constraintsPanelElementoCab);

		cancelarCab = new JButton("Cancelar");
		cancelarCab.addActionListener(new ButtonClickListener());
		cancelarCab.setActionCommand("cancelarCab");
		constraintsPanelElementoCab.gridx++;
		constraintsPanelElementoCab.insets = new Insets(0, -48, 4, 7);
		panelElementosCab.add(cancelarCab, constraintsPanelElementoCab);


		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel2.setLayout(new GridBagLayout());
		mainFrame.add(panel2);

		GridBagConstraints constraintsPanel2 = new GridBagConstraints();
		constraintsPanel2.anchor = GridBagConstraints.LINE_START;
		constraintsPanel2.insets = new Insets(0, -120, 10, 0);

		panelIDDet = new JPanel();
		//panelIDDet.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelIDDet.setLayout(new GridBagLayout());
		constraintsPanel2.gridx = 0;
		constraintsPanel2.gridy = 0;
		panel2.add(panelIDDet, constraintsPanel2);

		GridBagConstraints constraintsPanelIDDet = new GridBagConstraints();
		constraintsPanelIDDet.anchor = GridBagConstraints.LINE_START;
		constraintsPanelIDDet.insets = new Insets(0, 0, 0, 5);

		idProducto = new JLabel("idProducto");
		constraintsPanelIDDet.gridx = 0;
		constraintsPanelIDDet.gridy = 0;
		panelIDDet.add(idProducto, constraintsPanelIDDet);

		idProductoTxt = new JTextField();
		idProductoTxt.setPreferredSize(idTxtDimen);
		idProductoTxt.setEnabled(false);
		constraintsPanelIDDet.gridx++;
		panelIDDet.add(idProductoTxt, constraintsPanelIDDet);

		panelElementosDet = new JPanel();
		//panelElementosDet.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelElementosDet.setLayout(new GridBagLayout());
		constraintsPanel2.gridy++;
		panel2.add(panelElementosDet, constraintsPanel2);

		GridBagConstraints constraintsElementosDet = new GridBagConstraints();
		constraintsElementosDet.anchor = GridBagConstraints.LINE_START;
		constraintsElementosDet.insets = new Insets(0, 0, 4, 7);

		disponible = new JLabel("Disponible");
		constraintsElementosDet.gridx = 0;
		constraintsElementosDet.gridy = 0;
		panelElementosDet.add(disponible, constraintsElementosDet);

		disponibleTxt = new JTextField();
		disponibleTxt.setPreferredSize(TxtDimen);
		disponibleTxt.setEnabled(false);
		constraintsElementosDet.gridx++;
		panelElementosDet.add(disponibleTxt, constraintsElementosDet);

		tipoProducto = new JLabel("Tipo");
		constraintsElementosDet.gridx = 0;
		constraintsElementosDet.gridy++;
		panelElementosDet.add(tipoProducto, constraintsElementosDet);

		modelotipoProductoBox = new DefaultComboBoxModel<String>();
		tipoProductoBox = new JComboBox<String>();
		consultarTipoProducto(modelotipoProductoBox);
		tipoProductoBox.addActionListener(new ButtonClickListener());
		tipoProductoBox.setActionCommand("tipoProductoSeleccionado");
		constraintsElementosDet.gridx++;
		panelElementosDet.add(tipoProductoBox, constraintsElementosDet);

		producto = new JLabel("Producto");
		constraintsElementosDet.gridx++;
		panelElementosDet.add(producto, constraintsElementosDet);

		modeloProductoBox = new DefaultComboBoxModel<String>();
		productoBox = new JComboBox<String>();
		productoBox.addActionListener(new ButtonClickListener());
		productoBox.setActionCommand("productoSeleccionado");
		productoBox.addItem("Elija una opccion ...");
		constraintsElementosDet.gridx++;
		panelElementosDet.add(productoBox, constraintsElementosDet);

		cantidad = new JLabel("Cantidad");
		constraintsElementosDet.gridx = 0;
		constraintsElementosDet.gridy++;
		panelElementosDet.add(cantidad, constraintsElementosDet);

		cantidadTxt = new JTextField();
		cantidadTxt.setPreferredSize(TxtDimen);
		constraintsElementosDet.gridx++;
		panelElementosDet.add(cantidadTxt, constraintsElementosDet);

		precioVenta = new JLabel("Precio");
		constraintsElementosDet.gridx++;
		panelElementosDet.add(precioVenta, constraintsElementosDet);

		precioVentaTxt = new JTextField();
		precioVentaTxt.setPreferredSize(TxtDimen);
		constraintsElementosDet.gridx++;
		panelElementosDet.add(precioVentaTxt, constraintsElementosDet);

		aceptarDet = new JButton("Aceptar");
		aceptarDet.addActionListener(new ButtonClickListener());
		aceptarDet.setActionCommand("aceptarDet");
		constraintsElementosDet.gridx = 0;
		constraintsElementosDet.gridy++;
		panelElementosDet.add(aceptarDet, constraintsElementosDet);

		cancelarDet = new JButton("Cancelar");
		cancelarDet.addActionListener(new ButtonClickListener());
		cancelarDet.setActionCommand("cancelarDet");
		constraintsElementosDet.insets = new Insets(0, -7, 4, 0);
		constraintsElementosDet.gridx++;
		panelElementosDet.add(cancelarDet, constraintsElementosDet);


		panel3 = new JPanel();
		panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel3.setLayout(new GridLayout(2, 1));
		mainFrame.add(panel3);

		modelo = new DefaultTableModel();
		tabla = new JTable();
		modelarTabla(modelo);
		tabla.setDefaultEditor(Object.class, null);	//Se puede seleccionar pero no editar
		tablaScrl = new JScrollPane(tabla);
		panel3.add(tablaScrl);

		panel4 = new JPanel();
		panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel4.setLayout(new GridBagLayout());
		panel3.add(panel4);

		GridBagConstraints constraintsPanel4 = new GridBagConstraints();
		constraintsPanel4.anchor = GridBagConstraints.LINE_END;
		
		subTotal = new JLabel("Subtotal: ");
		constraintsPanel4.gridx = 0;
		constraintsPanel4.gridy = 0;
		constraintsPanel4.insets = new Insets(0, 0, 0, -300);
		panel4.add(subTotal, constraintsPanel4);
		
		subTotalTxt = new JTextField();
		subTotalTxt.setPreferredSize(new Dimension(70, 20));
		subTotalTxt.setEditable(false);
		constraintsPanel4.gridx++;
		panel4.add(subTotalTxt, constraintsPanel4);
		
		IVA = new JLabel("IVA: ");
		constraintsPanel4.gridx = 0;
		constraintsPanel4.gridy++;
		constraintsPanel4.insets = new Insets(0, 0, 0, -300);
		panel4.add(IVA, constraintsPanel4);
		
		IVATxt = new JTextField("12%");
		IVATxt.setPreferredSize(new Dimension(70, 20));
		IVATxt.setEditable(false);
		constraintsPanel4.gridx++;
		panel4.add(IVATxt, constraintsPanel4);
		
		total = new JLabel("Total: ");
		constraintsPanel4.gridx = 0;
		constraintsPanel4.gridy++;
		constraintsPanel4.insets = new Insets(0, 0, 0, -300);
		panel4.add(total, constraintsPanel4);
		
		totalTxt = new JTextField();
		totalTxt.setPreferredSize(new Dimension(70, 20));
		totalTxt.setEditable(false);
		constraintsPanel4.gridx++;
		panel4.add(totalTxt, constraintsPanel4);
		
		constraintsPanel4.insets = new Insets(20, 0, 0, 0);
		
		guardar = new JButton("Guardar");
		guardar.addActionListener(new ButtonClickListener());
		guardar.setActionCommand("guardar");
		
		panel4.add(guardar, constraintsPanel4);

		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ButtonClickListener());
		cancelar.setActionCommand("cancelar");
		constraintsPanel4.gridx++;
		panel4.add(cancelar, constraintsPanel4);

		mainFrame.setVisible(true);

	}

	public class ButtonClickListener implements ActionListener{

		public void actionPerformed (ActionEvent e) {

			String command = e.getActionCommand();
			Boolean valido = true;

			if(command.equals("clienteSeleccionado")) 
				consultarIDCliente(clienteBox.getSelectedItem().toString());

			if(command.equals("vendedorSeleccionado"))
				consultarIDVendedor(vendedorBox.getSelectedItem().toString());

			//if(command.equals("tipoCobroSeleccionado"))
			//consultarIDCobro(modeloCobroBox.getSelectedItem().toString());

			if(command.equals("tipoProductoSeleccionado"))
				consultarProducto(modeloProductoBox);

			if(command.equals("productoSeleccionado")) {

				try {

					consultarIDproducto(productoBox.getSelectedItem().toString());

				}catch(NullPointerException error) {

					System.out.println(error);

				}

			}

			if(command.equals("aceptarCab")) {

				if(!idClienteTxt.getText().equals("")) {

					objCabPedidoBean.setIdCliente(Integer.parseInt(idClienteTxt.getText()));

				}
				else {

					valido = false;

				}

				if(!idVendedorTxt.getText().equals("")) {

					objCabPedidoBean.setIdVendedor(Integer.parseInt(idVendedorTxt.getText()));

				}
				else {

					valido = false;

				}

				/*if(!idTipoCobroTxt.getText().equals("")) {

					objCabPedidoBean.setIdTipoCobro(Integer.parseInt(idTipoCobroTxt.getText()));

				}
				else {

					valido = false;

				}*/

				if(!estadoTxt.getText().equals("") && (estadoTxt.getText().equals("P") || estadoTxt.getText().equals("V"))) {

					objCabPedidoBean.setEstadoPed(estadoTxt.getText());

				}
				else {

					valido = false;

				}

				if(valido) {

					estadoTxt.setEnabled(false);
					clienteBox.setEnabled(false);
					vendedorBox.setEnabled(false);
					//tipoCobroBox.setEnabled(false);
					cancelarCab.setEnabled(false);
					aceptarCab.setEnabled(false);
					panel1.setBackground(Color.GRAY);
					panelElementosCab.setBackground(Color.GRAY);
					panelIDCab.setBackground(Color.GRAY);
					consultarDisponible();
					fechaStr = fecha.format(date.getDate());
					System.out.println(fechaStr);

				}


			}

			if(command.equals("cancelarCab")){

				estadoTxt.setText("");
				idClienteTxt.setText("");
				idVendedorTxt.setText("");
				clienteBox.setSelectedIndex(0);
				vendedorBox.setSelectedIndex(0);

			}

			if(command.equals("aceptarDet")) {

				if(!idProductoTxt.getText().equals("")) {

					objDetPedido.setIdProducto(Integer.parseInt(idProductoTxt.getText()));

				}
				else {

					valido = false;

				}

				if(!tipoProductoBox.getSelectedItem().toString().equals("")) {

					objTipoProducto.setDescripcionTipPro(tipoProductoBox.getSelectedItem().toString());

				}
				else {

					valido = false;

				}

				if(!productoBox.getSelectedItem().toString().equals("")) {

					objProducto.setDescripcionPro(productoBox.getSelectedItem().toString());

				}
				else {

					valido = false;

				}

				if(!cantidadTxt.getText().equals("")) {

					objDetPedido.setCantidadDetPed(Integer.parseInt(cantidadTxt.getText()));

				}
				else {

					valido = false;

				}

				if(!precioVentaTxt.getText().equals("")) {

					objDetPedido.setPrecioVtaDetPed(Double.parseDouble(precioVentaTxt.getText()));

				}
				else {

					valido = false;

				}

				int comprometidoTmp = hmComprometidoPro.get(Integer.parseInt(idProductoTxt.getText()));
				comprometidoTmp = comprometidoTmp + objDetPedido.getCantidadDetPed();

				if(comprometidoTmp > saldo) {

					valido = false;
					JOptionPane.showMessageDialog(mainFrame, "Ha exedido el saldo Disponible", "Atencion", 3);				

				}

				if(valido) {

					hmComprometidoPro.put(Integer.parseInt(idProductoTxt.getText()), comprometidoTmp);
					listObjDetPed.add(objDetPedido);
					modelo.addRow(new Object[] {objDetPedido.getIdProducto(), objTipoProducto.getDescripcionTipPro(), objProducto.getDescripcionPro(), objDetPedido.getCantidadDetPed(), objDetPedido.getPrecioVtaDetPed()});
					tabla.removeAll();
					tabla.setModel(modelo);
					
					//Calculos
					int cantidad = 0;
					double precio = 0;
					
					precio = (double) tabla.getValueAt(tabla.getRowCount() - 1, 4);
					cantidad = (int) tabla.getValueAt(tabla.getRowCount() - 1, 3);
					
					subtotal += precio * cantidad;
					
					disponibleTxt.setText(Integer.toString(saldo - comprometidoTmp));
					productoBox.setSelectedIndex(0);
					tipoProductoBox.setSelectedIndex(0);
					cantidadTxt.setText("");
					idProductoTxt.setText("");
					subTotalTxt.setText(Double.toString(subtotal));
					totalTxt.setText(Double.toString(subtotal + (subtotal * 0.12)));

				}
			}

			if(command.equals("cancelarDet")) {

				idProductoTxt.setText("");
				disponibleTxt.setText("");
				tipoProductoBox.setSelectedIndex(0);
				productoBox.setSelectedIndex(0);
				cantidadTxt.setText("");
				precioVentaTxt.setText("");

			}

			if(command.equals("guardar")) {

				if(!aceptarCab.isEnabled()) {
					obtenerUltimoNumPed();
					insertarUltimoNumPed();
					consultarIDCabPedido();
					insertarDetPedido();
					//System.exit(0);
					
				}
			}

		}

	}

	public void centrarFrame(JFrame framePrincipal){

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - framePrincipal.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - framePrincipal.getHeight()) / 2);
		framePrincipal.setLocation(x, y);

	}


	public static void main(String[] args) {

		new RegistrarPedidoGUI();

	}

}