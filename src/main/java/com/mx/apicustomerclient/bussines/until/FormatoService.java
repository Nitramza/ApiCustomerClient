package com.mx.apicustomerclient.bussines.until;

import com.mx.apicustomerclient.bussines.model.ValidacionModel;
import com.mx.apicustomerclient.bussines.value.BussinesValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FormatoService {
	private static final Logger LOG = LogManager.getLogger(FormatoService.class);

	protected static final String STR_ARROBA = "@";

	protected static final Pattern REGULAR_EXP_NOMBRES = Pattern
			.compile("^(?!.*([a-zA-ZÁÉÍÓÚáéíóúÑñ ])\\1{2})(?!.*(?i)(abc))[a-zA-ZÁÉÍÓÚáéíóúÑñ ]{3,50}$");
	protected static final Pattern REGULAR_EXP_TELEFONO = Pattern.compile("^\\d{8,12}$");
	protected static final Pattern REGULAR_EXP_CORREO = Pattern
			.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	protected static final Pattern REGULAR_EXP_SKU = Pattern
			.compile("^[A-Za-z]{4}\\d{1,10}$");
	protected static final Pattern REGULAR_EXP_CANTIDADES = Pattern
			.compile("^[0-9]+(\\.[0-9]{1,2})?$");

	public boolean validarNumeroEntero(String cadena) {
		boolean esValido = false;
		try {
			if (Integer.parseInt(cadena) >= 0) {
				esValido = true;
			}
		}
		catch (NumberFormatException e) {
			String metodo = e.getStackTrace()[BussinesValue.INT_VALOR_CERO].getMethodName();
			LOG.error(String.format(BussinesValue.STR_FROMATO_ERROR, metodo, e));
		}
		return esValido;
	}

	public boolean validarNumero(String cadena) {
		boolean esValido = false;
		try {
			if (Long.parseLong(cadena) >= 0) {
				esValido = true;
			}
		}
		catch (NumberFormatException e) {
			String metodo = e.getStackTrace()[BussinesValue.INT_VALOR_CERO].getMethodName();
			LOG.error(String.format(BussinesValue.STR_FROMATO_ERROR, metodo, e));
		}
		return esValido;
	}

	public boolean validarBooleano(String cadena) {
		boolean esValido = false;
		try {
			if ("true".equalsIgnoreCase(cadena) || "false".equalsIgnoreCase(cadena)) {
				esValido = true;
			}
		}
		catch (RuntimeException e) {
			String metodo = e.getStackTrace()[BussinesValue.INT_VALOR_CERO].getMethodName();
			LOG.error(String.format(BussinesValue.STR_FROMATO_ERROR, metodo, e));
		}
		return esValido;
	}

	public boolean validarFormatoNombres(String cadena) {
		boolean esCorrecto = false;
		Matcher mather = REGULAR_EXP_NOMBRES.matcher(cadena);
		if (mather.find()) {
			esCorrecto = true;
		}
		return esCorrecto;
	}

	public boolean validarFormatoTelefono(String cadena) {
		boolean esCorrecto = false;
		Matcher mather = REGULAR_EXP_TELEFONO.matcher(cadena);
		if (mather.find()) {
			esCorrecto = true;
		}
		return esCorrecto;
	}


	public boolean validarFormatoCorreo(String correo) {
		boolean esCorrecto = false;
		Matcher mather = REGULAR_EXP_CORREO.matcher(correo);
		if (Boolean.TRUE.equals(mather.find())) {
			String[] partes = correo.split(STR_ARROBA);
			if (partes.length == BussinesValue.INT_VALOR_DOS) {
				esCorrecto = true;
			}
		}
		return esCorrecto;
	}

	public boolean validarFormatoSku(String sku) {
		boolean esCorrecto = false;
		Matcher mather = REGULAR_EXP_SKU.matcher(sku);
		if (mather.find()) {
			esCorrecto = true;
		}
		return esCorrecto;
	}

	public boolean validarFormatoCantidades(String cantidad) {
		boolean esCorrecto = false;
		Matcher mather = REGULAR_EXP_CANTIDADES.matcher(cantidad);
		if (mather.find()) {
			esCorrecto = true;
		}
		return esCorrecto;
	}

	public boolean esObjetoVacio(Object obj) {
		boolean isVacio = true;
		try {
			if (obj == null) {
				return isVacio;
			}

			for (PropertyDescriptor pd : Introspector.getBeanInfo(obj.getClass(), Object.class).getPropertyDescriptors()) {
				final Object valor = pd.getReadMethod().invoke(obj);
				if (valor != null && (!(valor instanceof String) || !((String) valor).trim().isEmpty())) {
					isVacio = false;
					return isVacio;
				}
			}

		} catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
			isVacio = false;
			String metodo = e.getStackTrace()[BussinesValue.INT_VALOR_CERO].getMethodName();
			LOG.error(String.format(BussinesValue.STR_FROMATO_ERROR, metodo, e));
		}
		return isVacio;
	}
}
