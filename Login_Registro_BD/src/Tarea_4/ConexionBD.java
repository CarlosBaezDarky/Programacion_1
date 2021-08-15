package Tarea_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConexionBD { 
	
	//Atributo de la clase
	private Connection con;
	
	//Metodo para iniciar una conexion con la base de dato
	public Connection Conectar() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginRegistro?autoReconnect=true&useSSL=false", "root", "Risottonero60");
		
		if(con != null) {
			System.out.println("Conectado correctamente a la base de datos");
		}
		
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Error en la conexion con la base de datos"); 
		e.printStackTrace();
	}
	return con;
	}
	
	//Metodo que sirve para realizar un insert de los datos en la base de datos
	public void Insert (String usuario, String nombre, String apellido, String telefono, String correo, String pwd) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginRegistro?autoReconnect=true&useSSL=false", "root", "Risottonero60");
			
			String usuario1 = usuario;
			String nombre1 = nombre;
			String apellido1 = apellido;
			Long telefono1 = Long.parseLong(telefono);
			String correo1 = correo;
			String pwd1 = pwd;
			
			String query = "INSERT INTO usuarios (usuario, nombre, apellido, telefono, correo, clave) values ('"+usuario1+"','"+nombre1+"','"+apellido1+"','"+telefono1+"', '"+correo1+"', '"+pwd1+"')";
			
			Statement st = conexion.createStatement();
			
			st.executeUpdate(query);
			
			conexion.close();
			
			JOptionPane.showMessageDialog(null, "Usuario agregado");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en la conexion con la base de datos"); 
			e.printStackTrace();
		}
	}
	
	//Metodo que sirve para realizar un update de los datos en la base de datos
	public void Update (String usuario, String nombre, String apellido, String telefono, String correo, String pwd) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginRegistro?autoReconnect=true&useSSL=false", "root", "Risottonero60");
			
			String usuario1 = usuario;
			String nombre1 = nombre;
			String apellido1 = apellido;
			Long telefono1 = Long.parseLong(telefono);
			String correo1 = correo;
			String pwd1 = pwd;
			
			PreparedStatement query = (PreparedStatement)conexion.prepareStatement("UPDATE usuarios SET usuario = ?, nombre = ?, apellido = ?, telefono = ?, correo = ?, clave = ? WHERE id = ?");
		    query.setString(1, usuario1);
		    query.setString(2, nombre1);
		    query.setString(3, apellido1);
		    query.setLong(4, telefono1);
		    query.setString(5, correo1);
		    query.setString(6, pwd1);
		    query.setInt(7,UsuariosRegistrados.getId());
		    query.executeUpdate();

			conexion.close();
			
			JOptionPane.showMessageDialog(null, "Usuario actulizado");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en la conexion con la base de datos"); 
			e.printStackTrace();
		}
	}
	
	//Metodo que sirve para eliminar registros atraves de su id y sin recibir parametros
	public void Delete () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginRegistro?autoReconnect=true&useSSL=false", "root", "Risottonero60");

			PreparedStatement query = (PreparedStatement)conexion.prepareStatement("DELETE FROM usuarios WHERE id = ?");
		    query.setInt(1,UsuariosRegistrados.getId());
		    query.executeUpdate();

			conexion.close();
			
			JOptionPane.showMessageDialog(null, "Usuario eliminado");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en la conexion con la base de datos"); 
			e.printStackTrace();
		}
	}
	
}


