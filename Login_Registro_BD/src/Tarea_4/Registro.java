package Tarea_4;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class Registro {

	JFrame Registro;
	private JTextField textNombreUsuario;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textNumTelefono;
	private JTextField textCorreo;
	private JPasswordField passwordClave;
	private JPasswordField passwordConfirClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro window = new Registro();
					window.Registro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Registro() {
		initialize();
		
	}
	
	//Objeto tipo validarCampos
	ValidarCampos val = new ValidarCampos();
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Registro = new JFrame();
		Registro.getContentPane().setBackground(SystemColor.activeCaption);
		Registro.setTitle("Registro");
		Registro.setBounds(100, 100, 250, 420);
		Registro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Registro.getContentPane().setLayout(null);
		
		textNombreUsuario = new JTextField();
		textNombreUsuario.setFont(new Font("Consolas", Font.PLAIN, 15));
		textNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textNombreUsuario.setText("Usuario");
		textNombreUsuario.setBounds(48, 60, 140, 30);
		Registro.getContentPane().add(textNombreUsuario);
		textNombreUsuario.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Consolas", Font.PLAIN, 15));
		textNombre.setHorizontalAlignment(SwingConstants.CENTER);
		textNombre.setText("Nombre");
		textNombre.setBounds(48, 103, 140, 30);
		Registro.getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textApellido = new JTextField();
		textApellido.setFont(new Font("Consolas", Font.PLAIN, 15));
		textApellido.setText("Apellido");
		textApellido.setHorizontalAlignment(SwingConstants.CENTER);
		textApellido.setBounds(48, 145, 140, 30);
		Registro.getContentPane().add(textApellido);
		textApellido.setColumns(10);
		
		textNumTelefono = new JTextField();
		textNumTelefono.setFont(new Font("Consolas", Font.PLAIN, 12));
		textNumTelefono.setText("Numero Telefonico");
		textNumTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		textNumTelefono.setBounds(48, 188, 140, 30);
		Registro.getContentPane().add(textNumTelefono);
		textNumTelefono.setColumns(10);
		
		textCorreo = new JTextField();
		textCorreo.setFont(new Font("Consolas", Font.PLAIN, 15));
		textCorreo.setText("Correo");
		textCorreo.setHorizontalAlignment(SwingConstants.CENTER);
		textCorreo.setBounds(48, 230, 140, 30);
		Registro.getContentPane().add(textCorreo);
		textCorreo.setColumns(10);
		
		//Boton Registrar
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//Objeto de la clase ConexionBD
				ConexionBD con = new ConexionBD();
				
				//Obtiene la clave como un arreglo de chars y luego pasa esta arreglo a String
				char[] arrayA = passwordClave.getPassword();
				String pass1 = new String(arrayA);
				
				//Obtiene la confirmacion de la clave como un arreglo de chars y luego pasa esta arreglo a String
				char[] arrayB = passwordConfirClave.getPassword();
				String pass2 = new String(arrayB);
				
				//Contador de la ValidarCompos
				val.setContador(0);
				
				//Usa un if y una validacion de la clase ValidarCampos para ver si no existen campos vacios, en caso de no existir pasa a insertar los registros y de paso te regresa al login
				if(val.validar(textNombreUsuario.getText(), textNombre.getText(), textApellido.getText(), val.validarNumero(textNumTelefono.getText()), textCorreo.getText(), pass1, pass2) == 0) {
					con.Insert(textNombreUsuario.getText(), textNombre.getText(), textApellido.getText(), textNumTelefono.getText(), textCorreo.getText(), pass1);
					Login abrir = new Login();
					abrir.Login.setVisible(true);
					Registro.setVisible(false);
					
				}
			}
		});
		btnRegistrar.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnRegistrar.setBounds(48, 347, 140, 25);
		Registro.getContentPane().add(btnRegistrar);
		
		Label labelRegistro = new Label("REGISTER");
		labelRegistro.setForeground(Color.BLACK);
		labelRegistro.setFont(new Font("Consolas", Font.BOLD, 28));
		labelRegistro.setAlignment(Label.CENTER);
		labelRegistro.setBounds(10, 6, 149, 45);
		Registro.getContentPane().add(labelRegistro);
		
		passwordClave = new JPasswordField();
		passwordClave.setToolTipText("");
		passwordClave.setHorizontalAlignment(SwingConstants.CENTER);
		passwordClave.setBounds(48, 269, 140, 30);
		Registro.getContentPane().add(passwordClave);
		
		passwordConfirClave = new JPasswordField();
		passwordConfirClave.setHorizontalAlignment(SwingConstants.CENTER);
		passwordConfirClave.setToolTipText("");
		passwordConfirClave.setBounds(48, 310, 140, 30);
		Registro.getContentPane().add(passwordConfirClave);
		
		lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\admin\\Desktop\\Programacion 1\\Icon\\Pass_Icon.png"));
		lblNewLabel_5.setBounds(0, 303, 46, 48);
		Registro.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\admin\\Desktop\\Programacion 1\\Icon\\pass1_icon.png"));
		lblNewLabel_6.setBounds(0, 258, 46, 48);
		Registro.getContentPane().add(lblNewLabel_6);
	}
}
