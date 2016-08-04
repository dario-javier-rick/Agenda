package presentacion.vista;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import presentacion.controlador.Controlador;

public class VentanaAdminTContacto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador controlador;

	private JTable tablaPersonas;
	private JButton btnAgregarTContacto;
	private JButton btnBorrar;
	private DefaultTableModel modelTiposContacto;
	private String[] nombreColumnas = {"Tipo de contacto"};
	private JTextField txtTipo;
	private JLabel lblTipo;
	private JButton btnEditar;

	public VentanaAdminTContacto(Controlador controlador) 
	{
		super();
		setTitle("ABM tipos de contacto");
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 404, 375); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initialize();
		
		this.setVisible(true);
	}
	
	
	private void initialize() 
	{
		
		JScrollPane spTiposContactos = new JScrollPane();
		spTiposContactos.setBounds(10, 11, 368, 197);
		contentPane.add(spTiposContactos);
		
		modelTiposContacto = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelTiposContacto);
		
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		
		spTiposContactos.setViewportView(tablaPersonas);
		
		btnAgregarTContacto = new JButton("Agregar");
		btnAgregarTContacto.setBounds(289, 303, 89, 23);
		btnAgregarTContacto.addActionListener(this.controlador);
		contentPane.add(btnAgregarTContacto);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(10, 219, 89, 23);
		btnEditar.addActionListener(this.controlador);
		contentPane.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(289, 219, 89, 23);
		btnBorrar.addActionListener(this.controlador);
		contentPane.add(btnBorrar);
		
		txtTipo = new JTextField();
		txtTipo.setBounds(101, 304, 178, 20);
		contentPane.add(txtTipo);
		txtTipo.setColumns(10);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(20, 307, 46, 14);
		contentPane.add(lblTipo);
		
	}


	public JButton getBtnAgregarTContacto() {
		return btnAgregarTContacto;
	}

	public JButton getBtnEliminarTContacto() {
		return btnBorrar;
	}	

	public JTextField getTxtTipo() {
		return txtTipo;
	}


	public DefaultTableModel getModelTiposContacto() {
		return modelTiposContacto;
	}
	
	public JButton getBtnEditarTContacto() {
		return btnEditar;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}
	
	public JTable getTablaTContactos() {
		return tablaPersonas;
	}
}
