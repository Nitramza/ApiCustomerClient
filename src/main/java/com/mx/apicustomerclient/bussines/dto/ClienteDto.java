package com.mx.apicustomerclient.bussines.dto;

public class ClienteDto {
    private String nombreCompleto;
    private String correoElectronico;
    private String telefono;

    public ClienteDto() {
    }

    public ClienteDto(String nombreCompleto, String correoElectronico, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
