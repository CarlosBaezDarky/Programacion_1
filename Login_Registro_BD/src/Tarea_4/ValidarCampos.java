package Tarea_4;

import javax.swing.JOptionPane;

public class ValidarCampos {
	
	//Atributo de la clase el cual sirve de contador
	private int contador = 0;
	
	//set del atributo
	public void setContador( int contador) {
		this.contador = contador;
	}
	
	//Metodo para incrementar el atributo contador
	private int Incrementador() {
		return 1;
	}
	
	//Metodo que valida si el telefono es un entero o mejor dicho un long, da true si es un numero y false si no lo es
	public boolean validarNumero(String value) {
	    try {
	        long num = Long.parseLong(value);
		return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	//Metodo que se encarga de evaluar si existen campos sin llenar, y usa el atributo contador para saber cuantos campos son los estan vacios
	public int validar(String usuario, String nombre, String apellido, boolean telefono, String correo, String pass1, String pass2 ) {
		
		if(usuario.equals("") == true || usuario.equals(null) == true) { this.contador += Incrementador(); 
		JOptionPane.showMessageDialog(null, "Completar el campo Usuario.");}
		
		if(nombre.equals("") == true || nombre.equals(null) == true) { this.contador += Incrementador(); 
		JOptionPane.showMessageDialog(null, "Completar el campo Nombre.");}
		
		if(apellido.equals("") == true || apellido.equals(null) == true) { this.contador += Incrementador(); 
		JOptionPane.showMessageDialog(null, "Completar el campo Apellido.");}
		
		if(telefono == false) { this.contador += Incrementador(); 
		JOptionPane.showMessageDialog(null, "Completar el campo Numero Telefonico.");}
		
		if(correo.equals("") == true || correo.equals(null) == true) { this.contador += Incrementador(); 
		JOptionPane.showMessageDialog(null, "Completar el campo Correo.");}
		
		if(pass1.equals("") == true || pass1.equals(null) == true) { this.contador += Incrementador();
		JOptionPane.showMessageDialog(null, "Completar el campo Clave.");}
		
		if(pass2.equals("") == true || pass2.equals(null) == true) { this.contador += Incrementador();
		JOptionPane.showMessageDialog(null, "Completar el campo Confirmacion de Clave.");}
		
		validarPassword(pass1, pass2);
		
		return this.contador;

	}
	
	//Metodo que se encarga de validar si la clave y la confirmacion de la clave coinciden
	private void validarPassword(String a, String b) {
		
		if(a.equals(b) == true) {}
		else {this.contador += Incrementador(); JOptionPane.showMessageDialog(null, "Las claves no coinciden.");}
		
	}
	
	//Metodo que se encarga de evaluar si existen campos vacios en el Login
	public int validarLogin (String usuario, String pass) {
		
		
		if(usuario.equals("") == true || usuario.equals(null) == true) { this.contador += Incrementador();}
		
		
		if(pass.equals("") == true || pass.equals(null) == true) { this.contador += Incrementador();}
		
		return this.contador;

	}


}
