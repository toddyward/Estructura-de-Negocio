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
	private JMenuItem registrarPedidoItm;

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
		
		registrarPedidoItm = new JMenuItem("Registrar Pedido");
		registrarPedidoItm.addActionListener(new ButtonClickListener());
		registrarPedidoItm.setActionCommand("registrarPedido");
		menu.add(registrarPedidoItm);

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

		}

	}


	public static void main (String[] args)	{

		new InicioGUI();

	}
}