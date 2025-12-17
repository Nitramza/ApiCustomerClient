package com.mx.apicustomerclient.dao.dto;

public class ParametroDto {
	private String nombre;
	private String tipo;
	private boolean input;

	public ParametroDto() {
	}

	public ParametroDto(String nombre, String tipo, boolean input) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.input = input;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isInput() {
		return input;
	}

	public void setInput(boolean input) {
		this.input = input;
	}

	public boolean isOutput() {
		return !input;
	}
}
