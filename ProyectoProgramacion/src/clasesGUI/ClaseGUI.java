package clasesGUI;

import clasesBean.*;

import mysql.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.*;

//import com.mysql.jdbc.ResultSet;

public class ClaseGUI {

	private JFrame mainFrame;

	private JPanel panel1;
	private JPanel panelID;
	private JPanel panelLugarGeografico;
	private JPanel panelBotones;

	private JLabel idCliente;
	private JLabel idLugarGeo;
	private JLabel idPersona;
	private JLabel titulo;
	private JLabel nombreSubPer;
	private JLabel apellidoSubPer;
	private JLabel cedulaRucPer;
	private JLabel correoCli;
	private JLabel direccionCli;
	private JLabel telefonoCli;
	private JLabel provincias;
	private JLabel cantones;
	private JLabel parroquias;
	private JLabel lugarResidencia;
	private JLabel nombreLblErr;
	private JLabel cedulaRucLblErr;
	private JLabel correoLblErr;
	private JLabel direccionLblErr;
	private JLabel telefonoLblErr;
	private JLabel lugarResidenciaLblErr;

	private JTextField idClienteTxt;
	private JTextField idLugarGeoTxt;
	private JTextField idPersonaTxt;
	private JTextField nombreSubPerTxt;
	private JTextField apellidoSubPerTxt;
	private JTextField cedulaRucPerTxt;
	private JTextField correoCliTxt;
	private JTextField direccionCliTxt;
	private JTextField telefonoCliTxt;



	private String elementoSeleccionadoProvincia;
	private String elementoSeleccionadoCanton;

	private JComboBox <String> provinciasBox;
	private JComboBox <String> cantonesBox;
	private JComboBox <String> parroquiasBox;

	private DefaultTableModel model; //Modelar la tabla

	private DefaultComboBoxModel<String> provinciasModel;
	private DefaultComboBoxModel<String> cantonesModel  = new DefaultComboBoxModel<String>();
	private DefaultComboBoxModel<String> parroquiasModel = new DefaultComboBoxModel<String>();

	private JTable tablaDeDatos;

	private JButton agregar;
	private JButton cancelar;
	private JButton salir;

	private JScrollPane scrollTabla;
	private ConexionMySql cnxCliente = new ConexionMySql();

	private ClienteBean objClienteBean = new ClienteBean();
	private Lugar_GeoBean objLugarGeoBean = new Lugar_GeoBean();


	public ClaseGUI(){

		GUI();

	}

