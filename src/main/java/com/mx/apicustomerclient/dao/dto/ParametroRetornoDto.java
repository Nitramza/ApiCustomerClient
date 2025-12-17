package com.mx.apicustomerclient.dao.dto;

public class ParametroRetornoDto {
	private String banderaExito;
	private String codigoEstatus;
	private String mensajeEstatus;
	private String identificador;
	
	public ParametroRetornoDto() {
	
	}
	
	public ParametroRetornoDto(String banderaExito, String codigoEstatus, String mensajeEstatus, String identificador) {
		this.banderaExito = banderaExito;
		this.codigoEstatus = codigoEstatus;
		this.mensajeEstatus = mensajeEstatus;
		this.identificador = identificador;
	}
	
	public String getBanderaExito() {
		return banderaExito;
	}
	
	public void setBanderaExito(String banderaExito) {
		this.banderaExito = banderaExito;
	}
	
	public String getCodigoEstatus() {
		return codigoEstatus;
	}
	
	public void setCodigoEstatus(String codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
	}
	
	public String getMensajeEstatus() {
		return mensajeEstatus;
	}
	
	public void setMensajeEstatus(String mensajeEstatus) {
		this.mensajeEstatus = mensajeEstatus;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	
	public void setIdentificador(String identificador){
		this.identificador = identificador;
	}	
}
