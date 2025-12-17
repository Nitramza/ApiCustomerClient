package com.mx.apicustomerclient.bussines.value;

public class BussinesValue {
    public static final String STR_FROMATO_ERROR ="%s:%s";
    public static final String STR_VACIO="";
    public static final int INT_VALOR_CERO=0;
    public static final int INT_VALOR_UNO=1;
    public static final int INT_VALOR_DOS=2;
    public static final boolean BOOL_VALOR_TRUE = true ;
    public static final boolean BOOL_VALOR_FALSE = false;
    public static final int INT_DOSCIENTOS = 200;
    public static final int INT_DOSCIENTOS_UNO = 201;
    public static final int INT_CUATROCIENTOS = 400;
    public static final int INT_CUATROCIENTOS_UNO = 401;
    public static final int INT_CUATROCIENTOS_CUATRO = 404;
    public static final int INT_CUATROCIENTOS_NUEVE = 409;
    public static final int INT_QUINIENTOS = 500;

    public static final String STR_FORMATO_ORDEN="%s en la orden número %d";

    private BussinesValue() {
        throw new IllegalStateException("BussinesValue");
    }
}
