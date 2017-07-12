package clasesGUI;

import mysql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.*;

import com.mysql.jdbc.ResultSet;



public class ClaseGUI {

	private JFrame mainFrame;
	
	private JPanel panel1;
	//private JPanel panelID;
	
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
	private String[] tituloTbl = {"Nombre y Apellido", "Cedula/Ruc", "Correo", "Domicillo", "Telefono"};
	
	private JComboBox <String> provinciasBox;
	private JComboBox <String> cantonesBox;
	private JComboBox <String> parroquiasBox;
	
	private DefaultTableModel model; //Modelar la tabla
	
	private JTable tablaDeDatos;

	private JButton agregar;
	private JButton cancelar;
	private JButton salir;
	
	private JScrollPane scrollTabla;
	private ConexionMySql cnxCliente;
	
	public ClaseGUI(){
		
		GUI();
		
	}
	
	public void GUI(){
		
		GridBagConstraints constraints = new GridBagConstraints();
		Dimension dimensionTexto = new Dimension(50, 20);
		Insets idInsets = new Insets(0, 10, 0, 3);
		Insets insetNulo = new Insets(0, 0, 0, 0);
		
		mainFrame  = new JFrame();
		mainFrame.setSize(1200, 800);
		mainFrame.setLayout(new GridLayout());
		mainFrame.setTitle("Cliente");
		centrarFrame(mainFrame);

		
		//panelID = new JPanel();
		//panelID.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		//panelID.setLayout(new GridBagLayout());
		//panelID.setPreferredSize(new Dimension(mainFrame.getWidth(), 40));
		//panelID.setBounds(0, 0, mainFrame.getWidth(), 40);
		
		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel1.setLayout(new GridBagLayout());
		//panel1.setPreferredSize(new Dimension(1198, 500));
		
		idCliente = new JLabel();
		idCliente.setText("idCliente");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.insets = idInsets;
		panel1.add(idCliente, constraints);
		
		idClienteTxt = new JTextField();
		idClienteTxt.setEditable(false);
		idClienteTxt.setPreferredSize(dimensionTexto);
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = insetNulo;
		panel1.add(idClienteTxt, constraints);
		
		idLugarGeo = new JLabel();
		idLugarGeo.setText("idLugarGeo");
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.insets = idInsets;
		panel1.add(idLugarGeo, constraints);
		
		idLugarGeoTxt = new JTextField();
		idLugarGeoTxt.setEditable(false);
		idLugarGeoTxt.setPreferredSize(dimensionTexto);
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = insetNulo;
		panel1.add(idLugarGeoTxt, constraints);
		
		idPersona = new JLabel();
		idPersona.setText("idPersona");
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.insets = idInsets;
		panel1.add(idPersona, constraints);
		
		idPersonaTxt = new JTextField();
		idPersonaTxt.setEditable(false);
		idPersonaTxt.setPreferredSize(dimensionTexto);
		constraints.gridx++;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.insets = insetNulo;
		panel1.add(idPersonaTxt, constraints);
		
		Font tituloNegrita = new Font("Arial", Font.BOLD, 16);
		Font subtituloNegrita = new Font("Arial", Font.BOLD, 14);
		Dimension txtNomDimension = new Dimension(200, 20); //Dimension para la caja de texto nombres, apellidos
		Dimension txtDimension = new Dimension(410, 20); //Dimension para las demas cajas de texto
		
		titulo = new JLabel();
		titulo.setFont(tituloNegrita);
		titulo.setText("Datos del cliente:");
		constraints.gridx = 0;
		constraints.gridy++;
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
		constraints.insets = new Insets(10, -200, 0, 0);
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
		constraints.insets = new Insets(0, -200, 0, 0);
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
		
		provincias = new JLabel();
		provincias.setText("Provincia:");
		constraints.gridy++;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(provincias, constraints);
		
		try {
			leerTexto();
		} catch (IOException e) {
		}
				
		provinciasBox = new JComboBox<String>(provinciasStr);
		constraints.gridy++;
		constraints.insets = insetNulo;
		panel1.add(provinciasBox, constraints);
		
		cantones = new JLabel();
		cantones.setText("Canton:");
		constraints.gridx++;
		constraints.gridy--;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(cantones, constraints);
		
		cantonesBox = new JComboBox<String>();
		constraints.gridy++;
		constraints.insets = insetNulo;
		panel1.add(cantonesBox,  constraints);
		
		parroquias = new JLabel();
		parroquias.setText("Parroquias:");
		constraints.gridx++;
		constraints.gridy--;
		constraints.insets = new Insets(5, 0, 0, 0);
		panel1.add(parroquias, constraints);
		
		parroquiasBox = new JComboBox<String>();
		constraints.gridy++;
		constraints.insets = insetNulo;
		panel1.add(parroquiasBox, constraints);
		
		model = new DefaultTableModel(); //Paso para empezar a cargar datos desde mysql https://stackoverflow.com/questions/27815400/retrieving-data-from-jdbc-database-into-jtable
		model.addColumn("idCliente");
		model.addColumn("idPersona");
		model.addColumn("idLugarGeo");
		model.addColumn("Nombre");
		model.addColumn("Cedula/Ruc");
		model.addColumn("Correo");
		model.addColumn("Direccion");
		model.addColumn("Telefono");
		
		cnxCliente = new ConexionMySql();
		
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
		
		tablaDeDatos = new JTable(model);
		//tablaDeDatos.setAutoCreateColumnsFromModel(true);
		//tablaDeDatos.setHoriz
		//tablaDeDatos.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		//tablaDeDatos.setEnabled(false);
		//tablaDeDatos.setPreferredSize(new Dimension(400, 50));
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.insets = new Insets(30, 0, 10, 0);
		scrollTabla = new JScrollPane(tablaDeDatos);
		scrollTabla.setPreferredSize(new Dimension(800, 100));
		panel1.add(scrollTabla, constraints);
		
		agregar = new JButton();
		agregar.setText("Agregar");
		agregar.addActionListener(new ButtonClickListener());
		agregar.setActionCommand("Agregar");
		constraints.gridy++;
		panel1.add(agregar, constraints);
		
		cancelar = new JButton();
		cancelar.setText("Cancelar");
		cancelar.addActionListener(new ButtonClickListener());
		cancelar.setActionCommand("Cancelar");
		constraints.gridx++;
		panel1.add(cancelar, constraints);
		
		salir = new JButton();
		salir.setText("Salir");
		salir.addActionListener(new ButtonClickListener());
		salir.setActionCommand("Salir");
		salir.setActionCommand("Salir");
		constraints.gridx++;
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
	        	 
	        	 String insertarPersona, insertarLugar_geo;
	        	 String nombreApellidoStr, cedulaRucPerStr, correoCliStr, direccionCliStr, telefonoCliStr;
	        	 
	        	 nombreApellidoStr = nombreSubPerTxt.getText() + " " +apellidoSubPerTxt.getText();
	        	 cedulaRucPerStr = cedulaRucPerTxt.getText();
	        	 correoCliStr = correoCliTxt.getText();
	        	 direccionCliStr = direccionCliTxt.getText();
	        	 telefonoCliStr = telefonoCliTxt.getText();
	        	 
	        	 insertarPersona = "INSERT INTO PERSONA (nombrePer, cedulaRUCPer) VALUES (" + "'" +nombreApellidoStr + "'" + "," + "'" +cedulaRucPerStr + "'" + ")";
	        	 System.out.println(insertarPersona);
	        	 
	        	// insertarLugar_geo = "INSERT INTO lugar_geo ("
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
	
}
