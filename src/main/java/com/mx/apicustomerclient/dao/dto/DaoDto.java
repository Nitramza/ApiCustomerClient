package com.mx.apicustomerclient.dao.dto;

import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;


public class DaoDto {
	private InfoJdbcDto infoJdbc;
	private List<ParametroDto> listaParametrosVo;
	private List<InputOutputDto> listaInputOutput;
	private RowMapper<Object> rowMapper;

	public DaoDto() {
	}

	public DaoDto(InfoJdbcDto infoJdbc, List<ParametroDto> listaParametroVo, List<InputOutputDto> listaInputOutput,
				  RowMapper<Object> rowMapper) {
		this.infoJdbc = infoJdbc;
		this.listaParametrosVo = new ArrayList<>(listaParametroVo);
		this.listaInputOutput = new ArrayList<>(listaInputOutput);
		this.rowMapper = rowMapper;
	}

	public InfoJdbcDto getInfoJdbc() {
		return this.infoJdbc;
	}

	public void setInfoJdbc(InfoJdbcDto infoJdbc) {
		this.infoJdbc = infoJdbc;
	}

	public List<ParametroDto> getListaParametros() {
		return new ArrayList<>(this.listaParametrosVo);
	}

	public void setListaParametros(List<ParametroDto> listaParametrosVo) {
		this.listaParametrosVo = new ArrayList<>(listaParametrosVo);
	}

	public List<InputOutputDto> getListaInputOutput() {
		return new ArrayList<>(this.listaInputOutput);
	}

	public void setListaInputOutput(List<InputOutputDto> listaInputOutput) {
		this.listaInputOutput = new ArrayList<>(listaInputOutput);
	}

	public RowMapper<Object> getRowMapper() {
		return rowMapper;
	}

	public void setRowMapper(RowMapper<Object> rowMapper) {
		this.rowMapper = rowMapper;
	}
}
