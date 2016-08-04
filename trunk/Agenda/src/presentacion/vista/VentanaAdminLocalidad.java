package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import presentacion.controlador.Controlador;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class VentanaAdminLocalidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador;
	private JTable tablaLocalidades;
	private DefaultTableModel modelLocalidades;
	private  String[] nombreColumnas = {"Localidad"};
	private JTextField txtLocalidad;
	private JButton btnAgregarLocalidad;
	private JButton btnEditar;
	private JButton btnEliminar;


	public VentanaAdminLocalidad(Controlador controlador) 
	{
		super();
		setTitle("ABM Localidades");
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 407, 344); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initialize();

		this.setVisible(true);
	}
	
	
	private void initialize() {
		
		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(10, 11, 371, 182);
		contentPane.add(spLocalidades);
		
		modelLocalidades = new DefaultTableModel(null,nombreColumnas);
		tablaLocalidades = new JTable(modelLocalidades);
		
		tablaLocalidades.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaLocalidades.getColumnModel().getColumn(0).setResizable(false);
		
		spLocalidades.setViewportView(tablaLocalidades);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBounds(292, 272, 89, 23);
		btnAgregarLocalidad.addActionListener(this.controlador);
		contentPane.add(btnAgregarLocalidad);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(76, 272, 206, 23);
		contentPane.add(txtLocalidad);
		txtLocalidad.setColumns(10);
		
		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(10, 276, 98, 19);
		contentPane.add(lblLocalidad);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(this.controlador);
		btnEditar.setBounds(10, 204, 89, 23);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this.controlador);
		btnEliminar.setBounds(292, 204, 89, 23);
		btnEliminar.addActionListener(this.controlador);
		contentPane.add(btnEliminar);
		
	}


	public DefaultTableModel getModelLocalidades() 
	{
		return modelLocalidades;
	}
	
	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}


	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}
	
	public JButton getBtnEliminarLocalidad() {
		return btnEliminar;
	}
	
	public JButton getBtnEditarLocalidad() {
		return btnEditar;
	}

	public JTextField getTxtLocalidad() {
		return txtLocalidad;
	}
	
	public JTable getTablaLocalidades() {
		return tablaLocalidades;
	}
}
