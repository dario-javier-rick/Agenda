package presentacion.vista;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;
import java.awt.Font;

public class VentanaAdminDB
{
	private JFrame frmVentana;
	private JPanel contentPane;
	private JTextField txtUser;
	private JButton btnAceptar;
	private Controlador controlador;
	private JButton btnCancelar;
	private JTextField txtPasword;
	private JTextField txtPort;
	private JTextField txtDBName;
	
	public VentanaAdminDB() 
	{
		super();
		initialize();
	}
	
	private void initialize() 
	{
		frmVentana = new JFrame();
		frmVentana.setTitle("Administrar DB");
		frmVentana.setResizable(false);
		frmVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentana.setBounds(100, 100, 389, 312); // Entrega 15/03 - Dario Rick
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmVentana.getContentPane().add(contentPane);
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 365, 261); // Entrega 15/03 - Dario Rick
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUser.setBounds(61, 28, 113, 39);
		panel.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setBounds(184, 38, 126, 20);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this.controlador);
		btnAceptar.setBounds(83, 210, 95, 30); // Entrega 15/03 - Dario Rick
		panel.add(btnAceptar);
	
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this.controlador);
		btnCancelar.setBounds(184, 210, 95, 30);
		panel.add(btnCancelar);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(61, 69, 113, 39);
		panel.add(lblPassword);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPort.setBounds(61, 110, 113, 39);
		panel.add(lblPort);
		
		JLabel lblDbName = new JLabel("DB Name:");
		lblDbName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDbName.setBounds(61, 149, 113, 39);
		panel.add(lblDbName);
		
		txtPasword = new JTextField();
		txtPasword.setColumns(10);
		txtPasword.setBounds(184, 79, 126, 20);
		panel.add(txtPasword);
		
		txtPort = new JTextField();
		txtPort.setEditable(false);
		txtPort.setColumns(10);
		txtPort.setBounds(184, 120, 126, 20);
		panel.add(txtPort);
		
		txtDBName = new JTextField();
		txtDBName.setEditable(false);
		txtDBName.setColumns(10);
		txtDBName.setBounds(184, 159, 126, 20);
		panel.add(txtDBName);
	}
	
	public void show()
	{
		this.frmVentana.setVisible(true);
	}
	
	public void hide()
	{
		this.frmVentana.setVisible(false);
	}

	public JTextField getTxtUser() {
		return txtUser;
	}

	public void setTxtUser(JTextField txtUser) {
		this.txtUser = txtUser;
	}

	public JTextField getTxtPasword() {
		return txtPasword;
	}

	public void setTxtPasword(JTextField txtPasword) {
		this.txtPasword = txtPasword;
	}

	public JTextField getTxtPort() {
		return txtPort;
	}

	public void setTxtPort(JTextField txtPort) {
		this.txtPort = txtPort;
	}

	public JTextField getTxtDBName() {
		return txtDBName;
	}

	public void setTxtDBName(JTextField txtDBName) {
		this.txtDBName = txtDBName;
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
}

