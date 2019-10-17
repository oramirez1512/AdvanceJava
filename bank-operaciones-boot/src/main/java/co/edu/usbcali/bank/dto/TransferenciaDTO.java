package co.edu.usbcali.bank.dto;

import java.math.BigDecimal;

public class TransferenciaDTO {
	
	public String cuenIdOrigen;
	public String CuenIdDestino;
	public BigDecimal valor;
	public String usuUsuario;
	public String getCuenIdOrigen() {
		return cuenIdOrigen;
	}
	public void setCuenIdOrigen(String cuenIdOrigen) {
		this.cuenIdOrigen = cuenIdOrigen;
	}
	public String getCuenIdDestino() {
		return CuenIdDestino;
	}
	public void setCuenIdDestino(String cuenIdDestino) {
		CuenIdDestino = cuenIdDestino;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getUsuUsuario() {
		return usuUsuario;
	}
	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}
	public TransferenciaDTO(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario) {
		super();
		this.cuenIdOrigen = cuenIdOrigen;
		CuenIdDestino = cuenIdDestino;
		this.valor = valor;
		this.usuUsuario = usuUsuario;
	}
	public TransferenciaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
