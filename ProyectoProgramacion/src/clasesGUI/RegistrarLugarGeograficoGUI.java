package clasesGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import mysql.ConexionMySql;
import clasesBean.*;

public class RegistrarLugarGeograficoGUI {

	private JFrame mainFrame;

	private JPanel panel1;
	private JPanel panelID;
	private JPanel panelRadioButton;
	private JPanel panelButton;

	private JLabel codigoLugarGeografico;
	private JLabel descripcionLugarGeografico;
	private JLabel idLugarGeo;
	private JLabel idLugarGeoPadre;
	private JLabel datosGeograficos;
	private JLabel tipo;
	private JLabel codigoAlrt;
	private JLabel descripcionAlrt;

	private JTextField idLugarGeoTxt;
	private JTextField idLugarGeoPadreTxt;
	private JTextField codigoLugarGeograficoTxt;
	private JTextField descripcionLugarGeograficoTxt;

	private JRadioButton provinciaRadioButton;
	private JRadioButton cantonRadioButton;
	private JRadioButton parroquiaRadioButton;

	private JButton agregar;
	private JButton editar;
	private JButton eliminar;

	private JTable tabla;

	private DefaultTableModel modeloTabla;

	private ConexionMySql conexion = new ConexionMySql();

	private Lugar_GeoBean objLugarGeoBean = new Lugar_GeoBean();

	private JScrollPane tablaScroll;

	public RegistrarLugarGeograficoGUI() {

		mostrarGUI();

	}