	public void GUI(){

		GridBagConstraints constraints = new GridBagConstraints();
		Dimension dimensionTexto = new Dimension(50, 20);	//Dimension dedicado solamente a los textField's de ID
		Insets idInsets = new Insets(0, 10, 0, 3);	//Inset dedicado solamente a los id ubicados en la parte superior, dentro de panelID
		Insets insetNulo = new Insets(0, 0, 0, 0);	//Inset nulo
		Insets alertas = new Insets(0, -575, 0, 0);	//Dimension para las alertas en rojo al lado derecho

		mainFrame  = new JFrame();
		mainFrame.setSize(1200, 600);
		mainFrame.setLayout(new GridLayout());
		mainFrame.setTitle("Cliente");
		mainFrame.setMinimumSize(new Dimension(1200, 600));
		centrarFrame(mainFrame);

		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel1.setLayout(new GridBagLayout());

		panelID = new JPanel();
		panelID.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelID.setLayout(new GridBagLayout());
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_START;
		panel1.add(panelID, constraints);

		idCliente = new JLabel();
		idCliente.setText("idCliente");
		constraints.weightx = 0;
		constraints.fill = 0;
		constraints.gridy++;
		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.insets = idInsets;
		panelID.add(idCliente, constraints);

		idClienteTxt = new JTextField();
		idClienteTxt.setEditable(false);
		idClienteTxt.setPreferredSize(dimensionTexto);
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = insetNulo;
		panelID.add(idClienteTxt, constraints);

		idLugarGeo = new JLabel();
		idLugarGeo.setText("idLugarGeo");
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.insets = idInsets;
		panelID.add(idLugarGeo, constraints);

		idLugarGeoTxt = new JTextField();
		idLugarGeoTxt.setEditable(false);
		idLugarGeoTxt.setPreferredSize(dimensionTexto);
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = insetNulo;
		panelID.add(idLugarGeoTxt, constraints);

		idPersona = new JLabel();
		idPersona.setText("idPersona");
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.insets = idInsets;
		panelID.add(idPersona, constraints);

		idPersonaTxt = new JTextField();
		idPersonaTxt.setEditable(false);
		idPersonaTxt.setPreferredSize(dimensionTexto);
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = insetNulo;
		panelID.add(idPersonaTxt, constraints);

		Font tituloNegrita = new Font("Arial", Font.BOLD, 16);
		Font subtituloNegrita = new Font("Arial", Font.BOLD, 14);
		Dimension txtNomDimension = new Dimension(200, 20);	//Dimension para la caja de texto nombres, apellidos
		Dimension txtDimension = new Dimension(410, 20);	//Dimension para las demas cajas de texto (Correo, direccion ...)
		Dimension comboBoxDimension = new Dimension(200, 25);	//Dimension standar para los 3 ComboBox

		titulo = new JLabel();
		titulo.setFont(tituloNegrita);
		titulo.setText("Datos del cliente:");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = insetNulo;
		panel1.add(titulo, constraints);

		nombreSubPer = new JLabel();
		nombreSubPer.setText("Nombres");
		nombreSubPer.setFont(subtituloNegrita);
		constraints.gridy++;
		constraints.insets = new Insets(10, 0, 0, 0);
		panel1.add(nombreSubPer, constraints);

		apellidoSubPer = new JLabel();
		apellidoSubPer.setText("Apellidos");
		apellidoSubPer.setFont(subtituloNegrita);
		constraints.gridx++;
		constraints.insets = new Insets(10, -790, 0, 0);
		panel1.add(apellidoSubPer, constraints);

		nombreSubPerTxt = new JTextField();
		nombreSubPerTxt.setPreferredSize(txtNomDimension);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.insets = insetNulo;
		panel1.add(nombreSubPerTxt, constraints);

		apellidoSubPerTxt = new JTextField();
		apellidoSubPerTxt.setPreferredSize(txtNomDimension);
		constraints.gridx++;
		constraints.insets = new Insets(0, -790, 0, 0);
		panel1.add(apellidoSubPerTxt, constraints);

		nombreLblErr = new JLabel();
		nombreLblErr.setForeground(Color.RED);
		nombreLblErr.setFont(subtituloNegrita);
		constraints.insets = alertas;
		panel1.add(nombreLblErr, constraints);

		cedulaRucPer = new JLabel();
		cedulaRucPer.setText("Cedula/Ruc");
		cedulaRucPer.setFont(subtituloNegrita);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(cedulaRucPer, constraints);

		cedulaRucPerTxt = new JTextField();
		cedulaRucPerTxt.setPreferredSize(txtDimension);
		constraints.gridy++;
		constraints.insets = insetNulo;
		panel1.add(cedulaRucPerTxt, constraints);

		cedulaRucLblErr = new JLabel();
		cedulaRucLblErr.setForeground(Color.RED);
		cedulaRucLblErr.setFont(subtituloNegrita);
		constraints.gridx++;
		constraints.insets = alertas;
		panel1.add(cedulaRucLblErr, constraints);

		correoCli = new JLabel();
		correoCli.setText("Correo Electronico");
		correoCli.setFont(subtituloNegrita);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(correoCli, constraints);

		correoCliTxt = new JTextField();
		correoCliTxt.setPreferredSize(txtDimension);
		constraints.gridy++;
		constraints.insets = insetNulo;
		panel1.add(correoCliTxt, constraints);

		correoLblErr = new JLabel();
		correoLblErr.setForeground(Color.RED);
		correoLblErr.setFont(subtituloNegrita);
		constraints.gridx++;
		constraints.insets = alertas;
		panel1.add(correoLblErr, constraints);

		direccionCli = new JLabel();
		direccionCli.setText("Domicillo");
		direccionCli.setFont(subtituloNegrita);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(direccionCli, constraints);

		direccionCliTxt = new JTextField();
		direccionCliTxt.setPreferredSize(txtDimension);
		constraints.gridy++;
		
		constraints.insets = insetNulo;
		panel1.add(direccionCliTxt, constraints);

		direccionLblErr = new JLabel();
		direccionLblErr.setForeground(Color.RED);
		direccionLblErr.setFont(subtituloNegrita);
		constraints.gridx++;
		constraints.insets = alertas;
		panel1.add(direccionLblErr, constraints);

		telefonoCli = new JLabel();
		telefonoCli.setText("Telefono");
		telefonoCli.setFont(subtituloNegrita);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(telefonoCli, constraints);

		telefonoCliTxt = new JTextField();
		telefonoCliTxt.setPreferredSize(txtDimension);
		constraints.gridy++;
		constraints.insets = insetNulo;
		panel1.add(telefonoCliTxt, constraints);

		telefonoLblErr = new JLabel();
		telefonoLblErr.setForeground(Color.RED);
		telefonoLblErr.setFont(subtituloNegrita);
		constraints.gridx++;
		constraints.insets = alertas;
		panel1.add(telefonoLblErr, constraints);

		lugarResidencia = new JLabel();
		lugarResidencia.setText("Lugar de residencia");
		lugarResidencia.setFont(subtituloNegrita);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(lugarResidencia, constraints);

		panelLugarGeografico = new JPanel();
		panelLugarGeografico.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelLugarGeografico.setLayout(new GridBagLayout());
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);

