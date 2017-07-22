package clasesGUI;

import javax.swing.*;

public class InicioGUI {
		
		public InicioGUI() {
			
			
		}
	
		public JMenuBar crearMenuSuperior(){
			
			JMenuBar menubar;
			JMenu menu;
			JMenuItem nuevo;
			
			//Crear la barra de menu
			menubar = new JMenuBar();
			
			//Crear el menu
			menu = new JMenu();
			menu.setText("Archivo");
			menubar.add(menu);
			
			//Agregar un item al menu
			nuevo = new JMenu();
			nuevo.setText("Nuevo");
			menu.add(nuevo);
			
			return menubar;
			
		}
		
		public static void menuPrincipal() {
			
			JFrame menuPrincipal;
			
			menuPrincipal = new JFrame();
			
			//Agregar barra de menu al frame
			InicioGUI GUI = new InicioGUI();
			menuPrincipal.setJMenuBar(GUI.crearMenuSuperior());
			
			menuPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
			menuPrincipal.setVisible(true);
			
		}
		
		public static void main (String[] args)	{
			
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
				
					menuPrincipal();
					
				}
			});
			
		}
}
