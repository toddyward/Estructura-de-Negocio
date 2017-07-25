package clasesGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import mysql.ConexionMySql;
import clasesBean.*;

import java.util.Calendar;

public class RegistrarPedidoGUI {

	JFrame mainFrame;

	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	
	JLabel idCabeceraPedido;
	JLabel idCliente;
	JLabel idVendedor;
	JLabel idTipoCobro;
	JLabel numPedido;
	JLabel estado;
	JLabel fechaPedido;
	JLabel cliente;
	JLabel vendedor;
	JLabel idProducto;
	JLabel tipoProducto;
	JLabel producto;
	JLabel cantidad;
	JLabel precioVenta;
	JLabel tipoCobro; 
	JLabel saldo;

	JTextField idCabeceraPedidoTxt;
	JTextField idClienteTxt;
	JTextField idVendedorTxt;
	JTextField idTipoCobroTxt;
	JTextField numPedidoTxt;
	JTextField estadoTxt;
	JTextField idProductoTxt;
	JTextField cantidadTxt;
	JTextField precioVentaTxt;
	JTextField saldoTxt;
	
	JComboBox<String> clienteBox;
	JComboBox<String> vendedorBox;
	JComboBox<String> tipoProductoBox;
	JComboBox<String> productoBox;
	JComboBox<String> tipoCobroBox;

	JButton aceptarCab;
	JButton cancelarCab;
	JButton aceptarDet;
	JButton cancelarDet;
	JButton guardar;
	JButton cancelar;

	JTable tabla;
	JScrollPane tablaScrl;

	DefaultTableModel modelo;
	DefaultComboBoxModel<String> modeloClienteBox;
	DefaultComboBoxModel<String> modeloVendedorBox;
	DefaultComboBoxModel<String> modeloCobroBox;
	DefaultComboBoxModel<String> modelotipoProductoBox;
	DefaultComboBoxModel<String> modeloProductoBox;

	ConexionMySql conexion = new ConexionMySql();
	PersonaBean objPersona = new PersonaBean();
	ClienteBean objCliente = new ClienteBean();
	VendedorBean objVendedor = new VendedorBean();
	Cap_PedidoBean objCabPedidoBean = new Cap_PedidoBean();
	Tipo_CobroBean objCobro = new Tipo_CobroBean();
	Tipo_ProductoBean objTipoProducto = new Tipo_ProductoBean();
	ProductoBean objProducto = new ProductoBean();
	Det_PedidoBean objDetPedido = new Det_PedidoBean();
	
	private int intSaldo = 0;
	private int intComprometido = 0;

	public RegistrarPedidoGUI() {

		showGUI();

	}

	public void consultarCliente(DefaultComboBoxModel<String> modeloClienteBox) {

		String query = "SELECT persona.nombrePer FROM persona, cliente WHERE persona.idPersona=cliente.idPersona";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Clientes: " + query);

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

		String query = "SELECT persona.nombrePer FROM persona, vendedor WHERE persona.idPersona=vendedor.idPersona";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Vendedores: " + query);

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

	public void consultarCobro(DefaultComboBoxModel<String> modeloCobroBox) {

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

	}

	public void consultarTipoProducto(DefaultComboBoxModel<String> modeloTipoProductoBox) {

		String query = "SELECT descripcionTipPro FROM  tipo_producto";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Tipo Producto: " + query);

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
			objCliente.setIdCliente(result.getInt("idCliente"));

		}catch(SQLException error) {

			System.out.println(error);

		}

		idClienteTxt.setText(Integer.toString(objCliente.getIdCliente()));

	}

	public void consultarIDVendedor(String vendedorSeleccionado) {

		String query = "SELECT vendedor.idVendedor FROM persona, vendedor WHERE persona.idPersona=vendedor.idPersona AND persona.nombrePer='" + vendedorSeleccionado + "'";
		java.sql.ResultSet result = conexion.consulta(query);
		System.out.println("Vendedor seleccionado: " + query);

		try {

			result.next();
			objVendedor.setIdVendedor(result.getInt("idVendedor"));

		}catch(SQLException error) {

			System.out.println(error);

		}

		idVendedorTxt.setText(Integer.toString(objVendedor.getIdVendedor()));

	}

