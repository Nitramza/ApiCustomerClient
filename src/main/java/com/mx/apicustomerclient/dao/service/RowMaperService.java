package com.mx.apicustomerclient.dao.service;

import com.mx.apicustomerclient.bussines.value.BussinesValue;
import com.mx.apicustomerclient.dao.model.RespuestaBdModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;

@Service
public class RowMaperService {
	private static final Logger LOG = LogManager.getLogger(RowMaperService.class);

	private static final String STR_OUTPUT_EXITO = "PAIEXITO";
	private static final String STR_OUTPUT_MENSAJE = "PACMENSAJE";

	public RowMapper<Object> obtenerMapeoId(String nombreIdentificador) {
		try {
			return (ResultSet resultSet, int rowNum) -> new RespuestaBdModel(resultSet.getInt(STR_OUTPUT_EXITO),
					resultSet.getString(STR_OUTPUT_MENSAJE),
					resultSet.getString(nombreIdentificador));
		} catch (RuntimeException e) {
			final String metodo = e.getStackTrace()[BussinesValue.INT_VALOR_CERO].getMethodName();
			LOG.error(BussinesValue.STR_FROMATO_ERROR.formatted(metodo, e));
		}
		return null;

	}

	public RowMapper<Object> obtenerMapeoSimple() {
		try {
			return (ResultSet resultSet, int rowNum) -> new RespuestaBdModel(resultSet.getInt(STR_OUTPUT_EXITO),
					resultSet.getString(STR_OUTPUT_MENSAJE), BussinesValue.STR_VACIO);
		} catch (RuntimeException e) {
			final String metodo = e.getStackTrace()[BussinesValue.INT_VALOR_CERO].getMethodName();
			LOG.error(BussinesValue.STR_FROMATO_ERROR.formatted(metodo, e));
		}
		return null;
	}

}
