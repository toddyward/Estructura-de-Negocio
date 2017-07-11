package mysql;

import java.sql.*;
import javax.swing.*;

public class MysqlConnection {

	private Connection con;

	public MysqlConnection() {

		String url;
		String servidor = "localhost";
		String base = "ventas2017a"; 
		String usuario = "root";
		String pass = "";

		try{
			//Register the JDBC driver for MySQL.
			Class.forName("com.mysql.jdbc.Driver");

			url ="jdbc:mysql://" + servidor + "/"+ base;

			url+="?connectTimeout=7000&socketTimeout=7000";
			DriverManager.setLoginTimeout(1);
			con =DriverManager.getConnection(url,usuario, pass);

		}catch(Exception e ) {

			String 	message="No se ha podido establecer la conexion con la base de datos";
			JOptionPane.showMessageDialog(new JFrame(), message);

		}		
	}

	public ResultSet consulta (String queryBusqueda){

		Statement comando;
		ResultSet resultado=null;		

		try{

			comando = con.createStatement();	
			resultado=comando.executeQuery(queryBusqueda);	

		}catch( SQLException e ) {				

			e.getSQLState();				
			String message="La consulta ejecutada fue: " + queryBusqueda + "Error de Mysql: " + e.getMessage() + "Error codigo: " + e.getErrorCode();
			System.out.println(queryBusqueda);
			JOptionPane.showMessageDialog(new JFrame(), message);

		}

		return resultado;
	}

	public boolean siguiente (ResultSet rs){

		try { 	

			return rs.next();

		}catch(SQLException error ){

			return false;

		} 

	}

	public void insertar (String insert){

		Statement comando;

		try{

			comando = con.createStatement();	
			comando.executeUpdate(insert);	

		}catch( SQLException error ) {

			String message=null;
			message="Error de Mysql: " + error.getMessage() + "Error codigo: " + error.getErrorCode();
			JOptionPane.showMessageDialog(new JFrame(),message);

		}//end catch		

	}

	public boolean insertarB (String insert){

		Statement comando;

		try{

			comando = con.createStatement();	
			comando.executeUpdate(insert);
			return true;

		}catch( SQLException error ) {

			return false;

		}	

	}

	public String getString (ResultSet datos, String columna){

		try{

			return datos.getString(columna);

		}catch (SQLException error){

			String message="Error de Mysql: " + error.getMessage() + "Error código: " + error.getErrorCode();
			JOptionPane.showMessageDialog(new JFrame(),message);
			return (null);

		}			
	}

	public java.util.Date obtenerDate (ResultSet datos, String columna){

		try{

			return datos.getDate(columna);

		}catch (SQLException error){

			String message="Error de Mysql:" + error.getMessage() + "Error código: " + error.getErrorCode();
			JOptionPane.showMessageDialog(new JFrame(),message);
			return (null);

		}			
	} 

	public Connection getConeccion(){

		return this.con;

	}
}