	public void consultarIDCobro(String cobroSeleccionado) {

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

	}

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
		consultarSaldo(productoSeleccionado);

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


		String insertarNumPed = "INSERT INTO ventas2017a.cab_pedido (estadoPed, fechaPed, numeroFacturaPed, numeroPed, idTipoCobro, idCliente, idVendedor) VALUES ('" + objCabPedidoBean.getEstadoPed() + "', CURDATE(), '', '" + objCabPedidoBean.getNumeroPed() + "', '" + objCobro.getIdTipoCobro() + "', '" + objCliente.getIdCliente() + "', '" + objVendedor.getIdVendedor() + "')";
		System.out.println("Insertar cabecera: " + insertarNumPed);

		conexion.insertar(insertarNumPed);

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
		
		intSaldo = objProducto.getSaldoPro() - intComprometido;
		System.out.println("Saldo: " + intSaldo);
		saldoTxt.setText(Integer.toString(intSaldo));
		
	}

	public void showGUI() {

		mainFrame = new JFrame("Registrar Pedido");
		mainFrame.setSize(800, 400);
		mainFrame.setLayout(new GridLayout(3, 1));
		centrarFrame(mainFrame);

		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel1.setLayout(new GridBagLayout());
		mainFrame.add(panel1);

		GridBagConstraints constraintsPanel1 = new GridBagConstraints();
		constraintsPanel1.anchor = GridBagConstraints.LINE_START;
		Dimension idTxtDimen = new Dimension(50, 20);
		Dimension TxtDimen = new Dimension(75, 20);

		idCabeceraPedido = new JLabel("idCabPedido");
		constraintsPanel1.gridx = 0;
		constraintsPanel1.gridy = 0;
		panel1.add(idCabeceraPedido, constraintsPanel1);

		idCabeceraPedidoTxt = new JTextField();
		idCabeceraPedidoTxt.setPreferredSize(idTxtDimen);
		idCabeceraPedidoTxt.setEnabled(false);
		constraintsPanel1.gridx++;
		panel1.add(idCabeceraPedidoTxt, constraintsPanel1);

		idCliente = new JLabel("idCliente");
		constraintsPanel1.gridx++;
		panel1.add(idCliente, constraintsPanel1);

		idClienteTxt = new JTextField();
		idClienteTxt.setPreferredSize(idTxtDimen);
		idClienteTxt.setEnabled(false);
		constraintsPanel1.gridx++;
		panel1.add(idClienteTxt, constraintsPanel1);

		idVendedor = new JLabel("idVendedor");
		constraintsPanel1.gridx++;
		panel1.add(idVendedor, constraintsPanel1);

		idVendedorTxt = new JTextField();
		idVendedorTxt.setPreferredSize(idTxtDimen);
		idVendedorTxt.setEnabled(false);
		constraintsPanel1.gridx++;
		panel1.add(idVendedorTxt, constraintsPanel1);

		idTipoCobro = new JLabel("idTipoCobro");
		constraintsPanel1.gridx++;
		panel1.add(idTipoCobro, constraintsPanel1);

		idTipoCobroTxt = new JTextField();
		idTipoCobroTxt.setPreferredSize(idTxtDimen);
		idTipoCobroTxt.setEnabled(false);
		constraintsPanel1.gridx++;
		panel1.add(idTipoCobroTxt, constraintsPanel1);

		numPedido = new JLabel("Numero de Pedido");
		constraintsPanel1.gridx = 0;
		constraintsPanel1.gridy++;
		panel1.add(numPedido, constraintsPanel1);

		numPedidoTxt = new JTextField();
		numPedidoTxt.setPreferredSize(TxtDimen);
		numPedidoTxt.setEnabled(false);
		constraintsPanel1.gridx++;
		panel1.add(numPedidoTxt, constraintsPanel1);

		estado = new JLabel("Estado");
		constraintsPanel1.gridx++;
		panel1.add(estado, constraintsPanel1);

		estadoTxt = new JTextField();
		estadoTxt.setPreferredSize(TxtDimen);
		constraintsPanel1.gridx++;
		panel1.add(estadoTxt, constraintsPanel1);

		fechaPedido = new JLabel("Fecha");
		constraintsPanel1.gridx++;
		panel1.add(fechaPedido, constraintsPanel1);

		cliente = new JLabel("Cliente");
		constraintsPanel1.gridx = 0;
		constraintsPanel1.gridy++;
		panel1.add(cliente, constraintsPanel1);

		modeloClienteBox = new DefaultComboBoxModel<String>();
		clienteBox = new JComboBox<String>();
		consultarCliente(modeloClienteBox);
		clienteBox.addActionListener(new ButtonClickListener());
		clienteBox.setActionCommand("clienteSeleccionado");
		constraintsPanel1.gridx++;
		panel1.add(clienteBox, constraintsPanel1);

		vendedor = new JLabel("Vendedor");
		constraintsPanel1.gridx++;
		panel1.add(vendedor, constraintsPanel1);

		modeloVendedorBox = new DefaultComboBoxModel<String>();
		vendedorBox = new JComboBox<String>();
		consultarVendedor(modeloVendedorBox);
		vendedorBox.addActionListener(new ButtonClickListener());
		vendedorBox.setActionCommand("vendedorSeleccionado");
		constraintsPanel1.gridx++;
		panel1.add(vendedorBox, constraintsPanel1);

		tipoCobro = new JLabel("Cobro");
		constraintsPanel1.gridx++;
		panel1.add(tipoCobro, constraintsPanel1);

		modeloCobroBox = new DefaultComboBoxModel<String>();
		tipoCobroBox = new JComboBox<String>();
		consultarCobro(modeloCobroBox);
		tipoCobroBox.addActionListener(new ButtonClickListener());
		tipoCobroBox.setActionCommand("tipoCobroSeleccionado");
		constraintsPanel1.gridx++;
		panel1.add(tipoCobroBox, constraintsPanel1);

		aceptarCab = new JButton("Aceptar");
		aceptarCab.addActionListener(new ButtonClickListener());
		aceptarCab.setActionCommand("aceptarCab");
		constraintsPanel1.gridx = 0;
		constraintsPanel1.gridy++;
		panel1.add(aceptarCab, constraintsPanel1);

		cancelarCab = new JButton("Cancelar");
		constraintsPanel1.gridx++;
		panel1.add(cancelarCab, constraintsPanel1);


		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel2.setLayout(new GridBagLayout());
		mainFrame.add(panel2);

		GridBagConstraints constraintsPanel2 = new GridBagConstraints();
		constraintsPanel2.anchor = GridBagConstraints.LINE_START;

		idProducto = new JLabel("idProducto");
		constraintsPanel2.gridx = 0;
		constraintsPanel2.gridy = 0;
		panel2.add(idProducto, constraintsPanel2);

		idProductoTxt = new JTextField();
		idProductoTxt.setPreferredSize(idTxtDimen);
		idProductoTxt.setEnabled(false);
		constraintsPanel2.gridx++;
		panel2.add(idProductoTxt, constraintsPanel2);
		
		saldo = new JLabel("Saldo");
		constraintsPanel2.gridx++;
		panel2.add(saldo, constraintsPanel2);
		
		saldoTxt = new JTextField();
		saldoTxt.setPreferredSize(TxtDimen);
		saldoTxt.setEnabled(false);
		constraintsPanel2.gridx++;
		panel2.add(saldoTxt, constraintsPanel2);

		tipoProducto = new JLabel("Tipo");
		constraintsPanel2.gridx = 0;
		constraintsPanel2.gridy++;
		panel2.add(tipoProducto, constraintsPanel2);

		modelotipoProductoBox = new DefaultComboBoxModel<String>();
		tipoProductoBox = new JComboBox<String>();
		consultarTipoProducto(modelotipoProductoBox);
		tipoProductoBox.addActionListener(new ButtonClickListener());
		tipoProductoBox.setActionCommand("tipoProductoSeleccionado");
		constraintsPanel2.gridx++;
		panel2.add(tipoProductoBox, constraintsPanel2);

		producto = new JLabel("Producto");
		constraintsPanel2.gridx++;
		panel2.add(producto, constraintsPanel2);

		modeloProductoBox = new DefaultComboBoxModel<String>();
		productoBox = new JComboBox<String>();
		productoBox.addActionListener(new ButtonClickListener());
		productoBox.setActionCommand("productoSeleccionado");
		constraintsPanel2.gridx++;
		panel2.add(productoBox, constraintsPanel2);

		cantidad = new JLabel("Cantidad");
		constraintsPanel2.gridx++;
		panel2.add(cantidad, constraintsPanel2);

		cantidadTxt = new JTextField();
		cantidadTxt.setPreferredSize(TxtDimen);
		constraintsPanel2.gridx++;
		panel2.add(cantidadTxt, constraintsPanel2);

		precioVenta = new JLabel("Precio");
		constraintsPanel2.gridx++;
		panel2.add(precioVenta, constraintsPanel2);

		precioVentaTxt = new JTextField();
		precioVentaTxt.setPreferredSize(TxtDimen);
		constraintsPanel2.gridx++;
		panel2.add(precioVentaTxt, constraintsPanel2);

		aceptarDet = new JButton("Aceptar");
		aceptarDet.addActionListener(new ButtonClickListener());
		aceptarDet.setActionCommand("aceptarDet");
		constraintsPanel2.gridx = 0;
		constraintsPanel2.gridy++;
		panel2.add(aceptarDet, constraintsPanel2);

		cancelarDet = new JButton("Cancelar");
		constraintsPanel2.gridx++;
		panel2.add(cancelarDet, constraintsPanel2);


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
		panel4.setLayout(new GridLayout(1, 2));
		panel3.add(panel4);
		
		guardar = new JButton("Guardar");
		guardar.addActionListener(new ButtonClickListener());
		guardar.setActionCommand("guardar");
		panel4.add(guardar);
		
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ButtonClickListener());
		cancelar.setActionCommand("cancelar");
		panel4.add(cancelar);

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

			if(command.equals("tipoCobroSeleccionado"))
				consultarIDCobro(modeloCobroBox.getSelectedItem().toString());

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
				obtenerUltimoNumPed();

				if(!idClienteTxt.getText().equals("")) {

					objCliente.setIdCliente(Integer.parseInt(idClienteTxt.getText()));

				}
				else {

					valido = false;

				}

				if(!idVendedorTxt.getText().equals("")) {

					objVendedor.setIdVendedor(Integer.parseInt(idVendedorTxt.getText()));

				}
				else {

					valido = false;

				}

				if(!idTipoCobro.getText().equals("")) {

					objCobro.setIdTipoCobro(Integer.parseInt(idTipoCobroTxt.getText()));

				}
				else {

					valido = false;
					aceptarCab.setEnabled(false);

				}



			}

			if(command.equals("aceptarDet")) {

				if(!idProductoTxt.getText().equals("")) {

					objProducto.setIdProducto(Integer.parseInt(idProductoTxt.getText()));

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
					intComprometido = objDetPedido.getCantidadDetPed();
					intSaldo -= intComprometido;
					saldoTxt.setText(Integer.toString(intSaldo));
					System.out.println("Saldo: " + intSaldo);
					
					if(intSaldo < 0) {
						
						intSaldo += intComprometido;
						JOptionPane.showMessageDialog(mainFrame, "Exedio el saldo permitido", "Saldo Exedido", 3);
						valido = false;
						
					}
						

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

				if(valido) {

					modelo.addRow(new Object[] {objProducto.getIdProducto(), objTipoProducto.getDescripcionTipPro(), objProducto.getDescripcionPro(), objDetPedido.getCantidadDetPed(), objDetPedido.getPrecioVtaDetPed()});
					cantidadTxt.setText("");
					idProductoTxt.setText("");

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
