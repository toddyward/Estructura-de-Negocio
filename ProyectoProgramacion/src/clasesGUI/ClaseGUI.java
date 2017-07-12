package clasesGUI;

import mysql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListDataListener;
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

	private JTextField idClienteTxt;
	private JTextField idLugarGeoTxt;
	private JTextField idPersonaTxt;
	private JTextField nombreSubPerTxt;
	private JTextField apellidoSubPerTxt;
	private JTextField cedulaRucPerTxt;
	private JTextField correoCliTxt;
	private JTextField direccionCliTxt;
	private JTextField telefonoCliTxt;

	private String[] provinciasStr;

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

	public ClaseGUI(){

		GUI();

	}

	public void GUI(){

		GridBagConstraints constraints = new GridBagConstraints();
		Dimension dimensionTexto = new Dimension(50, 20);
		Insets idInsets = new Insets(0, 10, 0, 3);
		Insets insetNulo = new Insets(0, 0, 0, 0);

		mainFrame  = new JFrame();
		mainFrame.setSize(1200, 600);
		mainFrame.setLayout(new GridLayout());
		mainFrame.setTitle("Cliente");
		centrarFrame(mainFrame);


		
		//panelID.setPreferredSize(new Dimension(mainFrame.getWidth(), 40));
		//panelID.setBounds(0, 0, mainFrame.getWidth(), 40);

		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel1.setLayout(new GridBagLayout());
		//panel1.setPreferredSize(new Dimension(1198, 500));

		panelID = new JPanel();
		panelID.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelID.setLayout(new GridBagLayout());
		constraints.gridx = 0;
		constraints.gridy = 0;
		//constraints.weightx = 1;
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.LINE_START;
		panel1.add(panelID, constraints);
		
		idCliente = new JLabel();
		idCliente.setText("idCliente");
		//constraints.gridx = 0;
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
		Dimension txtNomDimension = new Dimension(200, 20); //Dimension para la caja de texto nombres, apellidos
		Dimension txtDimension = new Dimension(410, 20); //Dimension para las demas cajas de texto

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

		correoCli = new JLabel();
		correoCli.setText("Correo Electronico");
		correoCli.setFont(subtituloNegrita);
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(correoCli, constraints);

		correoCliTxt = new JTextField();
		correoCliTxt.setPreferredSize(txtDimension);
		constraints.gridy++;
		constraints.insets = insetNulo;
		panel1.add(correoCliTxt, constraints);

		direccionCli = new JLabel();
		direccionCli.setText("Domicillo");
		direccionCli.setFont(subtituloNegrita);
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(direccionCli, constraints);

		direccionCliTxt = new JTextField();
		direccionCliTxt.setPreferredSize(txtDimension);
		constraints.gridy++;
		constraints.insets = insetNulo;
		panel1.add(direccionCliTxt, constraints);

		telefonoCli = new JLabel();
		telefonoCli.setText("Telefono");
		telefonoCli.setFont(subtituloNegrita);
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(telefonoCli, constraints);

		telefonoCliTxt = new JTextField();
		telefonoCliTxt.setPreferredSize(txtDimension);
		constraints.gridy++;
		constraints.insets = insetNulo;
		panel1.add(telefonoCliTxt, constraints);

		lugarResidencia = new JLabel();
		lugarResidencia.setText("Lugar de residencia");
		lugarResidencia.setFont(subtituloNegrita);
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(lugarResidencia, constraints);

		panelLugarGeografico = new JPanel();
		panelLugarGeografico.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelLugarGeografico.setLayout(new GridBagLayout());
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);
		
		GridBagConstraints constraintsLugGeografico = new GridBagConstraints();
		provincias = new JLabel();
		provincias.setText("Provincia:");
		constraintsLugGeografico.gridx = 0;
		constraintsLugGeografico.gridy = 0;
		//constraints.insets = new Insets(5, 0, 0, 0);
		panelLugarGeografico.add(provincias, constraintsLugGeografico);

		/*try {
		
			leerTexto();
		
		} catch (IOException e) {

		}*/
		
		provinciasModel = new DefaultComboBoxModel<String>();
		//provinciasModel.addListDataListener(new ListDataListener());
		consultaInicioProvincias(provinciasModel);
		
		provinciasBox = new JComboBox<String>(/*provinciasStr*/provinciasModel);
		provinciasBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				elementoSeleccionadoProvincia = (String) provinciasBox.getSelectedItem();
				consultaInicioCantones(cantonesModel);
				cantonesBox.setModel(cantonesModel);
				System.out.println(elementoSeleccionadoProvincia);
				
			}
		});
		constraintsLugGeografico.gridy++;
		constraintsLugGeografico.insets = insetNulo;
		panelLugarGeografico.add(provinciasBox, constraintsLugGeografico);

		cantones = new JLabel();
		cantones.setText("Canton:");
		constraintsLugGeografico.gridx++;
		constraintsLugGeografico.gridy--;
		constraintsLugGeografico.insets = new Insets(5, 0, 0, 0);
		panelLugarGeografico.add(cantones, constraintsLugGeografico);

		cantonesBox = new JComboBox<String>(cantonesModel);
		cantonesBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				elementoSeleccionadoCanton = (String) cantonesBox.getSelectedItem();
				//consultaInicioCantones(cantonesModel);
				consultaInicioParroquias(parroquiasModel);
				parroquiasBox.setModel(parroquiasModel);
				System.out.println(elementoSeleccionadoCanton);
				
			}
		});
		constraintsLugGeografico.gridy++;
		constraintsLugGeografico.insets = insetNulo;
		panelLugarGeografico.add(cantonesBox,  constraintsLugGeografico);

		parroquias = new JLabel();
		parroquias.setText("Parroquias:");
		constraintsLugGeografico.gridx++;
		constraintsLugGeografico.gridy--;
		constraintsLugGeografico.insets = new Insets(5, 0, 0, 0);
		panelLugarGeografico.add(parroquias, constraintsLugGeografico);

		parroquiasBox = new JComboBox<String>();
		constraintsLugGeografico.gridy++;
		constraintsLugGeografico.insets = insetNulo;
		panelLugarGeografico.add(parroquiasBox, constraintsLugGeografico);

		panel1.add(panelLugarGeografico, constraints);
		
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
		tablaDeDatos.setDefaultEditor(Object.class, null);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.insets = new Insets(30, 0, 10, 0);
		scrollTabla = new JScrollPane(tablaDeDatos);
		scrollTabla.setPreferredSize(new Dimension(1000, 100));
		panel1.add(scrollTabla, constraints);
		
		agregar = new JButton();
		agregar.setText("Agregar");
		agregar.addActionListener(new ButtonClickListener());
		agregar.setActionCommand("Agregar");
		constraints.gridy++;
		constraints.anchor = GridBagConstraints.LINE_END;
		
		panel1.add(agregar, constraints);

		cancelar = new JButton();
		cancelar.setText("Cancelar");
		cancelar.addActionListener(new ButtonClickListener());
		cancelar.setActionCommand("Cancelar");
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.CENTER;
		panel1.add(cancelar, constraints);

		salir = new JButton();
		salir.setText("Salir");
		salir.addActionListener(new ButtonClickListener());
		salir.setActionCommand("Salir");
		salir.setActionCommand("Salir");
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.CENTER;
		panel1.add(salir, constraints);

		//constraints.gridx = 0;
		//constraints.gridy = 0;
		//constraints.weightx = 1;
		//constraints.weighty = 1;
		//constraints.fill = GridBagConstraints.BOTH;
		//constraints.anchor = GridBagConstraints.CENTER;
		//mainFrame.add(panelID, constraints);

		constraints.gridy++;
		mainFrame.add(panel1, constraints);

		mainFrame.setVisible(true);

	}

	private class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String comando = e.getActionCommand(); 

			if(comando.equals("Agregar")) {

				String insertarPersona, insertarCliente;
				String nombreApellidoStr, cedulaRucPerStr, correoCliStr, direccionCliStr, telefonoCliStr;

				nombreApellidoStr = nombreSubPerTxt.getText() + " " +apellidoSubPerTxt.getText();
				cedulaRucPerStr = cedulaRucPerTxt.getText();
				correoCliStr = correoCliTxt.getText();
				direccionCliStr = direccionCliTxt.getText();
				telefonoCliStr = telefonoCliTxt.getText();

				insertarPersona = "INSERT INTO persona (nombrePer, cedulaRUCPer) VALUES (" + "'" +nombreApellidoStr + "'" + "," + "'" +cedulaRucPerStr + "'" + ")";
				System.out.println(insertarPersona);

				cnxCliente.insertarDatos(insertarPersona);
				
				insertarCliente = "INSERT INTO cliente (correoCli, direccionCli, telefonoCli, idLugarGeo, idPersona) VALUES (" + "'" + correoCliStr + "'" + "," + "'" + direccionCliStr + "'" + "," + "'" + telefonoCliStr + "'" + "," + "1" + "," +"LAST_INSERT_ID()" + ")";
				System.out.println(insertarCliente);
				
				cnxCliente.insertarDatos(insertarCliente);
				
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

	public void leerTexto() throws IOException{

		File directory = new File("psudoBD/Provincias.cvs");
		System.out.println(directory.getAbsolutePath());
		String path = directory.getAbsolutePath().toString();

		FileReader fr = new FileReader(path);

		BufferedReader texto = new BufferedReader(fr);

		int numLineas = 23, i;

		provinciasStr = new String[numLineas];

		System.out.println("Provincias:\n");
		for(i = 0; i < numLineas; i++){
			provinciasStr[i] = texto.readLine();
			System.out.println(provinciasStr[i]);
		}

		texto.close();

	}

	public void consultaInicio(DefaultTableModel model) {
		
		
		String query = "SELECT cliente.idCliente, cliente.idPersona, cliente.idLugarGeo, persona.nombrePer, persona.cedulaRUCPer, cliente.correoCli, cliente.direccionCli, cliente.telefonoCli FROM cliente, persona, lugar_geo WHERE cliente.idPersona=persona.idPersona AND cliente.idLugarGeo=lugar_geo.idLugarGeo ORDER BY persona.nombrePer";
		java.sql.ResultSet result = cnxCliente.consulta(query);

		try {

			while(result.next()) {

				int idClienteQry = result.getInt("idCliente");
				int idPersonaQry = result.getInt("idPersona");
				int idLugarGeoQry = result.getInt("idLugarGeo");
				String nombrePerQry = result.getString("nombrePer");
				String cedulaRUCPerQry = result.getString("cedulaRUCPer");
				String correoCliQry = result.getString("correoCli");
				String direccionCliQry = result.getString("direccionCli");
				String telefonoCliQry = result.getString("telefonoCli");
				model.addRow(new Object[] {idClienteQry, idPersonaQry, idLugarGeoQry, nombrePerQry, cedulaRUCPerQry, correoCliQry, direccionCliQry, telefonoCliQry});

			}

		}catch(SQLException error){

			System.out.println(error);

		}
		
	}
	
	public void consultaInicioProvincias(DefaultComboBoxModel<String> provinciasModel) {
		
		String query = "SELECT * FROM lugar_geo ORDER BY descripcionLugGeo";
		String codigoLugGeoQry;
		java.sql.ResultSet result = cnxCliente.consulta(query);
		
		try {
			
			while(result.next()) {
				
				codigoLugGeoQry = result.getString("codigoLugGeo");
				
				if(codigoLugGeoQry.length() == 2) {
					
					String descripcionLugGeoQry = result.getString("descripcionLugGeo");
					provinciasModel.addElement(descripcionLugGeoQry);
				
				}
				
			}
			
		}catch(SQLException error) {
			
			System.out.println(error);
			
		}
		
	}
	
	public void consultaInicioCantones(DefaultComboBoxModel<String> cantonesModel) {
		
		String query = "SELECT * FROM lugar_geo ORDER BY descripcionLugGeo";
		String queryProvincias = "SELECT lugar_geo.codigoLugGeo FROM lugar_geo WHERE lugar_geo.descripcionLugGeo='" + elementoSeleccionadoProvincia + "'";
		String codigoLugGeoQry, codigoLugGeoQryProvincias = "";
		java.sql.ResultSet result = cnxCliente.consulta(query);
		java.sql.ResultSet resultProvincias = cnxCliente.consulta(queryProvincias);
		
		cantonesModel.removeAllElements();
		
		try {
			
			while(resultProvincias.next()) {
				
				codigoLugGeoQryProvincias = resultProvincias.getString("codigoLugGeo");
				
				
			}
			
		}catch(SQLException error) {
			
			System.out.println(error);
			
		}
		
		System.out.println("Consulta provincial: " + codigoLugGeoQryProvincias);
		
		try {
			
			while(result.next()) {
				
				codigoLugGeoQry = result.getString("codigoLugGeo");
				
				System.out.println("Consulta cantonal: " + codigoLugGeoQry);
				if(codigoLugGeoQry.substring(0, 2).equals(codigoLugGeoQryProvincias ) && codigoLugGeoQry.length() == 4) { //Falta realizarr esto 
					
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
		String codigoLugGeoQry, codigoLugGeoQryCantones = "";
		java.sql.ResultSet result = cnxCliente.consulta(query);
		java.sql.ResultSet resultCantones = cnxCliente.consulta(queryCantones);
		
		parroquiasModel.removeAllElements();
		
		try {
			
			while(resultCantones.next()) {
				
				codigoLugGeoQryCantones = resultCantones.getString("codigoLugGeo");
				
				
			}
			
		}catch(SQLException error) {
			
			System.out.println(error);
			
		}
		
		System.out.println("Consulta cantonal: (codigoLugGeoQryCantones)" + codigoLugGeoQryCantones);
		
		try {
			
			while(result.next()) {
				
				codigoLugGeoQry = result.getString("codigoLugGeo");
				
				System.out.println("Consulta cantonal: " + codigoLugGeoQry);
				if(codigoLugGeoQry.length() != 2)
				if(codigoLugGeoQry.substring(0, 4).equals(codigoLugGeoQryCantones ) && codigoLugGeoQry.length() == 8) { 
					
					String descripcionLugGeoQry = result.getString("descripcionLugGeo");
					parroquiasModel.addElement(descripcionLugGeoQry);
					
				}
				
			}
			
		}catch(SQLException error) {
			
			System.out.println(error);
			
			
		}
		
	}
	
}
