package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import presentacion.controlador.Controlador;

public class VentanaEditarLocalidad extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador;
	private DefaultTableModel modelLocalidades;
	private JTextField txtLocalidad1;
	private JButton btnAceptar;
	private JTextField txtLocalidad2;


	public VentanaEditarLocalidad(Controlador controlador) 
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
		
		txtLocalidad1 = new JTextField();
		txtLocalidad1.setEditable(false);
		txtLocalidad1.setBounds(51, 38, 120, 23);
		contentPane.add(txtLocalidad1);
		txtLocalidad1.setColumns(10);
		
		txtLocalidad2 = new JTextField();
		txtLocalidad2.setColumns(10);
		txtLocalidad2.setBounds(51, 86, 120, 23);
		contentPane.add(txtLocalidad2);
		
	}


	public DefaultTableModel getModelLocalidades() 
	{
		return modelLocalidades;
	}

	public JButton getBtnAceptarEdicion() {
		return btnAceptar;
	}

	public JTextField getTxtLocalidad1() {
		return txtLocalidad1;
	}
	
	public void setTxtLocalidad1(String text) {
		this.txtLocalidad1.setText(text);
	}
	
	public JTextField getTxtLocalidad2() {
		return txtLocalidad2;
	}
}
