package com.mx.apicustomerclient.dao.repository;

import com.mx.apicustomerclient.bussines.value.BussinesValue;
import com.mx.apicustomerclient.dao.dto.DaoDto;
import com.mx.apicustomerclient.dao.dto.InfoJdbcDto;
import com.mx.apicustomerclient.dao.dto.InputOutputDto;
import com.mx.apicustomerclient.dao.dto.ParametroDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.Collections;
import java.util.Map;

@Repository
public class SqlDao implements IDao {
	private static final Logger LOG = LogManager.getLogger(SqlDao.class);

	@Qualifier("dsSql")
	public JdbcTemplate jdbcTemplate;

	public SqlDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Map<String, Object> ejecutar(DaoDto daoVo) {
		final SimpleJdbcCall simpleJdbcCall = iniciarJdbc(this.jdbcTemplate, daoVo.getInfoJdbc());
		daoVo.getListaParametros().forEach((ParametroDto parametro) -> {
			int sqlType;
			switch (parametro.getTipo()) {
			case "boolean" -> sqlType = Types.BOOLEAN;
			case "int", "integer" -> sqlType = Types.INTEGER;
			default -> sqlType = Types.VARCHAR;
			}

			if (parametro.isInput()) {
				simpleJdbcCall.declareParameters(new SqlParameter(parametro.getNombre(), sqlType));
			} else {
				simpleJdbcCall.declareParameters(new SqlOutParameter(parametro.getNombre(), sqlType));
			}
		});
		final MapSqlParameterSource sqlParameter = obtenerMapaValores(daoVo.getListaInputOutput());
		simpleJdbcCall.returningResultSet(daoVo.getInfoJdbc().getResultName(), daoVo.getRowMapper());
		try {
			return simpleJdbcCall.execute(sqlParameter);
		} catch (RuntimeException e) {
			final String metodo = e.getStackTrace()[BussinesValue.INT_VALOR_CERO].getMethodName();
			LOG.error(BussinesValue.STR_FROMATO_ERROR.formatted(metodo, e));
		}
		return Collections.emptyMap();

	}

	@Override
	public SimpleJdbcCall iniciarJdbc(JdbcTemplate jdbcTemplate, InfoJdbcDto infoJdbcModel) {
		final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		simpleJdbcCall.withSchemaName(infoJdbcModel.getSchemaName());
		simpleJdbcCall.withCatalogName(infoJdbcModel.getCatalogName());
		simpleJdbcCall.withProcedureName(infoJdbcModel.getProcedureName());
		return simpleJdbcCall;
	}

	@Override
	public MapSqlParameterSource obtenerMapaValores(Iterable<InputOutputDto> ioModel) {
		final MapSqlParameterSource sqlParameter = new MapSqlParameterSource();
		ioModel.forEach(io -> sqlParameter.addValue(io.getNombre(), io.getValor()));
		return sqlParameter;
	}
}