	public void mostrarGUI() {

		mainFrame = new JFrame();
		mainFrame.setSize(new Dimension(600, 400));
		mainFrame.setTitle("Registrar lugar geografico");
		centrarFrame(mainFrame);
		mainFrame.setLayout(new GridLayout());


		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel1.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		panelID = new JPanel();
		panelID.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelID.setLayout(new GridBagLayout());
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel1.add(panelID, constraints);

		GridBagConstraints constraintsID = new GridBagConstraints();

		idLugarGeo = new JLabel();
		idLugarGeo.setText("idLugarGeo");
		constraintsID.gridx = 0;
		constraintsID.gridy = 0;
		panelID.add(idLugarGeo, constraintsID);

		Dimension idTxtDimension = new Dimension(50, 20); //Dimension para IDTextField

		idLugarGeoTxt = new JTextField();
		idLugarGeoTxt.setPreferredSize(idTxtDimension);
		idLugarGeoTxt.setEnabled(false);
		constraintsID.gridx++;
		constraintsID.insets = new Insets(0, 5, 0, 10);
		panelID.add(idLugarGeoTxt, constraintsID);

		idLugarGeoPadre = new JLabel();
		idLugarGeoPadre.setText("idLugarGeoPadre");
		constraintsID.gridx++;
		constraintsID.insets = new Insets(0, 0, 0, 0);
		panelID.add(idLugarGeoPadre, constraintsID);

		idLugarGeoPadreTxt = new JTextField();
		idLugarGeoPadreTxt.setPreferredSize(idTxtDimension);
		idLugarGeoPadreTxt.setEnabled(false);
		constraintsID.gridx++;
		constraintsID.insets = new Insets(0, 5, 0, 10);
		panelID.add(idLugarGeoPadreTxt, constraintsID);

		Font titulo = new Font("Arial", Font.BOLD, 16);	//Fuente para el titulo
		Font subtitulo = new Font("Arial", Font.BOLD, 14);	//FUente para los subtitulos

		tipo = new JLabel();
		tipo.setText("Tipo:");
		tipo.setFont(titulo);
		constraints.gridy++;
		constraints.insets = new Insets(10, 0, 5, 0);
		constraints.anchor = GridBagConstraints.LINE_START;
		panel1.add(tipo, constraints);

		panelRadioButton = new JPanel();
		panelRadioButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelRadioButton.setLayout(new GridLayout(1,3));
		constraints.gridy++;
		constraints.insets = new Insets(0, 0, 0, 0);
		panel1.add(panelRadioButton, constraints);

		provinciaRadioButton = new JRadioButton();
		provinciaRadioButton.setText("Provincia");
		provinciaRadioButton.setActionCommand("provincia");
		//provinciaRadioButton.addActionListener(radiobutton);
		provinciaRadioButton.setSelected(true);
		panelRadioButton.add(provinciaRadioButton);

		cantonRadioButton = new JRadioButton();
		cantonRadioButton.setText("Canton");
		cantonRadioButton.setActionCommand("canton");
		//cantonRadioButton.addActionListener(radiobutton);
		panelRadioButton.add(cantonRadioButton);

		parroquiaRadioButton = new JRadioButton();
		parroquiaRadioButton.setText("Parroquia");
		parroquiaRadioButton.setActionCommand("parroquia");
		//parroquiaRadioButton.addActionListener(radiobutton);
		panelRadioButton.add(parroquiaRadioButton);

		//Agregar al grupo de botones
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(provinciaRadioButton);
		grupo.add(cantonRadioButton);
		grupo.add(parroquiaRadioButton);

		datosGeograficos = new JLabel();
		datosGeograficos.setText("Datos Geograficos:");
		datosGeograficos.setFont(titulo);
		constraints.gridy++;
		constraints.insets = new Insets(10, 0, 5, 0);
		panel1.add(datosGeograficos, constraints);

		codigoLugarGeografico = new JLabel();
		codigoLugarGeografico.setText("Codigo");
		codigoLugarGeografico.setFont(subtitulo);
		constraints.gridy++;
		constraints.insets = new Insets(0, 0, 0, 0);
		panel1.add(codigoLugarGeografico, constraints);

		Dimension TxtDimension = new Dimension(100, 20);	//Dimension para TextField

		codigoLugarGeograficoTxt = new JTextField();
		codigoLugarGeograficoTxt.setPreferredSize(TxtDimension);
		constraints.gridy++;
		panel1.add(codigoLugarGeograficoTxt, constraints);

		codigoAlrt = new JLabel();
		codigoAlrt.setForeground(Color.RED);
		codigoAlrt.setFont(subtitulo);
		constraints.gridx++;
		constraints.insets = new Insets(0, -210, 0, 0);
		panel1.add(codigoAlrt, constraints);

		descripcionLugarGeografico = new JLabel();
		descripcionLugarGeografico.setText("Descripcion");
		descripcionLugarGeografico.setFont(subtitulo);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.insets = new Insets(0, 0, 0, 0);
		panel1.add(descripcionLugarGeografico, constraints);

		descripcionLugarGeograficoTxt = new JTextField();
		descripcionLugarGeograficoTxt.setPreferredSize(TxtDimension);
		constraints.gridy++;
		panel1.add(descripcionLugarGeograficoTxt, constraints);

		descripcionAlrt = new JLabel();
		descripcionAlrt.setForeground(Color.RED);
		descripcionAlrt.setFont(subtitulo);
		constraints.gridx++;
		constraints.insets = new Insets(0, -210, 0, 0);
		panel1.add(descripcionAlrt, constraints);

		tabla = new JTable();
		modelarTabla(tabla);
		tabla.setDefaultEditor(Object.class, null);	//Se puede seleccionar pero no editar
		tablaScroll = new JScrollPane(tabla);
		tablaScroll.setPreferredSize(new Dimension(400, 70));
		constraints.gridy++;
		constraints.insets = new Insets(20, -300, 0, 0);
		constraints.anchor = GridBagConstraints.CENTER;
		panel1.add(tablaScroll, constraints);

		panelButton = new JPanel();

		panelButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panelButton.setLayout(new GridLayout(1, 3));
		constraints.gridx++;
		constraints.gridy++;
		constraints.insets = new Insets(15, -300, 0, 0);
		constraints.anchor = GridBagConstraints.LINE_START;
		panel1.add(panelButton, constraints);

		agregar = new JButton();
		agregar.setText("Agregar");
		agregar.addActionListener(new ButtonClickListener());
		agregar.setActionCommand(agregar.getText());
		panelButton.add(agregar);

		editar = new JButton();
		editar.setText("Editar");
		editar.addActionListener(new ButtonClickListener());
		editar.setActionCommand(editar.getText());
		panelButton.add(editar);

		eliminar = new JButton();
		eliminar.setText("Eliminar");
		eliminar.addActionListener(new ButtonClickListener());
		eliminar.setActionCommand(eliminar.getText());
		panelButton.add(eliminar);

		mainFrame.add(panel1);
		mainFrame.setVisible(true);
	}

	public void modelarTabla(JTable tabla) {

		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("idLugarGeo");
		modeloTabla.addColumn("idLugarGeoPadre");
		modeloTabla.addColumn("Codigo");
		modeloTabla.addColumn("Descripcion");
		modeloTabla.addColumn("Tipo");
		consultaInicio(modeloTabla);

	}

