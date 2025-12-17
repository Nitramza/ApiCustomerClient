package com.mx.apicustomerclient.bussines.value;

public final class MensajeErrorValue {

	public static final String STR_ERR_USR_NULL_NOMBRE = "No se recibió el nombre de cliente";
	public static final String STR_ERR_USR_FORM_NOMBRE = "El formato del nombre de cliente no es válido";

	public static final String STR_ERR_USR_NULL_CORREO = "No se recibió el email del cliente";
	public static final String STR_ERR_USR_FORM_CORREO = "El formato de email del cliente no es válido";

	public static final String STR_ERR_USR_NULL_TELEFONO = "No se recibió el número de teléfono";
	public static final String STR_ERR_USR_FORM_TELEFONO= "El formato  número de teléfono no es válido";

	public static final String STR_ERR_USR_NULL_SKU = "No se recibió el código SKU del producto";
	public static final String STR_ERR_USR_FORM_SKU = "El formato del SKU no es válido (debe iniciar con 4 letras seguido de números)";

	public static final String STR_ERR_USR_NULL_CUSTOMERID = "No se recibió el identificador del cliente";
	public static final String STR_ERR_USR_FORM_CUSTOMERID = "El identificador del cliente debe ser un número entero válido";

	public static final String STR_ERR_USR_NULL_CANTIDAD = "No se recibió la cantidad del producto";
	public static final String STR_ERR_USR_FORM_CANTIDAD = "La cantidad debe ser un número entero positivo";
	public static final String STR_ERR_STOCK = "No hay stock suficiente para el producto con SKU: %s";

	private MensajeErrorValue() {		
		throw new IllegalStateException("MensajeErrorValue");
	}
}
