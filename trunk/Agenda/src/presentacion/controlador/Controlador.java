package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Agenda;
import persistencia.conexion.Config;
import persistencia.conexion.Encrypter;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaAdminDB;
//Entrega 15/03 - Dario Rick INI
import presentacion.vista.VentanaAdminLocalidad;
import presentacion.vista.VentanaAdminTContacto;
import presentacion.vista.VentanaEditarLocalidad;
import presentacion.vista.VentanaEditarPersona;
import presentacion.vista.VentanaEditarTContacto;
//Entrega 15/03 - Dario Rick FIN
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.DomicilioDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.ReporteDTO;
import dto.TPersonaDTO;

public class Controlador implements ActionListener {
	private VentanaAdminDB vistaDB;
	private Vista vista;
	private List<PersonaDTO> personas_en_tabla;
	private VentanaPersona ventanaPersona;
	// Entrega 15/03 - Dario Rick INI
	private VentanaAdminTContacto ventanaAdminTContacto;
	private List<TPersonaDTO> tiposcontactos_en_tabla;
	private VentanaAdminLocalidad ventanaAdminLocalidad;
	private List<LocalidadDTO> localidades_en_tabla;
	// Entrega 15/03 - Dario Rick FIN
	private VentanaEditarLocalidad ventanaEditarLocalidad;
	private VentanaEditarTContacto ventanaEditarTContacto;
	private VentanaEditarPersona ventanaEditarPersona;
	private Agenda agenda;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(this);
		this.vista.getBtnBorrar().addActionListener(this);
		this.vista.getBtnReporte().addActionListener(this);
		this.vista.getBtnEditar().addActionListener(this);
		this.vista.getBtnModificarBaseDe().addActionListener(this);
		this.agenda = agenda;
		this.vistaDB = new VentanaAdminDB();
		this.vistaDB.getBtnAceptar().addActionListener(this);
		this.vistaDB.getBtnCancelar().addActionListener(this);
		this.personas_en_tabla = null;
	}

	public void inicializar() {
		if(this.agenda.sistemaIniciado()){
			this.llenarTabla();
		}
		else{
			this.cargarDatos();
		}
	}
	
	private void cargarDatos() {
		JTextField user = vistaDB.getTxtUser();
		JTextField pass = vistaDB.getTxtPasword();
		JTextField dbname = vistaDB.getTxtDBName();
		JTextField port = vistaDB.getTxtPort();
		
		try {
			user.setText(Encrypter.Desencriptar(Config.getValorParametro("mysql_user")));
			pass.setText(Encrypter.Desencriptar(Config.getValorParametro("mysql_pass")));
			dbname.setText(Encrypter.Desencriptar(Config.getValorParametro("mysql_dbname")));
			port.setText(Encrypter.Desencriptar(Config.getValorParametro("mysql_port")));
			this.vistaDB.setTxtUser(user);
			this.vistaDB.setTxtPasword(pass);
			this.vistaDB.setTxtDBName(dbname);
			this.vistaDB.setTxtPort(port);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.vistaDB.show();
		
	}

	private void llenarTabla() {
		System.out.println("Llenar tabla");
		this.vista.getModelPersonas().setRowCount(0); // Para vaciar la tabla
		this.vista.getModelPersonas().setColumnCount(0);
		this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());

		this.personas_en_tabla = agenda.obtenerPersonas();
		for (int i = 0; i < this.personas_en_tabla.size(); i++) {
			Object[] fila = { this.personas_en_tabla.get(i).getNombre(), this.personas_en_tabla.get(i).getTelefono(),
					this.personas_en_tabla.get(i).getDomicilio().getCalle(), this.personas_en_tabla.get(i).getDomicilio().getAltura(),
					this.personas_en_tabla.get(i).getDomicilio().getPiso(), this.personas_en_tabla.get(i).getDomicilio().getDepto(),
					this.personas_en_tabla.get(i).getDomicilio().getLocalidad().getNombre_Localidad(), this.personas_en_tabla.get(i).getEmail(),
					this.personas_en_tabla.get(i).getFecha_Nacimiento(),
					this.personas_en_tabla.get(i).getTipoPersona() };
			this.vista.getModelPersonas().addRow(fila);
		}
		this.vista.show();
	}

	// Entrega 15/03 - Dario Rick INI
	private void llenarTablaLocalidades() {
		System.out.println("Llenar tabla localidades");
		this.ventanaAdminLocalidad.getModelLocalidades().setRowCount(0); // Para
																			// vaciar
																			// la
																			// tabla
		this.ventanaAdminLocalidad.getModelLocalidades();
		this.ventanaAdminLocalidad.getModelLocalidades()
				.setColumnIdentifiers(this.ventanaAdminLocalidad.getNombreColumnas());

		this.localidades_en_tabla = agenda.obtenerLocalidades();
		for (int i = 0; i < this.localidades_en_tabla.size(); i++) {
			Object[] fila = { this.localidades_en_tabla.get(i).toString() };
			this.ventanaAdminLocalidad.getModelLocalidades().addRow(fila);
		}
		// this.ventanaEditarLocalidad.show();
	}
	
	@SuppressWarnings("unused")
	private String asignarSignoZodiacal(Date date) throws ParseException{
		String ret = "";
		Calendar instance = Calendar.getInstance();
	    instance.setTime(date);
        int año = instance.get(Calendar.YEAR);
        
        
	    SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
	    formateador.format(date);	    
	    Date fecha = formateador.parse(formateador.format(date));
	    
	    Date AriesI = formateador.parse("21/03/"+año);
		Date AriesF = formateador.parse("20/04/"+año);
		Date TauroI = formateador.parse("21/04/"+año);
		Date TauroF = formateador.parse("21/05/"+año);
		Date GeminisI = formateador.parse("22/05/"+año);
		Date GeminisF = formateador.parse("21/06/"+año);
		Date CancerI = formateador.parse("22/06/"+año);
		Date CancerF = formateador.parse("23/07/"+año);
		Date LeoI = formateador.parse("24/07/"+año);
		Date LeoF = formateador.parse("23/08/"+año);
		Date VirgoI = formateador.parse("24/08/"+año);
		Date VirgoF = formateador.parse("23/09/"+año);
		Date LibraI = formateador.parse("24/09/"+año);
		Date LibraF = formateador.parse("23/10/"+año);
		Date EscorpioI = formateador.parse("24/10/"+año);
		Date EscorpioF = formateador.parse("22/11/"+año);
		Date SagitarioI = formateador.parse("23/11/"+año);
		Date SagitarioF = formateador.parse("21/12/"+año);
		Date CapricornioI = formateador.parse("22/12/"+año);
		Date CapricornioF = formateador.parse("20/01/"+año);
		Date AcuarioI = formateador.parse("21/01/"+año);
		Date AcuarioF = formateador.parse("19/02/"+año);
		Date PiscisI = formateador.parse("20/02/"+año);
		Date PiscisF = formateador.parse("20/03/"+año);
		
        if (date.equals(AriesI) || date.equals(AriesF) || (date.after(AriesI) && date.before(AriesF)))
            return "Aries";

        else if (date.equals(TauroI) || date.equals(TauroF) || (date.after(TauroI) && date.before(TauroF)))
            return "Tauro";

        else if (date.equals(GeminisI) || date.equals(GeminisF) || (date.after(GeminisI) && date.before(GeminisF)))
            return "Geminis";

        else if (date.equals(CancerI) || date.equals(CancerF) || (date.after(CancerI) && date.before(CancerF)))
        	return "Cancer";

        else if (date.equals(LeoI) || date.equals(LeoF) || (date.after(LeoI) && date.before(LeoF)))
            return "Leo";

        else if (date.equals(VirgoI) || date.equals(VirgoF) || (date.after(VirgoI) && date.before(VirgoF)))
            return "Virgo";

        else if (date.equals(LibraI) || date.equals(LibraF) || (date.after(LibraI) && date.before(LibraF)))
            return "Libra";

        else if (date.equals(EscorpioI) || date.equals(EscorpioF) || (date.after(EscorpioI) && date.before(EscorpioF)))
            return "Escorpio";

        else if (date.equals(SagitarioI) || date.equals(SagitarioF) || (date.after(SagitarioI) && date.before(SagitarioF)))
            return "Sagitario";

        else if (date.equals(CapricornioI) || date.equals(CapricornioF) || (date.after(CapricornioI) && date.before(CapricornioF)))
            return "Capricornio";

        else if (date.equals(AcuarioI) || date.equals(AcuarioF) || (date.after(AcuarioI) && date.before(AcuarioF)))
            return "Acuario";

        else if (date.equals(PiscisI) || date.equals(PiscisF) || (date.after(PiscisI) && date.before(PiscisF)))
            return "Piscis";    
        
        return ret;
	}

	private void llenarTablaTContacto() {
		System.out.println("Llenar tabla TContacto");
		this.ventanaAdminTContacto.getModelTiposContacto().setRowCount(0); // Para
																			// vaciar
																			// la
																			// tabla
		this.ventanaAdminTContacto.getModelTiposContacto();
		this.ventanaAdminTContacto.getModelTiposContacto()
				.setColumnIdentifiers(this.ventanaAdminTContacto.getNombreColumnas());

		this.tiposcontactos_en_tabla = agenda.obtenerTiposContactos();
		for (int i = 0; i < this.tiposcontactos_en_tabla.size(); i++) {
			Object[] fila = { this.tiposcontactos_en_tabla.get(i).toString() };
			this.ventanaAdminTContacto.getModelTiposContacto().addRow(fila);
		}
	}
	// Entrega 15/03 - Dario Rick FIN

	public void actionPerformed(ActionEvent e) {
		// Vista principal
		
		if (this.vistaDB!=null){
			if (e.getSource() == this.vistaDB.getBtnAceptar()){
				this.agenda.escribirInicio();
				this.agenda.setUserAndPass(this.vistaDB.getTxtUser().getText(), this.vistaDB.getTxtPasword().getText());
				JOptionPane.showMessageDialog(this.ventanaAdminLocalidad, "Base de datos modificada con exito");
				this.vistaDB.hide();
				this.llenarTabla();
			}
			else if(e.getSource() == this.vistaDB.getBtnCancelar()){
				this.agenda.escribirInicio();
				this.vistaDB.hide();
				this.llenarTabla();
			}
		}
		
		if (this.vista != null) {
			if (e.getSource() == this.vista.getBtnModificarBaseDe()){
				this.vista.hide();
				this.cargarDatos();
			} else if (e.getSource() == this.vista.getBtnAgregar()) {
				this.ventanaPersona = new VentanaPersona(this);
			} else if (e.getSource() == this.vista.getBtnBorrar()) {
				int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				for (int fila : filas_seleccionadas) {
					this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
				}

				this.llenarTabla();

			} else if (e.getSource() == this.vista.getBtnReporte()) {
				List<PersonaDTO> personas = agenda.obtenerPersonas();
				List<ReporteDTO> personasReporte = new ArrayList<ReporteDTO>();
				for (PersonaDTO persona : personas){
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					String fecha_nac = df.format(persona.getFecha_Nacimiento().getTime());
					try {
						String signo = this.asignarSignoZodiacal(persona.getFecha_Nacimiento());
						personasReporte.add(new ReporteDTO(persona.getNombre(),persona.getTelefono(),
								persona.getDomicilio().getCalle()+" "+persona.getDomicilio().getAltura(),
								persona.getDomicilio().getLocalidad().getNombre_Localidad(),persona.getEmail(),fecha_nac,
								persona.getTipo_Persona().getTipo(),signo));
						System.out.println(this.asignarSignoZodiacal(persona.getFecha_Nacimiento()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					System.out.println(fecha_nac);
				}
				Collections.sort(personasReporte);
				ReporteAgenda reporte = new ReporteAgenda(personasReporte);
				reporte.mostrar();
			}

			else if (e.getSource() == this.vista.getBtnEditar()) {
				int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				if (filas_seleccionadas.length != 1) {
					JOptionPane.showMessageDialog(this.ventanaAdminLocalidad, "Debe seleccionar una fila");
				} else {
					PersonaDTO persona = this.personas_en_tabla.get(filas_seleccionadas[0]);
					this.ventanaEditarPersona = new VentanaEditarPersona(this);
					this.ventanaEditarPersona.setTxtNombre(persona.getNombre());
					this.ventanaEditarPersona.setTxtTelefono(persona.getTelefono());
					this.ventanaEditarPersona.setTextCalle(persona.getDomicilio().getCalle());
					this.ventanaEditarPersona.setTextAltura(String.valueOf(persona.getDomicilio().getAltura()));
					this.ventanaEditarPersona.setTextPiso(String.valueOf(persona.getDomicilio().getPiso()));
					this.ventanaEditarPersona.setTextDepto(persona.getDomicilio().getDepto());
					
					System.out.println(persona.getDomicilio().getLocalidad().getFecha_Baja());
					if (persona.getDomicilio().getLocalidad().getFecha_Baja() != null)
					{
						this.ventanaEditarPersona.AddItemComboLocalidad(persona.getDomicilio().getLocalidad());
					}
					
					
					this.ventanaEditarPersona.setTextEmail(persona.getEmail());
					java.sql.Date fecha_nac = new java.sql.Date(persona.getFecha_Nacimiento().getTime());
					this.ventanaEditarPersona.setFechaNac(fecha_nac);
					//this.ventanaEditarPersona.AddItemComboTContacto(persona.getTipoPersona());
					this.ventanaEditarPersona.setIdPersona(persona.getIdPersona());
				}

			}
		}

		// Vista Editar Personas
		if (this.ventanaEditarPersona != null) {
			List<LocalidadDTO> localidades = this.obtenerLocalidades();
			List<TPersonaDTO> tiposContactos = this.obtenerTiposContactos();
			// Date fechaNac = this.ventanaPersona.getFechaNac().getDate();
			if (this.ventanaEditarPersona != null
					&& e.getSource() == this.ventanaEditarPersona.getBtnAgregarPersona()) {
				if (this.ventanaEditarPersona.getTxtNombre().getText().toString().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaEditarPersona, "Falta completar Nombre y Apellido");
				} else if (this.ventanaEditarPersona.getTxtTelefono().getText().toString().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaEditarPersona, "Falta completar Telefono");
				}

				else if (this.ventanaEditarPersona.getTxtCalle().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaEditarPersona, "Falta completar calle");
				}

				else if (this.ventanaEditarPersona.getTxtAltura().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaEditarPersona, "Falta completar altura");
				}

				else if (this.ventanaEditarPersona.getTxtPiso().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaEditarPersona, "Falta completar altura");
				}

				else if (this.ventanaEditarPersona.getTxtDepto().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaEditarPersona, "Falta completar altura");
				}

				else if (localidades.size() == 0) {
					// && !this.ventanaPersona.getTipoContacto().equals(null)){
					JOptionPane.showMessageDialog(this.ventanaEditarPersona, "Debe agregar una localidad");
				}

				else if (this.ventanaEditarPersona.getTxtEmail().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaEditarPersona, "Debe agregar un Email");
				}

				else if (this.ventanaEditarPersona.getFechaNac().getDate() == null) {
					JOptionPane.showMessageDialog(this.ventanaEditarPersona, "Debe agregar una fecha de nacimiento");
				}

				else if (tiposContactos.size() == 0) {
					JOptionPane.showMessageDialog(this.ventanaEditarPersona, "Debe agregar un tipo de contacto");
				}

				else {
					
					int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
					PersonaDTO persona = this.personas_en_tabla.get(filas_seleccionadas[0]);
					
					System.out.println("idPersona  editar: " + persona.getIdPersona());
					
					// tipo_Persona y localidad existen, puesto que estan bindeados. Tengo que armar el domicilio
					DomicilioDTO domicilio = new DomicilioDTO( 
							this.ventanaEditarPersona.getTxtCalle().getText(), 
							(this.ventanaEditarPersona.getTxtAltura().getText() != "") ? Integer.parseInt(this.ventanaEditarPersona.getTxtAltura().getText()) : null,
							(this.ventanaEditarPersona.getTxtPiso().getText() != "") ? Integer.parseInt(this.ventanaEditarPersona.getTxtPiso().getText()) : null,
									this.ventanaEditarPersona.getTxtDepto().getText(), 
							this.ventanaEditarPersona.getLocalidad()
							);
					
		
					domicilio.setIdDomicilio(persona.getDomicilio().getIdDomicilio());
					
					
					PersonaDTO nuevaPersona = new PersonaDTO(
							this.ventanaEditarPersona.getTxtNombre().getText(), 
							ventanaEditarPersona.getTxtTelefono().getText(), 
							domicilio,
							this.ventanaEditarPersona.getTxtEmail().getText(), 
							new java.sql.Date(
									this.ventanaEditarPersona.getFechaNac().getDate().getTime()), 
							this.ventanaEditarPersona.getTipoPersona() 
							);
					
					nuevaPersona.setIdPersona(persona.getIdPersona());
				
					this.agenda.editarPersona(nuevaPersona);
					this.llenarTabla();
					this.ventanaEditarPersona.dispose();
				}
			} else if (this.ventanaEditarPersona != null
					&& e.getSource() == this.ventanaEditarPersona.getBtnEditarTContacto()) {
				this.ventanaAdminTContacto = new VentanaAdminTContacto(this);
				this.llenarTablaTContacto();
			} else if (this.ventanaEditarPersona != null
					&& e.getSource() == this.ventanaEditarPersona.getBtnEditarLocalidad()) {
				this.ventanaAdminLocalidad = new VentanaAdminLocalidad(this);
				this.llenarTablaLocalidades();
			}
		}

		// Vista ABM Personas
		if (this.ventanaPersona != null) {
			List<LocalidadDTO> localidades = this.obtenerLocalidades();
			List<TPersonaDTO> tiposContactos = this.obtenerTiposContactos();
			// Date fechaNac = this.ventanaPersona.getFechaNac().getDate();
			if (this.ventanaPersona != null && e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) {
				if (this.ventanaPersona.getTxtNombre().getText().toString().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaPersona, "Falta completar Nombre y Apellido");
				} else if (this.ventanaPersona.getTxtTelefono().getText().toString().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaPersona, "Falta completar Telefono");
				}

				else if (this.ventanaPersona.getTxtCalle().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaPersona, "Falta completar calle");
				}

				else if (this.ventanaPersona.getTxtAltura().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaPersona, "Falta completar altura");
				}

				else if (this.ventanaPersona.getTxtPiso().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaPersona, "Falta completar altura");
				}

				else if (this.ventanaPersona.getTxtDepto().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaPersona, "Falta completar altura");
				}

				else if (localidades.size() == 0) {
					// && !this.ventanaPersona.getTipoContacto().equals(null)){
					JOptionPane.showMessageDialog(this.ventanaPersona, "Debe agregar una localidad");
				}

				else if (this.ventanaPersona.getTxtEmail().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaPersona, "Debe agregar un Email");
				}

				else if (this.ventanaPersona.getFechaNac().getDate() == null) {
					JOptionPane.showMessageDialog(this.ventanaPersona, "Debe agregar una fecha de nacimiento");
				}

				else if (tiposContactos.size() == 0) {
					JOptionPane.showMessageDialog(this.ventanaPersona, "Debe agregar un tipo de contacto");
				}

				else {
					/*
					PersonaDTO nuevaPersona = new PersonaDTO(0, this.ventanaPersona.getTxtNombre().getText(),
							ventanaPersona.getTxtTelefono().getText(), 0, this.ventanaPersona.getTxtCalle().getText(),
							(this.ventanaPersona.getTxtAltura().getText() != "")
									? Integer.parseInt(this.ventanaPersona.getTxtAltura().getText()) : null,
							(this.ventanaPersona.getTxtPiso().getText() != "")
									? Integer.parseInt(this.ventanaPersona.getTxtPiso().getText()) : null,
							this.ventanaPersona.getTxtDepto().getText(), 0, this.ventanaPersona.getLocalidad(),
							this.ventanaPersona.getTxtEmail().getText(), this.ventanaPersona.getFechaNac().getDate(), 0,
							this.ventanaPersona.getTipoContacto());
					*/
					
					// tipo_Persona y localidad existen, puesto que estan bindeados. Tengo que armar el domicilio
					DomicilioDTO domicilio = new DomicilioDTO( 
							this.ventanaPersona.getTxtCalle().getText(), 
							(this.ventanaPersona.getTxtAltura().getText() != "") ? Integer.parseInt(this.ventanaPersona.getTxtAltura().getText()) : null,
							(this.ventanaPersona.getTxtPiso().getText() != "") ? Integer.parseInt(this.ventanaPersona.getTxtPiso().getText()) : null,
									this.ventanaPersona.getTxtDepto().getText(), 
							this.ventanaPersona.getLocalidad()
							);
					
					PersonaDTO nuevaPersona = new PersonaDTO(
							this.ventanaPersona.getTxtNombre().getText(), 
							ventanaPersona.getTxtTelefono().getText(), 
							domicilio,
							this.ventanaPersona.getTxtEmail().getText(), 
							this.ventanaPersona.getFechaNac().getDate(), 
							this.ventanaPersona.getTipoPersona() 
							);
					
					this.agenda.agregarPersona(nuevaPersona);
					java.sql.Date fecha_nac = new java.sql.Date(this.ventanaPersona.getFechaNac().getDate().getTime());
					System.out.println(fecha_nac);
					this.llenarTabla();
					this.ventanaPersona.dispose();
				}
			} else if (this.ventanaPersona != null && e.getSource() == this.ventanaPersona.getBtnEditarTContacto()) {
				this.ventanaAdminTContacto = new VentanaAdminTContacto(this);
				this.llenarTablaTContacto();
			} else if (this.ventanaPersona != null && e.getSource() == this.ventanaPersona.getBtnEditarLocalidad()) {
				this.ventanaAdminLocalidad = new VentanaAdminLocalidad(this);
				this.llenarTablaLocalidades();
			}

		}

		// Vista ABM Localidades
		if (this.ventanaAdminLocalidad != null) {
			if (e.getSource() == this.ventanaAdminLocalidad.getBtnAgregarLocalidad()) {
				if (this.ventanaAdminLocalidad.getTxtLocalidad().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaAdminLocalidad, "Localidad no puede estar vacio");
				} else {
					/*
					 * LocalidadDTO nuevaLocalidad = new LocalidadDTO( -1,
					 * ventanaEditarLocalidad.getTxtLocalidad().getText(), null
					 * );
					 */
					this.agenda.agregarLocalidad(ventanaAdminLocalidad.getTxtLocalidad().getText());
					this.llenarTablaLocalidades(); // Verificar!!
					this.ventanaPersona.bindLocalidades();
					// this.ventanaPersona.getCboLocalidad().setSelectedIndex(this.ventanaPersona.getCboLocalidad().getItemCount());
					this.ventanaAdminLocalidad.dispose();
				}
			}

			else if (e.getSource() == this.ventanaAdminLocalidad.getBtnEliminarLocalidad()) {
				int[] filas_seleccionadas = this.ventanaAdminLocalidad.getTablaLocalidades().getSelectedRows();
				for (int fila : filas_seleccionadas) {
					this.agenda.eliminarLocalidad(this.localidades_en_tabla.get(fila));
				}

				this.llenarTablaLocalidades();
				this.ventanaPersona.bindLocalidades();
			}

			else if (e.getSource() == this.ventanaAdminLocalidad.getBtnEditarLocalidad()) {
				int[] filas_seleccionadas = this.ventanaAdminLocalidad.getTablaLocalidades().getSelectedRows();
				if (filas_seleccionadas.length > 1) {
					JOptionPane.showMessageDialog(this.ventanaAdminLocalidad, "Debe seleccionar una fila");
				} else if (filas_seleccionadas.length == 0) {
					JOptionPane.showMessageDialog(this.ventanaAdminLocalidad, "Debe seleccionar una fila");
				} else {
					LocalidadDTO editar = this.localidades_en_tabla.get(filas_seleccionadas[0]);
					this.ventanaEditarLocalidad = new VentanaEditarLocalidad(this);
					this.ventanaEditarLocalidad.setTxtLocalidad1(editar.getNombre_Localidad());
				}
			} else if (this.ventanaEditarLocalidad != null
					&& e.getSource() == this.ventanaEditarLocalidad.getBtnAceptarEdicion()) {
				if (this.ventanaEditarLocalidad.getTxtLocalidad1().getText()
						.equals(this.ventanaEditarLocalidad.getTxtLocalidad2().getText())) {

					JOptionPane.showMessageDialog(this.ventanaEditarLocalidad, "Los campos no pueden coincidir");
				} else {
					this.agenda.editarLocalidad(this.ventanaEditarLocalidad.getTxtLocalidad1().getText(),
							this.ventanaEditarLocalidad.getTxtLocalidad2().getText());
					this.llenarTablaLocalidades();
					this.ventanaPersona.bindLocalidades();
					this.ventanaEditarLocalidad.dispose();
				}
			}
		}

		// Vista ABM Tipos Contactos
		if (this.ventanaAdminTContacto != null) {
			if (e.getSource() == this.ventanaAdminTContacto.getBtnAgregarTContacto()) {
				System.out.println("Agregando contacto");
				if (this.ventanaAdminTContacto.getTxtTipo().getText().equals("")) {
					JOptionPane.showMessageDialog(this.ventanaAdminTContacto, "Tipo de contacto no puede estar vacio");
				} else {
					this.agenda.agregarTContacto(ventanaAdminTContacto.getTxtTipo().getText());
					this.llenarTablaTContacto(); // Verificar!!
					if (this.ventanaPersona != null)
						this.ventanaPersona.bindTiposContactos();
					if (this.ventanaEditarPersona != null)
						this.ventanaEditarPersona.bindTiposContactos();

					this.ventanaAdminTContacto.dispose();
				}
			}

			else if (e.getSource() == this.ventanaAdminTContacto.getBtnEliminarTContacto()) {
				System.out.println("A eliminar");
				int[] filas_seleccionadas = this.ventanaAdminTContacto.getTablaTContactos().getSelectedRows();
				for (int fila : filas_seleccionadas) {
					this.agenda.eliminarTContacto(this.tiposcontactos_en_tabla.get(fila));
				}
				
				this.ventanaPersona.bindTiposContactos();
				this.llenarTablaTContacto();
			}

			else if (e.getSource() == this.ventanaAdminTContacto.getBtnEditarTContacto()) {
				int[] filas_seleccionadas = this.ventanaAdminTContacto.getTablaTContactos().getSelectedRows();
				if (filas_seleccionadas.length > 1) {
					JOptionPane.showMessageDialog(this.ventanaAdminLocalidad, "Debe seleccionar una fila");
				} else if (filas_seleccionadas.length == 0) {
					JOptionPane.showMessageDialog(this.ventanaAdminLocalidad, "Debe seleccionar una fila");
				} else {
					TPersonaDTO editar = this.tiposcontactos_en_tabla.get(filas_seleccionadas[0]);
					this.ventanaEditarTContacto = new VentanaEditarTContacto(this);
					this.ventanaEditarTContacto.setTxtTContacto1(editar.getTipo());
				}
			} else if (this.ventanaEditarTContacto != null
					&& e.getSource() == this.ventanaEditarTContacto.getBtnAceptarEdicion()) {
				if (this.ventanaEditarTContacto.getTxtTContacto1().getText()
						.equals(this.ventanaEditarTContacto.getTxtTContacto2().getText())) {

					JOptionPane.showMessageDialog(this.ventanaEditarLocalidad, "Los campos no pueden coincidir");
				} else {
					this.agenda.editarTContacto(this.ventanaEditarTContacto.getTxtTContacto1().getText(),
							this.ventanaEditarTContacto.getTxtTContacto2().getText());
					this.llenarTablaTContacto();
					this.ventanaPersona.bindTiposContactos();
					this.ventanaEditarTContacto.dispose();
				}
			}
		}

	}

	// Entrega 15/03 - Dario Rick INI
	public List<LocalidadDTO> obtenerLocalidades() {
		return agenda.obtenerLocalidades();
	}

	public List<TPersonaDTO> obtenerTiposContactos() { //Parametrizado como TPersonaDTO
		return agenda.obtenerTiposContactos();
	}
	// Entrega 15/03 - Dario Rick FIN
}
