package com.mx.apicustomerclient.security.dto;

public class CrediancialesDto {
    private String usuario;
    private String clave;

    public CrediancialesDto() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
