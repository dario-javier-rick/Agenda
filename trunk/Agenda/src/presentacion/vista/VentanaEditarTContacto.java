package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

public class VentanaEditarTContacto extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador;
	private JTextField txtTContacto1;
	private JButton btnAceptar;
	private JTextField txtTContacto2;


	public VentanaEditarTContacto(Controlador controlador) 
	{
		super();
		setTitle("Modificar Localidad");
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 346, 187); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initialize();

		this.setVisible(true);
	}
	
	
	private void initialize() {
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(204, 49, 89, 48);
		btnAceptar.addActionListener(this.controlador);
		contentPane.add(btnAceptar);
		
		txtTContacto1 = new JTextField();
		txtTContacto1.setEditable(false);
		txtTContacto1.setBounds(51, 38, 120, 23);
		contentPane.add(txtTContacto1);
		txtTContacto1.setColumns(10);
		
		txtTContacto2 = new JTextField();
		txtTContacto2.setColumns(10);
		txtTContacto2.setBounds(51, 86, 120, 23);
		contentPane.add(txtTContacto2);
		
	}

	public JButton getBtnAceptarEdicion() {
		return btnAceptar;
	}

	public JTextField getTxtTContacto1() {
		return txtTContacto1;
	}
	
	public void setTxtTContacto1(String text) {
		this.txtTContacto1.setText(text);
	}
	
	public JTextField getTxtTContacto2() {
		return txtTContacto2;
	}
}
