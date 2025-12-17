package com.mx.apicustomerclient.dao.value;

public final class DaoValue {
	public static final int INT_VALOR_METODO = 0;
	public static final String STR_TIPO_CHAR = "VARCHAR";
	public static final String STR_TIPO_INT = "INT";
	public static final String STR_FROMATO_DAO_ERROR = "Dao error %s:%s";

	public static final String STR_FORMAT_DRIVER = "%s%s";
	public static final String STR_COMPLEMENT_DRIVER = ";encrypt=true;trustServerCertificate=true;";

	public static final String STR_NAME_BD = "Bd_Customers";
	public static final String STR_SCHEMA_BD = "VENTAS";
	public static final String STR_SP_REGISTRA_CLIENTE = "SPAPIREGISTRACLIENTE";
	public static final String STR_SP_BUSQUEDA_CLIENTE_ID = "SPAPICONSULTACLIENTEPORID";
	public static final String STR_SP_REGISTRA_ORDEN= "SPAPIREGISTRAORDEN";
	public static final String STR_SP_BUSQUEDA_ORDEN = "SPAPICONSULTAORDENDETALLE";

	private DaoValue() {
		throw new IllegalStateException("DaoValue");
	}

}
