package com.mx.apicustomerclient.dao.model;

public class RespuestaBdModel {

	private int exito;
	private String mensaje;
	private String identificador;

	public RespuestaBdModel() {
		this.exito = 0;
		this.mensaje = "";
		this.identificador = "";
	}

	public RespuestaBdModel(int exito, String mensaje, String identificador) {
		this.exito = exito;
		this.mensaje = mensaje;
		this.identificador = identificador;
	}

	public int getExito() {
		return exito;
	}

	public void setExito(int exito) {
		this.exito = exito;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	@Override
	public String toString() {
		return "RespuestaBdModel [exito=" + exito + ", mensaje=" + mensaje + ", identificador=" + identificador + "]";
	}

}
