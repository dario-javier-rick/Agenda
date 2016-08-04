package dto;

public class DomicilioDTO {
	private int idDomicilio;
	private String Calle;
	private int Altura;
	private int Piso;
	private String Depto;
	private LocalidadDTO localidad;
	
	public DomicilioDTO(int idDomicilio, String Calle, int Altura, int Piso, String Depto, LocalidadDTO localidad)
	{
		this.idDomicilio = idDomicilio;
		this.Calle = Calle;
		this.Altura = Altura;
		this.Piso = Piso;
		this.Depto = Depto;
		this.localidad = localidad;
	}
	
	// Usar este constructor
	public DomicilioDTO(String Calle, int Altura, int Piso, String Depto, LocalidadDTO localidad)
	{
		this.Calle = Calle;
		this.Altura = Altura;
		this.Piso = Piso;
		this.Depto = Depto;
		this.localidad = localidad;
	}
	
	

	public int getIdDomicilio() {
		return idDomicilio;
	}
	public void setIdDomicilio(int idDomicilio) {
		this.idDomicilio = idDomicilio;
	}
	public String getCalle() {
		return Calle;
	}
	public void setCalle(String calle) {
		this.Calle = calle;
	}
	public int getAltura() {
		return Altura;
	}
	public void setAltura(int altura) {
		this.Altura = altura;
	}
	public int getPiso() {
		return Piso;
	}
	public void setPiso(int piso) {
		this.Piso = piso;
	}
	public String getDepto() {
		return Depto;
	}
	public void setDepto(String depto) {
		this.Depto = depto;
	}
	public LocalidadDTO getLocalidad() {
		return localidad;
	}
	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}
}
