package mysql;

import java.sql.*;

public class ConexionMySql {

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "jdbc:mysql://";
	String servidor = "localhost/";
	String baseDeDatos = "ventas2017a"; 
	
	final String USER = "root";
	final String PASSWORD = "";
	
	public void conexion(String args[]){
		
		DB_URL += servidor + baseDeDatos; 
		
		Connection conexion = null;
		Statement stmt = null;
		
		
		
		try{
			
			Class.forName(JDBC_DRIVER);
			System.out.println("Conectando a la base de datos");
			conexion = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			System.out.println("Creando Statement");
			stmt = conexion.createStatement();
			
		}catch(SQLException se){
			
			se.printStackTrace(); //Manejar errores de JDBC
		
		}catch(Exception e){
			
			e.printStackTrace(); //Manejar errores de Class.forName
		
		}finally{
			
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
		
	}
	
}
