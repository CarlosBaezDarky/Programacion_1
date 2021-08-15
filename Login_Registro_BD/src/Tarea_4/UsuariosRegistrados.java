package Tarea_4;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.ScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import java.awt.Scrollbar;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class UsuariosRegistrados {

	JFrame UsRegistrado;
	private JTable table;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnCerrarSeccion;
	private JTextField nombreUp;
	private JTextField apellidoUp;
	private JTextField telefonoUp;
	private JTextField correoUp;
	private JTextField usuarioUp;
	private JPasswordField claveUp;
	
	DefaultTableModel modelo;  
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuariosRegistrados window = new UsuariosRegistrados();
					window.UsRegistrado.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UsuariosRegistrados() {
		initialize();
		
	}
	//Atributo el cual almacena el id de los registros o usuarios 
	private static int id;
	private JLabel lblNewLabel;
	
	//Metodo get del id
	public static int getId() {
		return id;
	}
	
	//Metodo set del id
	public static void setId(int a) {
		id = a;
	}
	
	//Metodo para mostrar los datos de la base de datos
	private void mostrarDatos() {

	ConexionBD con = new ConexionBD();
	Connection conexion = con.Conectar();
	
	String sql = "SELECT * FROM usuarios";
	Statement st;
	
	String[] dato = new String[7];
	
	try {
		st = conexion.createStatement();
		
		ResultSet result = st.executeQuery(sql);
		
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		
		while(result.next()) {
			dato[0] = result.getString(1);
			dato[1] = result.getString(2);
			dato[2] = result.getString(3);
			dato[3] = result.getString(4);
			dato[4] = result.getString(5);
			dato[5] = result.getString(6);

			modelo.addRow(dato);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		UsRegistrado = new JFrame();
		UsRegistrado.getContentPane().setBackground(SystemColor.activeCaption);
		UsRegistrado.setTitle("Usuarios Registrado");
		UsRegistrado.setBounds(100, 100, 700, 315);
		UsRegistrado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 480, 206);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(400, 200));
		UsRegistrado.getContentPane().add(scrollPane);
		
		//Boton Actulizar
		btnActualizar = new JButton("Actulizar");
		btnActualizar.setBounds(20, 235, 110, 27);
		btnActualizar.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnActualizar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//Objeto de la clase ValidarCampos
				ValidarCampos val3 = new ValidarCampos();
				
				//Objeto de la clase ConexionBD
				ConexionBD conec = new ConexionBD();
				
				//Contador de la ValidarCompos
				val3.setContador(0);
				
				//Obtiene la clave como un arreglo de chars y luego pasa esta arreglo a String
				char[] arrayA = claveUp.getPassword();
				String pass = new String(arrayA);
				
				//Usa un if y una validacion de la clase ValidarCampos para ver si no existen campos vacios, en caso de no existir pasa a actualizar los datos y los muestrar
				if(val3.validar(usuarioUp.getText(), nombreUp.getText(), apellidoUp.getText(), val3.validarNumero(telefonoUp.getText()), correoUp.getText(), pass, pass) == 0) {
					conec.Update(usuarioUp.getText(), nombreUp.getText(), apellidoUp.getText(), telefonoUp.getText(), correoUp.getText(), pass);
					mostrarDatos();
				}
			}
		});
		
		//Boton Eliminar
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(189, 235, 110, 27);
		btnEliminar.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnEliminar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//Objeto de la clase ConexionBD
				ConexionBD conect = new ConexionBD();
				
				//Usa el metodo Delete de la clase para eliminar los registros atraves de su id, el cual obtien de  un evento clicked
				conect.Delete();
				
				//Muestra los datos restantes
				mostrarDatos();
			}
		});
		
		//Boton Cerrar Seccion
		btnCerrarSeccion = new JButton("Cerrar Seccion");
		btnCerrarSeccion.setBounds(345, 235, 147, 27);
		btnCerrarSeccion.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnCerrarSeccion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//Habre la ventana  Login y cierra la ventana actrual UsuariosRegistrados
				Login abrir = new Login();
				abrir.Login.setVisible(true);
				UsRegistrado.setVisible(false);
				
			}
		});
		
		nombreUp = new JTextField();
		nombreUp.setBounds(526, 49, 130, 20);
		nombreUp.setFont(new Font("Consolas", Font.PLAIN, 11));
		nombreUp.setText("Nombre");
		nombreUp.setHorizontalAlignment(SwingConstants.CENTER);
		nombreUp.setColumns(10);
		
		apellidoUp = new JTextField();
		apellidoUp.setBounds(526, 80, 130, 20);
		apellidoUp.setFont(new Font("Consolas", Font.PLAIN, 11));
		apellidoUp.setHorizontalAlignment(SwingConstants.CENTER);
		apellidoUp.setText("Apellido");
		apellidoUp.setColumns(10);
		
		telefonoUp = new JTextField();
		telefonoUp.setBounds(526, 111, 130, 20);
		telefonoUp.setFont(new Font("Consolas", Font.PLAIN, 11));
		telefonoUp.setText("Telefono");
		telefonoUp.setHorizontalAlignment(SwingConstants.CENTER);
		telefonoUp.setColumns(10);
		
		correoUp = new JTextField();
		correoUp.setBounds(526, 142, 130, 20);
		correoUp.setFont(new Font("Consolas", Font.PLAIN, 11));
		correoUp.setText("Correo");
		correoUp.setHorizontalAlignment(SwingConstants.CENTER);
		correoUp.setColumns(10);
		
		usuarioUp = new JTextField();
		usuarioUp.setBounds(526, 20, 130, 20);
		usuarioUp.setFont(new Font("Consolas", Font.PLAIN, 11));
		usuarioUp.setHorizontalAlignment(SwingConstants.CENTER);
		usuarioUp.setText("Usuario");
		usuarioUp.setColumns(10);
		
		claveUp = new JPasswordField();
		claveUp.setBounds(526, 189, 130, 20);
		claveUp.setFont(new Font("Consolas", Font.PLAIN, 11));
		claveUp.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(485, 173, 48, 48);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\admin\\Desktop\\Programacion 1\\Icon\\pass1_icon.png"));
		
		//Modelo de la JTable creo para coincidir con los campos de la base de datos
		modelo = new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
					"Id","Usuario", "Nombre", "Apellido", "Telefono", "Correo"
				}
			);
		table = new JTable(modelo);
		
		//Evneto Clicked, el cual permite tomar los datos de la JTable y pasarlo a unos campos especificos, para Actualizar o Eliminar
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.getClickCount() == 1) {
					JTable receptor = (JTable)e.getSource();
					
					UsuariosRegistrados.setId(Integer.parseInt(receptor.getModel().getValueAt(receptor.getSelectedRow(), 0).toString()));
					usuarioUp.setText(receptor.getModel().getValueAt(receptor.getSelectedRow(), 1).toString());
					nombreUp.setText(receptor.getModel().getValueAt(receptor.getSelectedRow(), 2).toString());
					apellidoUp.setText(receptor.getModel().getValueAt(receptor.getSelectedRow(), 3).toString());
					telefonoUp.setText(receptor.getModel().getValueAt(receptor.getSelectedRow(), 4).toString());
					correoUp.setText(receptor.getModel().getValueAt(receptor.getSelectedRow(), 5).toString());
					
				}
			}
		});
		UsRegistrado.getContentPane().setLayout(null);
		scrollPane.setViewportView(table);
		UsRegistrado.getContentPane().add(scrollPane);
		UsRegistrado.getContentPane().add(btnActualizar);
		UsRegistrado.getContentPane().add(btnEliminar);
		UsRegistrado.getContentPane().add(btnCerrarSeccion);
		UsRegistrado.getContentPane().add(lblNewLabel);
		UsRegistrado.getContentPane().add(apellidoUp);
		UsRegistrado.getContentPane().add(claveUp);
		UsRegistrado.getContentPane().add(usuarioUp);
		UsRegistrado.getContentPane().add(correoUp);
		UsRegistrado.getContentPane().add(telefonoUp);
		UsRegistrado.getContentPane().add(nombreUp);
		
		//Muestra los registros
		mostrarDatos();
	}
}
