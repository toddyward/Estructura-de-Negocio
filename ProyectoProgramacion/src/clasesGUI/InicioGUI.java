package clasesGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InicioGUI {

	private JFrame mainFrame;
	
	private JMenuBar menubarra;
	
	private JMenu menu;
	private JMenu menu2;
	
	private JMenuItem registrarClienteItm;
	private JMenuItem registrarCliente2Itm;
	private JMenuItem registrarLugarGeograficoItm;
	private JMenuItem registrarPedidoItm;
	private JMenuItem registrarReportes;

	public InicioGUI() {

		showInicioGUI();

	}

	public void showInicioGUI() {

		mainFrame = new JFrame();
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setTitle("Estructura de negocio");
		mainFrame.setLayout(new GridBagLayout());

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
		
		registrarPedidoItm = new JMenuItem("Registrar Pedido");
		registrarPedidoItm.addActionListener(new ButtonClickListener());
		registrarPedidoItm.setActionCommand("registrarPedido");
		menu.add(registrarPedidoItm);
		
		menu2 = new JMenu();
		menu2.setText("Reportes");
		menubarra.add(menu2);
		
		registrarReportes = new JMenuItem("Iniciar un Reporte");
		registrarReportes.addActionListener(new ButtonClickListener());
		registrarReportes.setActionCommand("registrarReportes");
		menu2.add(registrarReportes);

		mainFrame.setVisible(true);

	}

	private class ButtonClickListener implements ActionListener{

		public void actionPerformed (ActionEvent e) {

			String command = e.getActionCommand();

			if(command.equals("registrarClienteItm")) 
				new RegistrarClienteGUI();

			else if(command.equals("registrarCliente2Itm"))
				new RegistrarClienteGUIver2ArrayList();

			else if(command.equals("registrarLugarGeograficoItm"))
				new RegistrarLugarGeograficoGUI();
			
			else if(command.equals("registrarPedido"))
				new RegistrarPedidoGUI();
			
			else if(command.equals("registrarReportes"))
				new RegistrarReportesGUI();

		}

	}


	public static void main (String[] args)	{

		new InicioGUI();

	}
}