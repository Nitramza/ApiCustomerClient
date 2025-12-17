package com.mx.apicustomerclient.bussines.model;

public class ValidacionModel {
	private boolean valido;
	private String detalleError;	
	private int codigo;
		
	public ValidacionModel() {
		
	}

	public ValidacionModel(boolean valido, String detalleError, int codigo) {	
		this.valido = valido;
		this.detalleError = detalleError;
		this.codigo = codigo;
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public String getDetalleError() {
		return detalleError;
	}

	public void setDetalleError(String detalleError) {
		this.detalleError = detalleError;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
