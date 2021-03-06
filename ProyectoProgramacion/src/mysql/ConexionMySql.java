package mysql;

import java.sql.*;
import clasesGUI.RegistrarClienteGUI;

public class ConexionMySql {

	Connection conexion = null;
	Statement stmt = null;

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "jdbc:mysql://";
	String servidor = "localhost/";
	String baseDeDatos = "ventas2017a"; 

	final String USER = "root";
	final String PASSWORD = "";

	public ConexionMySql(){
		
		conexion();
		
	}
	
	public void conexion(){

		DB_URL += servidor + baseDeDatos; 

		try{

			Class.forName(JDBC_DRIVER);
			System.out.println("Conectando a la base de datos");
			conexion = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			System.out.println("Conexion Exitosa");

		}catch(SQLException se){

			se.printStackTrace(); //Manejar errores de JDBC

		}catch(Exception e){

			e.printStackTrace(); //Manejar errores de Class.forName

		}

	}

	public ResultSet consulta(String query){

		ResultSet resultado = null;
		
		try{
			
			stmt = conexion.createStatement();
			resultado = stmt.executeQuery(query);
			
		}catch(SQLException error){
			
			System.out.println(error);
			
		}
		
		return resultado;
		
	}

	public void insertar(String insert){
		
		Statement comando;
		
		try{
			
			comando = conexion.createStatement();
			comando.executeUpdate(insert);
			
		}catch(SQLException error){
			
			System.out.println(error);
			RegistrarClienteGUI.imprimirErrores(error);
			
		}
		
	}
	
	public void cerrarConexionSql(){

		try{

			if(stmt != null)
				stmt.close();

		}catch(SQLException se2){

		}

		try{

			if(conexion != null)
				conexion.close();

		}catch(SQLException se3){

			se3.printStackTrace();

		}

	}
	
	public Connection getConeccion(){
		return this.conexion;
	}

}
