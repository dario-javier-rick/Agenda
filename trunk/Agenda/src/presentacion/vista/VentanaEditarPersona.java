package presentacion.vista;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import presentacion.controlador.Controlador;

// Entrega 15/03 - Dario Rick INI
import javax.swing.JComboBox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;

import com.toedter.calendar.JDateChooser; 
// Entrega 15/03 - Dario Rick FIN

import dto.LocalidadDTO;
import dto.TPersonaDTO;

public class VentanaEditarPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JButton btnAgregarPersona;
	private Controlador controlador;
// Entrega 15/03 - Dario Rick INI
	private JTextField textCalle;
	private int idPersona;
	private JTextField textAltura;
	private JTextField textPiso;
	private JTextField textDepto;
	private JTextField textEmail;
	private JButton btnEditarLocalidad;
	private JButton btnEditarTContacto;
	private JComboBox<LocalidadDTO> comboLocalidad;
	private JComboBox<TPersonaDTO> comboTipoContacto;
// Entrega 15/03 - Dario Rick FIN
// Entrega 15/03 - Ivan Quintero INI
	private JDateChooser fechaNac;
// Entrega 15/03 - Ivan Quintero FIN
	
	public VentanaEditarPersona(Controlador controlador) 
	{
		super();
		setTitle("Editar contacto");
		this.controlador = controlador;
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 404, 375); // Entrega 15/03 - Dario Rick
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 373, 315); // Entrega 15/03 - Dario Rick
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 11, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(10, 52, 113, 14);
		panel.add(lblTelfono);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.addKeyListener(new KeyAdapter() {
			// Entrega 15/03 - Ivan Quintero INI
			@Override
			public void keyTyped(KeyEvent evt) {
				char ch = evt.getKeyChar();
				String c = Character.toString(ch);
				if (!c.matches("[a-zA-Z ]+")){
					if (ch != KeyEvent.VK_BACK_SPACE)
					{
						getToolkit().beep();
					}
					evt.consume();
				}
			}
			// Entrega 15/03 - Ivan Quintero FIN
		});
		txtNombre.setBounds(149, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.addKeyListener(new KeyAdapter() {
			// Entrega 15/03 - Ivan Quintero INI
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				
				if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE))
				{
					if (c != KeyEvent.VK_BACK_SPACE)
					{
						getToolkit().beep();
					}
					evt.consume();
				}
			}
			// Entrega 15/03 - Ivan Quintero FIN
		});
		txtTelefono.setBounds(149, 49, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		btnAgregarPersona = new JButton("Aceptar");
		btnAgregarPersona.addActionListener(this.controlador);
		btnAgregarPersona.setBounds(272, 292, 89, 23); // Entrega 15/03 - Dario Rick
		panel.add(btnAgregarPersona);
		
		// Entrega 15/03 - Dario Rick INI
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 199, 89, 14);
		panel.add(lblEmail);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setBounds(10, 231, 137, 14);
		panel.add(lblFechaDeNacimiento);
		
		JLabel lblTipoDeContacto = new JLabel("Tipo de contacto");
		lblTipoDeContacto.setBounds(10, 264, 137, 14);
		panel.add(lblTipoDeContacto);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(10, 92, 72, 14);
		panel.add(lblDomicilio);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(114, 92, 46, 14);
		panel.add(lblCalle);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(264, 92, 46, 14);
		panel.add(lblAltura);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(114, 120, 46, 14);
		panel.add(lblPiso);
		
		JLabel lblDepto = new JLabel("Depto.");
		lblDepto.setBounds(264, 120, 46, 14);
		panel.add(lblDepto);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 164, 89, 14);
		panel.add(lblLocalidad);
		
		textCalle = new JTextField();
		textCalle.addKeyListener(new KeyAdapter() {
			// Entrega 15/03 - Ivan Quintero INI
			@Override
			public void keyTyped(KeyEvent evt) {
				char ch = evt.getKeyChar();
				String c = Character.toString(ch);
				if (!c.matches("[a-zA-Z0-9 ]+")){
					if (ch != KeyEvent.VK_BACK_SPACE)
					{
						getToolkit().beep();
					}
					evt.consume();
				}
			}
			// Entrega 15/03 - Ivan Quintero FIN
		});
		textCalle.setBounds(149, 89, 89, 20);
		panel.add(textCalle);
		textCalle.setColumns(10);
		
		textAltura = new JTextField();
		textAltura.addKeyListener(new KeyAdapter() {
			// Entrega 15/03 - Ivan Quintero INI
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				
				if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE))
				{
					if (c != KeyEvent.VK_BACK_SPACE)
					{
						getToolkit().beep();
					}
					evt.consume();
				}
			}
			// Entrega 15/03 - Ivan Quintero FIN
		});
		textAltura.setBounds(305, 89, 46, 20);
		panel.add(textAltura);
		textAltura.setColumns(10);
		
		textPiso = new JTextField();
		textPiso.addKeyListener(new KeyAdapter() {
			// Entrega 15/03 - Ivan Quintero INI
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				
				if(!Character.isDigit(c))
				{
					if (c != KeyEvent.VK_BACK_SPACE)
					{
						getToolkit().beep();
					}
					evt.consume();
				}
			}
			// Entrega 15/03 - Ivan Quintero FIN
		});
		textPiso.setBounds(149, 117, 89, 20);
		panel.add(textPiso);
		textPiso.setColumns(10);
		
		textDepto = new JTextField();
		textDepto.addKeyListener(new KeyAdapter() {
			// Entrega 15/03 - Ivan Quintero INI
			@Override
			public void keyTyped(KeyEvent evt) {
				char ch = evt.getKeyChar();
				String c = Character.toString(ch);
				if (!c.matches("[a-zA-Z0-9]+")){
					if (ch != KeyEvent.VK_BACK_SPACE)
					{
						getToolkit().beep();
					}
					evt.consume();
				}
			}
			// Entrega 15/03 - Ivan Quintero FIN
		});
		textDepto.setBounds(305, 117, 46, 20);
		panel.add(textDepto);
		textDepto.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(149, 196, 89, 20);
		panel.add(textEmail);
		textEmail.setColumns(10);
		
		comboLocalidad = new JComboBox<LocalidadDTO>();
		comboLocalidad.setBounds(149, 161, 89, 20);
		panel.add(comboLocalidad);
		
		comboTipoContacto = new JComboBox<TPersonaDTO>();
		comboTipoContacto.setBounds(149, 261, 89, 20);
		panel.add(comboTipoContacto);
		
		// Entrega 15/03 - Ivan Quintero INI
		fechaNac = new JDateChooser();
		fechaNac.setBounds(149, 231, 89, 20);
		panel.add(fechaNac);
		// Entrega 15/03 - Ivan Quintero FIN
		
		btnEditarLocalidad = new JButton("Editar");
		btnEditarLocalidad.addActionListener(this.controlador);
		btnEditarLocalidad.setBounds(262, 160, 72, 23);
		panel.add(btnEditarLocalidad);
	
		btnEditarTContacto = new JButton("Editar");
		btnEditarTContacto.addActionListener(this.controlador);
		btnEditarTContacto.setBounds(262, 260, 72, 23);
		panel.add(btnEditarTContacto);
		
		/*JCalendar fechaNacimiento = new JCalendar();
		fechaNacimiento.setBounds(130, 230, 200, 20);
		panel.add(fechaNacimiento);*/
		
		bindTiposContactos();
		bindLocalidades();
		
		// Entrega 15/03 - Dario Rick FIN
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}
	
	public JTextField getTxtCalle()
	{
		return textCalle;
	}
	
	public JTextField getTxtAltura()
	{
		return textAltura;
	}

	public JTextField getTxtPiso()
	{
		return textPiso;
	}
	
	public JTextField getTxtDepto()
	{
		return textDepto;
	}
	
	public LocalidadDTO getLocalidad()
	{
		return (LocalidadDTO) comboLocalidad.getSelectedItem();
	}
	
	public TPersonaDTO getTipoPersona()
	{
		return (TPersonaDTO) comboTipoContacto.getSelectedItem();
	}
	
	public JComboBox<LocalidadDTO> getCboLocalidad()
	{
		return comboLocalidad;
	}
	
	public JTextField getTxtEmail()
	{
		return textEmail;
	}
	
	public void AddItemComboLocalidad(LocalidadDTO item){
		this.comboLocalidad.addItem(item);
		this.comboLocalidad.setSelectedItem(item);
	}
	
	public void AddItemComboTContacto(TPersonaDTO item){
		this.comboTipoContacto.addItem(item);
		this.comboLocalidad.setSelectedItem(item);
	}
	
	public JTextField getTextCalle() {
		return textCalle;
	}

	public void setTextCalle(String textCalle) {
		this.textCalle.setText(textCalle);
	}

	public JTextField getTextAltura() {
		return textAltura;
	}

	public void setTextAltura(String textAltura) {
		this.textAltura.setText(textAltura);
	}

	public JTextField getTextPiso() {
		return textPiso;
	}

	public void setTextPiso(String textPiso) {
		this.textPiso.setText(textPiso);
	}

	public JTextField getTextDepto() {
		return textDepto;
	}

	public void setTextDepto(String textDepto) {
		this.textDepto.setText(textDepto);
	}

	public JTextField getTextEmail() {
		return textEmail;
	}

	public void setTextEmail(String textEmail) {
		this.textEmail.setText(textEmail);
	}
	
	public void setTxtNombre(String txtNombre) {
		this.txtNombre.setText(txtNombre);
	}

	public void setTxtTelefono(String txtTelefono) {
		this.txtTelefono.setText(txtTelefono);
	}

	/*public void setFechaNac(JDateChooser fechaNac) {
		this.fechaNac = fechaNac;
	}*/

	public void setFechaNac(Date fechaNac) {
		this.fechaNac.setDate(fechaNac);
	}
	public JDateChooser getFechaNac()
	{
		return fechaNac;
	}
	
	public String getTipoContacto()
	{
		return comboTipoContacto.getSelectedItem().toString();
	}
	
	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}
	
	public void setIdPersona(int i){
		this.idPersona = i;
	}
	
	public int getIdPersona()
	{
		return this.idPersona;
	}
	
	// Entrega 15/03 - Dario Rick INI
	public JButton getBtnEditarTContacto() 
	{
		return btnEditarTContacto;
	}
	public JButton getBtnEditarLocalidad() 
	{
		return btnEditarLocalidad;
	}
	
	public void bindLocalidades() {
		comboLocalidad.removeAllItems();
		for(LocalidadDTO elemento : controlador.obtenerLocalidades())
			comboLocalidad.addItem(elemento);
	}

	public void bindTiposContactos() {
		comboTipoContacto.removeAllItems();
		for(TPersonaDTO elemento : controlador.obtenerTiposContactos())
			comboTipoContacto.addItem(elemento);
	}
	
	// Entrega 15/03 - Dario Rick FIN
	
	
}

