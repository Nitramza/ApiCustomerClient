package com.mx.apicustomerclient.bussines.dto;

public class SalidaDto {
    private String folio;
    private Object resultado;

    public SalidaDto() {
    }

    public SalidaDto(String folio, Object resultado) {
        this.folio = folio;
        this.resultado = resultado;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Object getResultado() {
        return resultado;
    }

    public void setResultado(Object resultado) {
        this.resultado = resultado;
    }
}
