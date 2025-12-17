package com.mx.apicustomerclient.dao.dto;

public class InputOutputDto {
	private String nombre;
	private Object valor;

	public InputOutputDto() {
	}

	public InputOutputDto(String nombre, Object valor) {
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Object getValor() {
		return this.valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

}
