package co.edu.usbcali.bank.dto;

import java.math.BigDecimal;

public class UserDTO {

	

	private String usuUsuario;

	private String activo;

	private String clave;

	private BigDecimal identificacion;

	private String nombre;
	
	private Long tiusId;


	public Long getTiusId() {
		return tiusId;
	}


	public void setTiusId(Long tiusId) {
		this.tiusId = tiusId;
	}


	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUsuUsuario() {
		return usuUsuario;
	}

	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public BigDecimal getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(BigDecimal identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	

}
