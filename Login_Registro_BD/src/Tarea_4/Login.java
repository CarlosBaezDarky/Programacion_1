package Tarea_4;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Login {

	JFrame Login;
	private JTextField txtUsuario;
	private JPasswordField passwordClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.Login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}
	
	// Objeto tipo ValidarCampos con el fin de usar sus metodos de validacion
	ValidarCampos val2 = new ValidarCampos(); 
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Login = new JFrame();
		Login.setTitle("Login");
		Login.getContentPane().setFont(new Font("Consolas", Font.PLAIN, 11));
		Login.getContentPane().setBackground(SystemColor.activeCaption);
		Login.setBounds(100, 100, 250, 350);
		Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Login.getContentPane().setLayout(null);
		
		Label labelLogin = new Label("LOGIN");
		labelLogin.setFont(new Font("Consolas", Font.BOLD, 30));
		labelLogin.setForeground(Color.BLACK);
		labelLogin.setAlignment(Label.CENTER);
		labelLogin.setBounds(25, 10, 101, 57);
		Login.getContentPane().add(labelLogin);
		
		txtUsuario = new JTextField();
		txtUsuario.setText("Usuario");
		txtUsuario.setFont(new Font("Consolas", Font.PLAIN, 15));
		txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(48, 89, 147, 37);
		Login.getContentPane().add(txtUsuario);
		
		//Boton Entrar
		JButton btnLogin = new JButton("Entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Obtiene la clave como un arreglo de chars y luego pasa esta arreglo a String
				char[] arrayA = passwordClave.getPassword();
				String pass = new String(arrayA);
				
				//Contador de la ValidarCompos
				val2.setContador(0);
				
				//Usa un if y una validacion de la Clase ValidarCampos para saber si existen campos por llenar 
				if (val2.validarLogin(txtUsuario.getText(), pass) >= 1) {
					JOptionPane.showMessageDialog(null, "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse.");
				}
				else {
					
					//Objeto de la clase ConexionBD y usa el metodo Conectar para concetarse a la base de datos
					ConexionBD con1 = new ConexionBD();
					Connection conex = con1.Conectar();
					
					//Uso de try para ejecutar un cosulta con el fin de ver si las credencias insertadas existen en la base de datos o estan registradas
					try {
			            PreparedStatement st = (PreparedStatement)conex.prepareStatement("SELECT * FROM usuarios WHERE usuario =? AND clave =?");
			        st.setString(1, txtUsuario.getText());
			        st.setString(2, pass);
			        ResultSet res = st.executeQuery();
			        
			        //En caso de estar en la base de datos pues de llama a la ventana UsuariosRegistrados
			        if(res.next()){
			        	UsuariosRegistrados abrir = new UsuariosRegistrados();
						abrir.UsRegistrado.setVisible(true);
						Login.setVisible(false);   
			        	}
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				 }
				}
			}
		});
		btnLogin.setFont(new Font("Consolas", Font.PLAIN, 25));
		btnLogin.setBounds(48, 212, 147, 37);
		Login.getContentPane().add(btnLogin);
		
		//Boton Registrarse
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//Abre la ventana de Registro y cierra la actual Login
				Registro abrir = new Registro();
				abrir.Registro.setVisible(true);
				Login.setVisible(false);
			}
		});
		btnRegistrarse.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnRegistrarse.setBounds(61, 260, 123, 20);
		Login.getContentPane().add(btnRegistrarse);
		
		passwordClave = new JPasswordField();
		passwordClave.setHorizontalAlignment(SwingConstants.CENTER);
		passwordClave.setBounds(48, 150, 147, 37);
		Login.getContentPane().add(passwordClave);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\admin\\Desktop\\Programacion 1\\Icon\\User_icon.png"));
		lblNewLabel.setBounds(10, 80, 46, 53);
		Login.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\admin\\Desktop\\Programacion 1\\Icon\\Pass_Icon.png"));
		lblNewLabel_1.setBounds(10, 143, 46, 50);
		Login.getContentPane().add(lblNewLabel_1);
}
}

