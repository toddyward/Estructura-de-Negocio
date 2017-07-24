package clasesGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import clasesGUI.*;

import javax.swing.*;

public class InicioGUI {
		
		private JFrame mainFrame;
		private JMenuBar menubarra;
		private JMenu menu;
		private JMenuItem registrarClienteItm;
		private JMenuItem registrarCliente2Itm;
		private JMenuItem registrarLugarGeograficoItm;
		
		private JButton registrarCliente;
		private JButton registrarCliente2;
		private JButton registrarLugarGeografico;
		
		
		public InicioGUI() {
			
			showInicioGUI();
			
		}
	
		public void showInicioGUI() {
			
			mainFrame = new JFrame();
			mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			mainFrame.setTitle("Estructura de negocio");
			mainFrame.setLayout(new GridBagLayout());
			
			GridBagConstraints constraints = new GridBagConstraints();
			
			menubarra = new JMenuBar();
			mainFrame.setJMenuBar(menubarra);
			
			menu = new JMenu();
			menu.setText("Archivo");
			menubarra.add(menu);
			
			registrarClienteItm = new JMenuItem("Registrar Cliente V1");
			registrarClienteItm.addActionListener(new ButtonClickListener());
			registrarClienteItm.setActionCommand("registrarClienteItm");
			menu.add(registrarClienteItm);
			
			registrarCliente2Itm = new JMenuItem("Registrar Cliente V2");
			registrarCliente2Itm.addActionListener(new ButtonClickListener());
			registrarCliente2Itm.setActionCommand("registrarCliente2Itm");
			menu.add(registrarCliente2Itm);
			
			registrarLugarGeograficoItm = new JMenuItem("Registrar Lugar Geografico");
			registrarLugarGeograficoItm.addActionListener(new ButtonClickListener());
			registrarLugarGeograficoItm.setActionCommand("registrarLugarGeograficoItm");
			menu.add(registrarLugarGeograficoItm);
			
			
			
			Dimension dimensionBotones = new Dimension(300, 40);
			
			/*registrarCliente = new JButton();
			registrarCliente.setText("Registrar cliente V1");
			registrarCliente.addActionListener(new ButtonClickListener());
			registrarCliente.setActionCommand(registrarCliente.getText());
			registrarCliente.setPreferredSize(dimensionBotones);
			constraints.insets = new Insets(0, 0, 25, 0);
			constraints.gridx = 0;
			constraints.gridy = 0;
			
			mainFrame.add(registrarCliente, constraints);
			
			registrarCliente2 = new JButton();
			registrarCliente2.setText("Registrar cliente V2");
			registrarCliente2.addActionListener(new ButtonClickListener());
			registrarCliente2.setActionCommand(registrarCliente2.getText());
			registrarCliente2.setPreferredSize(dimensionBotones);
			constraints.gridy++;
			mainFrame.add(registrarCliente2, constraints);
			
			registrarLugarGeografico = new JButton();
			registrarLugarGeografico.setText("Registrar Lugar Geografico");
			registrarLugarGeografico.addActionListener(new ButtonClickListener());
			registrarLugarGeografico.setActionCommand(registrarLugarGeografico.getText());
			registrarLugarGeografico.setPreferredSize(dimensionBotones);
			constraints.gridy++;
			mainFrame.add(registrarLugarGeografico, constraints);*/
			
			mainFrame.setVisible(true);
			
		}
		
		private class ButtonClickListener implements ActionListener{
			
			public void actionPerformed (ActionEvent e) {
				
				String command = e.getActionCommand();
				
				/*if(command.equals(registrarCliente.getText())) 
					new RegistrarClienteGUI();
				
				else if(command.equals(registrarCliente2.getText()))
					new RegistrarClienteGUIver2ArrayList();
				
				else if(command.equals(registrarLugarGeografico.getText()))
					new RegistrarLugarGeograficoGUI();*/
				
				if(command.equals("registrarClienteItm")) 
				new RegistrarClienteGUI();
			
			else if(command.equals("registrarCliente2Itm"))
				new RegistrarClienteGUIver2ArrayList();
			
			else if(command.equals("registrarLugarGeograficoItm"))
				new RegistrarLugarGeograficoGUI();
					
			}
			
		}
		
		
		public static void main (String[] args)	{
		
			new InicioGUI();
			
		}
}