	public void consultaInicio(DefaultTableModel modeloTabla) {

		String query = "SELECT a.idLugarGeo, a.idLugarGeoPadre, a.codigoLugGeo, a.descripcionLugGeo FROM lugar_geo a";

		java.sql.ResultSet result = conexion.consulta(query);

		System.out.println("Consulta: " + query);

		try {

			while(result.next()) {

				objLugarGeoBean.setIdLugarGeo(result.getInt("idLugarGeo"));
				objLugarGeoBean.setIdLugarGeo1(result.getInt("idLugarGeoPadre"));
				objLugarGeoBean.setCodigoLugarGeo(result.getString("codigoLugGeo"));
				objLugarGeoBean.setDescripcionLugGeo(result.getString("descripcionLugGeo"));

				if(objLugarGeoBean.getCodigoLugarGeo().length() == 2) 
					modeloTabla.addRow(new Object[] {objLugarGeoBean.getIdLugarGeo(), objLugarGeoBean.getIdLugarGeo1(), objLugarGeoBean.getCodigoLugarGeo(), objLugarGeoBean.getDescripcionLugGeo(), "Provincia"});		
				else if(objLugarGeoBean.getCodigoLugarGeo().length() == 6)
					modeloTabla.addRow(new Object[] {objLugarGeoBean.getIdLugarGeo(), objLugarGeoBean.getIdLugarGeo1(), objLugarGeoBean.getCodigoLugarGeo(), objLugarGeoBean.getDescripcionLugGeo(), "Canton"});		
				else if(objLugarGeoBean.getCodigoLugarGeo().length() == 8)
					modeloTabla.addRow(new Object[] {objLugarGeoBean.getIdLugarGeo(), objLugarGeoBean.getIdLugarGeo1(), objLugarGeoBean.getCodigoLugarGeo(), objLugarGeoBean.getDescripcionLugGeo(), "Parroquia"});		
				else 
					System.out.println("No se ejecuto");

			}

		}catch(SQLException error) {

			System.out.println(error);

		}


		tabla.setModel(modeloTabla);

	}

	private class ButtonClickListener implements ActionListener{

		public void actionPerformed (ActionEvent e) {

			String command = e.getActionCommand();
			Boolean validar = true;

			if(command.equals(agregar.getText())) {

				System.out.println("Ejecutando boton Agregar");

				if(provinciaRadioButton.isSelected()) {

					if(!codigoLugarGeograficoTxt.getText().equals("")) {

						codigoAlrt.setText("");

						if(codigoLugarGeograficoTxt.getText().length() == 2)
							objLugarGeoBean.setCodigoLugarGeo(codigoLugarGeograficoTxt.getText());
						else {
							codigoAlrt.setText("Provincia solo 2 digitos");
							validar = false;
							
						}
					}
					else {
						codigoAlrt.setText("Informacion Requerida");
						validar = false;
						
					}
					if(!descripcionLugarGeograficoTxt.getText().equals("")) {

						descripcionAlrt.setText("");
						objLugarGeoBean.setDescripcionLugGeo(descripcionLugarGeograficoTxt.getText());

					}
					else {
						descripcionAlrt.setText("Informacion Requerida");
						validar = false;
						
					}
					
					if(validar) {
						
						String agregarLugarGeografico = "INSERT INTO ventas2017a.lugar_geo (codigoLugGeo, descripcionLugGeo) VALUES ('" + objLugarGeoBean.getCodigoLugarGeo() + "', '"+ objLugarGeoBean.getDescripcionLugGeo() +"')";
						System.out.println("Ingresar: "+ agregarLugarGeografico);
						conexion.insertar(agregarLugarGeografico);
						
						while(modeloTabla.getRowCount() > 0)
							modeloTabla.removeRow(0);
						
						consultaInicio(modeloTabla);
						
					}
					
				}

			}
			
			if(command.equals(eliminar.getText())) {
				
				System.out.println("Ejecutando boton Eliminar");
				System.out.println("Fila seleccionada: " + tabla.getSelectedRow());
				
				if(tabla.getSelectedRow() > -1) {
					
					objLugarGeoBean.setIdLugarGeo(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()));
					
					int opcion = JOptionPane.showConfirmDialog(mainFrame, "Esta seguro que desea eliminar: " + tabla.getValueAt(tabla.getSelectedRow(), 3).toString(), "Eliminar elemento", 1);
					
					if(opcion == JOptionPane.YES_OPTION) {
						
						String eliminar = "DELETE FROM lugar_geo WHERE lugar_geo.idLugarGeo=" + objLugarGeoBean.getIdLugarGeo();
						System.out.println("Eliminar: " + eliminar);
						conexion.insertar(eliminar);
						
						while(modeloTabla.getRowCount() > 0)
							modeloTabla.removeRow(0);
						
						consultaInicio(modeloTabla);
						
					}
					
				}
				else {
					
					JOptionPane.showMessageDialog(mainFrame, "Seleccione un elemento de la Tabla", "Error", 2);
					
				}
				
			}

		}


	}

	public void centrarFrame(JFrame mainFrame) {

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = ((int) (dimension.getWidth() - mainFrame.getWidth()) / 2);
		int y = ((int) (dimension.getHeight() - mainFrame.getHeight()) / 2);

		mainFrame.setLocation(x, y);

	}

	public static void main(String[] args) {

		new RegistrarLugarGeograficoGUI();

	}

}
