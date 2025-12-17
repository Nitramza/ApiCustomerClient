package com.mx.apicustomerclient.bussines.model;

import org.springframework.http.HttpStatus;

public class RespuestaModel {
    private HttpStatus estatus;
    private String folio;
    private Object resultado;

    public RespuestaModel() {
    }

    public RespuestaModel(HttpStatus estatus, String folio, Object resultado) {
        this.estatus = estatus;
        this.folio = folio;
        this.resultado = resultado;
    }

    public RespuestaModel(String folio, Object resultado) {
        this.folio = folio;
        this.resultado = resultado;
    }

    public HttpStatus getEstatus() {
        return estatus;
    }

    public void setEstatus(HttpStatus estatus) {
        this.estatus = estatus;
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

    @Override
    public String toString() {
        return "RespuestaModel{" +
                "estatus=" + estatus +
                ", folio='" + folio + '\'' +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
