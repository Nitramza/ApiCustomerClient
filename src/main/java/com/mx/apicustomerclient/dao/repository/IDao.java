package com.mx.apicustomerclient.dao.repository;

import com.mx.apicustomerclient.dao.dto.DaoDto;
import com.mx.apicustomerclient.dao.dto.InfoJdbcDto;
import com.mx.apicustomerclient.dao.dto.InputOutputDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface IDao {
	Map<String, Object> ejecutar(DaoDto daoVo);

	SimpleJdbcCall iniciarJdbc(JdbcTemplate jdbcTemplate, InfoJdbcDto infoJdbcModel);

	MapSqlParameterSource obtenerMapaValores(Iterable<InputOutputDto> ioModel);
}