		GridBagConstraints constraintsLugGeografico = new GridBagConstraints();	//Constraints para usarlos dentro de panelLugarGeografico
		provincias = new JLabel();
		provincias.setText("Provincia:");
		constraintsLugGeografico.gridx = 0;
		constraintsLugGeografico.gridy = 0;
		panelLugarGeografico.add(provincias, constraintsLugGeografico);

		provinciasModel = new DefaultComboBoxModel<String>();
		consultaInicioProvincias(provinciasModel);	//Cargar las provincias de manera predeterminada

		provinciasBox = new JComboBox<String>(provinciasModel);
		provinciasBox.setPreferredSize(comboBoxDimension);
		provinciasBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				elementoSeleccionadoProvincia = (String) provinciasBox.getSelectedItem();
				consultaInicioCantones(cantonesModel);	//Cargar los cantones de manera predeterminada
				cantonesBox.setModel(cantonesModel);	//Ejecutar el modelaje en el combobox de Cantones

			}
		});
		constraintsLugGeografico.gridy++;
		constraintsLugGeografico.insets = insetNulo;
		panelLugarGeografico.add(provinciasBox, constraintsLugGeografico);

		cantones = new JLabel();
		cantones.setText("Canton:");
		constraintsLugGeografico.gridx++;
		constraintsLugGeografico.gridy--;
		panelLugarGeografico.add(cantones, constraintsLugGeografico);

		cantonesBox = new JComboBox<String>(cantonesModel);
		cantonesBox.setPreferredSize(comboBoxDimension);
		cantonesBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				elementoSeleccionadoCanton = (String) cantonesBox.getSelectedItem();
				consultaInicioParroquias(parroquiasModel);	//Cargar las parroquias de manera predeterminada
				parroquiasBox.setModel(parroquiasModel);	//Ejecutar el modelaje en el combobox de Parroquias

			}
		});
		constraintsLugGeografico.gridy++;
		constraintsLugGeografico.insets = insetNulo;
		panelLugarGeografico.add(cantonesBox,  constraintsLugGeografico);

		parroquias = new JLabel();
		parroquias.setText("Parroquias:");
		constraintsLugGeografico.gridx++;
		constraintsLugGeografico.gridy--;
		panelLugarGeografico.add(parroquias, constraintsLugGeografico);

		parroquiasBox = new JComboBox<String>();
		parroquiasBox.setPreferredSize(comboBoxDimension);
		constraintsLugGeografico.gridy++;
		constraintsLugGeografico.insets = insetNulo;
		panelLugarGeografico.add(parroquiasBox, constraintsLugGeografico);

		panel1.add(panelLugarGeografico, constraints);

		lugarResidenciaLblErr = new JLabel();
		lugarResidenciaLblErr.setForeground(Color.RED);
		lugarResidenciaLblErr.setFont(subtituloNegrita);
		constraints.gridx++;
		constraints.insets = new Insets(5, -375, 0, 0);	//Inset para ubicar correctamente el label de alerta (Independiente de los otros)
		panel1.add(lugarResidenciaLblErr, constraints);

		model = new DefaultTableModel(); //Paso para empezar a cargar datos desde mysql https://stackoverflow.com/questions/27815400/retrieving-data-from-jdbc-database-into-jtable

		model.addColumn("idCliente");
		model.addColumn("idPersona");
		model.addColumn("idLugarGeo");
		model.addColumn("Nombre");
		model.addColumn("Cedula/Ruc");
		model.addColumn("Correo");
		model.addColumn("Direccion");
		model.addColumn("Telefono");

		consultaInicio(model); //Modela la tabla con los datos actuales de la base de datos mysql

		tablaDeDatos = new JTable(model);
		tablaDeDatos.setDefaultEditor(Object.class, null);	//Se puede seleccionar pero no editar
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.insets = new Insets(30, 0, 10, 0);
		scrollTabla = new JScrollPane(tablaDeDatos);
		scrollTabla.setPreferredSize(new Dimension(1000, 100));
		panel1.add(scrollTabla, constraints);

		panelBotones = new JPanel();
		panelBotones.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		constraints.gridy++;
		constraints.anchor = GridBagConstraints.LINE_END;
		
		GridBagConstraints botonesConstraints = new GridBagConstraints();
		
		agregar = new JButton();
		agregar.setText("Agregar");
		agregar.addActionListener(new ButtonClickListener());
		agregar.setActionCommand("Agregar");
		botonesConstraints.gridx = 0;
		botonesConstraints.gridy = 0;
		botonesConstraints.anchor = GridBagConstraints.LINE_END;
		panelBotones.add(agregar, botonesConstraints);

		cancelar = new JButton();
		cancelar.setText("Cancelar");
		cancelar.addActionListener(new ButtonClickListener());
		cancelar.setActionCommand("Cancelar");
		botonesConstraints.gridx++;
		panelBotones.add(cancelar, botonesConstraints);

		salir = new JButton();
		salir.setText("Salir");
		salir.addActionListener(new ButtonClickListener());
		salir.setActionCommand("Salir");
		salir.setActionCommand("Salir");
		botonesConstraints.gridx++;
		panelBotones.add(salir, botonesConstraints);	
		
		
		panel1.add(panelBotones, constraints);
		mainFrame.add(panel1, constraints);

		mainFrame.setVisible(true);

	}

	private class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String comando = e.getActionCommand(); 
			String alerta1 = "Informacion Requerida";
			String regexSoloLet = "^[\\p{L} .'-]+$";
			String regexCorreo = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$"; //Obtenido de: https://stackoverflow.com/questions/42266148/email-validation-regex-java


			Boolean validacion = true;

			if(comando.equals("Agregar")) {

				String insertarPersona, insertarCliente;
				String nombreApellidoStr = null, cedulaRucPerStr = null, correoCliStr = null, direccionCliStr = null, telefonoCliStr = null, idLugarGeoStr = null;

				//Validacion de datos

				if(!(nombreSubPerTxt.getText().equals("") || apellidoSubPerTxt.getText().equals(""))) {
					
					nombreLblErr.setText("");
					if(nombreSubPerTxt.getText().matches(regexSoloLet) && apellidoSubPerTxt.getText().matches(regexSoloLet)) {
						
						nombreLblErr.setText("");
						nombreApellidoStr = nombreSubPerTxt.getText() + " " + apellidoSubPerTxt.getText();
						
					}
					else {

						nombreLblErr.setText("Ingrese un nombre valido");
						validacion = false;

					}

				}
				else {

					nombreLblErr.setText(alerta1);
					validacion = false;

				}

				if(!cedulaRucPerTxt.getText().equals("")) {
					
					//Falta validacion de cedula
					cedulaRucLblErr.setText("");
					cedulaRucPerStr = cedulaRucPerTxt.getText();

				}
				else {

					cedulaRucLblErr.setText(alerta1);
					validacion = false;

				}

				if(!correoCliTxt.getText().equals("")) {
					
					correoLblErr.setText("");
					if(correoCliTxt.getText().matches(regexCorreo)) {
						
						correoLblErr.setText("");
						correoCliStr = correoCliTxt.getText();
						
					}
					else {

						correoLblErr.setText("Ingrese un correo valido");
						validacion = false;

					}

				}
				else {

					correoLblErr.setText(alerta1);
					validacion = false;

				}

				if(!direccionCliTxt.getText().equals("")) {
					
					direccionLblErr.setText("");
					direccionCliStr = direccionCliTxt.getText();
					
				}
				else {

					direccionLblErr.setText(alerta1);
					validacion = false;

				}

				if(!telefonoCliTxt.getText().equals("")) {

					//Falta validar (Autorizacion: Sheila)
					telefonoLblErr.setText("");
					telefonoCliStr = telefonoCliTxt.getText();

				}
				else {

					telefonoLblErr.setText(alerta1);
					validacion = false;

				}

				if(!(provinciasBox.getItemCount() == 0 || cantonesBox.getItemCount() == 0 || parroquiasBox.getItemCount() == 0)) {
					
					lugarResidenciaLblErr.setText("");
					idLugarGeoStr = actualLugarGeoSeleccionado((String) cantonesBox.getSelectedItem(), (String) parroquiasBox.getSelectedItem());
				
				}
				else {
					
					lugarResidenciaLblErr.setText("Seleccione una parroquia");
					validacion = false;
				}

				if(validacion == true) {

					//Ejecutar INSERTS
					insertarPersona = "INSERT INTO persona (nombrePer, cedulaRUCPer) VALUES (" + "'" +nombreApellidoStr + "'" + "," + "'" +cedulaRucPerStr + "'" + ")";
					System.out.println("Comando Insertar Persona: " + insertarPersona);
					cnxCliente.insertar(insertarPersona);

					insertarCliente = "INSERT INTO cliente (correoCli, direccionCli, telefonoCli, idLugarGeo, idPersona) VALUES (" + "'" + correoCliStr + "'" + "," + "'" + direccionCliStr + "'" + "," + "'" + telefonoCliStr + "'" + "," + idLugarGeoStr + "," +"LAST_INSERT_ID()" + ")";
					System.out.println("Comando Insertar Cliente: " + insertarCliente);
					cnxCliente.insertar(insertarCliente);
					
					//Remover las alertas
					nombreLblErr.setText("");
					cedulaRucLblErr.setText("");
					correoLblErr.setText("");
					direccionLblErr.setText("");
					telefonoLblErr.setText("");
					lugarResidenciaLblErr.setText("");
					
					//Inicializar los textFields
					nombreSubPerTxt.setText("");
					apellidoSubPerTxt.setText("");
					cedulaRucPerTxt.setText("");
					correoCliTxt.setText("");
					direccionCliTxt.setText("");
					telefonoCliTxt.setText("");
					

				}
				
				//Remover las filas del modelo para volver a actualizarla
				while(model.getRowCount() > 0)
					model.removeRow(0);

				consultaInicio(model);

			}

		}
	}

	public static void main (String args[]){

		new ClaseGUI();

	}

	public void centrarFrame(JFrame framePrincipal){

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - framePrincipal.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - framePrincipal.getHeight()) / 2);
		framePrincipal.setLocation(x, y);

	}

	public void consultaInicio(DefaultTableModel model) {

		//Ejecutar query
		String query = "SELECT cliente.idCliente, cliente.idPersona, cliente.idLugarGeo, persona.nombrePer, persona.cedulaRUCPer, cliente.correoCli, cliente.direccionCli, cliente.telefonoCli FROM cliente, persona, lugar_geo WHERE cliente.idPersona=persona.idPersona AND cliente.idLugarGeo=lugar_geo.idLugarGeo ORDER BY persona.nombrePer";
		
		java.sql.ResultSet result = cnxCliente.consulta(query);
		
		System.out.println("Consulta Tabla de datos: " + query + "\n");

		try {

			while(result.next()) {

				objClienteBean.setIdCliente(result.getInt("idCliente"));
				objClienteBean.setIdPersona(result.getInt("idPersona"));
				objClienteBean.setIdLugarGeo(result.getInt("idLugarGeo"));
				
				objClienteBean.setNombrePer(result.getString("nombrePer"));
				objClienteBean.setCedulaRucPer(result.getString("cedulaRUCPer"));
				objClienteBean.setCorreoElecCli(result.getString("correoCli"));
				objClienteBean.setDireccionCli(result.getString("direccionCli"));
				objClienteBean.setTelefonoCli(result.getString("telefonoCli"));
				model.addRow(new Object[] {objClienteBean.getIdCliente(), objClienteBean.getIdPersona(), objClienteBean.getIdLugarGeo(), objClienteBean.getNombrePer(), objClienteBean.getCedulaRucPer(), objClienteBean.getCorreoElecCli(), objClienteBean.getDireccionCli(), objClienteBean.getTelefonoCli()});

			}

		}catch(SQLException error){

			System.out.println(error);

		}

	}

	public void consultaInicioProvincias(DefaultComboBoxModel<String> provinciasModel) {

		String query = "SELECT * FROM lugar_geo ORDER BY descripcionLugGeo";
		
		java.sql.ResultSet result = cnxCliente.consulta(query);
		
		System.out.println("Consulta ComboBox Provincias: " + query + "\n");

		try {

			while(result.next()) {

				objLugarGeoBean.setCodigoLugarGeo(result.getString("codigoLugGeo"));

				if(objLugarGeoBean.getCodigoLugarGeo().length() == 2) {

					objLugarGeoBean.setDescripcionLugGeo(result.getString("descripcionLugGeo"));
					provinciasModel.addElement(objLugarGeoBean.getDescripcionLugGeo());

				}

			}

		}catch(SQLException error) {

			System.out.println(error);

		}

	}

	public void consultaInicioCantones(DefaultComboBoxModel<String> cantonesModel) {

		String query = "SELECT * FROM lugar_geo ORDER BY descripcionLugGeo";
		String queryProvincias = "SELECT lugar_geo.codigoLugGeo FROM lugar_geo WHERE lugar_geo.descripcionLugGeo='" + elementoSeleccionadoProvincia + "'";
		String codigoLugGeoQryProvincias = ""; //Necesarias para realizar el siguente query, y modelar la tabla
		
		java.sql.ResultSet result = cnxCliente.consulta(query);
		java.sql.ResultSet resultProvincias = cnxCliente.consulta(queryProvincias);
		
		System.out.println("Consulta ComboBox Cantones: " + query);
		System.out.println("Consulta ComboBox Cantones (Provincia) " + queryProvincias + "\n");

		cantonesModel.removeAllElements();

		try {

			while(resultProvincias.next()) {

				codigoLugGeoQryProvincias = resultProvincias.getString("codigoLugGeo");


			}

		}catch(SQLException error) {

			System.out.println(error);

		}

		try {

			while(result.next()) {

				objLugarGeoBean.setCodigoLugarGeo(result.getString("codigoLugGeo"));

				if(objLugarGeoBean.getCodigoLugarGeo().substring(0, 2).equals(codigoLugGeoQryProvincias ) && objLugarGeoBean.getCodigoLugarGeo().length() == 6) { //Evitar cargar rovincias, y al mismo tiempo seleccionar las del mismo grupo de provincias

					String descripcionLugGeoQry = result.getString("descripcionLugGeo");
					cantonesModel.addElement(descripcionLugGeoQry);

				}

			}

		}catch(SQLException error) {

			System.out.println(error);


		}

	}

	public void consultaInicioParroquias(DefaultComboBoxModel<String> parroquiasModel) {

		String query = "SELECT * FROM lugar_geo ORDER BY descripcionLugGeo";
		String queryCantones = "SELECT lugar_geo.codigoLugGeo FROM lugar_geo WHERE lugar_geo.descripcionLugGeo='" + elementoSeleccionadoCanton + "'";
		String codigoLugGeoQryCantones = "";
		
		java.sql.ResultSet result = cnxCliente.consulta(query);
		java.sql.ResultSet resultCantones = cnxCliente.consulta(queryCantones);

		System.out.println("Consulta ComboBox Parroquias: " + query);
		System.out.println("Consulta ComboBox Parroquias (Cantones) " + queryCantones + "\n");
		
		parroquiasModel.removeAllElements(); //Remover todos los elementos, en caso de seleccionar otro canton o provincia

		try {

			while(resultCantones.next()) {

				codigoLugGeoQryCantones = resultCantones.getString("codigoLugGeo");

			}

		}catch(SQLException error) {

			System.out.println(error);

		}

		try {

			while(result.next()) {

				objLugarGeoBean.setCodigoLugarGeo(result.getString("codigoLugGeo"));

				if(objLugarGeoBean.getCodigoLugarGeo().length() != 2)
					if(objLugarGeoBean.getCodigoLugarGeo().substring(0, 6).equals(codigoLugGeoQryCantones ) && objLugarGeoBean.getCodigoLugarGeo().length() == 8) { //Control para cargar solo parroquias del mismo canton

						String descripcionLugGeoQry = result.getString("descripcionLugGeo");
						parroquiasModel.addElement(descripcionLugGeoQry);

					}

			}

		}catch(SQLException error) {

			System.out.println(error);

		}

	}
	
	public String actualLugarGeoSeleccionado(String canton, String parroquia) {
		
		String query = "SELECT a.idLugarGeo FROM lugar_geo a, lugar_geo b WHERE a.descripcionLugGeo='" + parroquia + "' AND b.descripcionLugGeo='" + canton + "' AND a.idLugarGeoPadre=b.idLugarGeo";
		String idLugarGeo = null;
		
		java.sql.ResultSet result = cnxCliente.consulta(query);
		
		System.out.println("Consulta Item seleccionado: " + query + "\n");
		
		try {
			
			while(result.next()) {
				
				idLugarGeo = result.getString("idLugarGeo");
				
			}
			
		}catch(SQLException error) {
			
			System.out.println(error);
			
		}
				
		return idLugarGeo;
		
	}

}

