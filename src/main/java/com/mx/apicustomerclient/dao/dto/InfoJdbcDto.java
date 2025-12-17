package com.mx.apicustomerclient.dao.dto;

public class InfoJdbcDto {
	private String catalogName;
	private String schemaName;
	private String procedureName;
	private String resultName;

	public InfoJdbcDto() {

	}

	public InfoJdbcDto(String catalogName, String schemaName, String procedureName, String resultName) {
		this.catalogName = catalogName;
		this.schemaName = schemaName;
		this.procedureName = procedureName;
		this.resultName = resultName;
	}


	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

}
