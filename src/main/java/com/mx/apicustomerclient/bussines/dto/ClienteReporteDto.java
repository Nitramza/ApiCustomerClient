package com.mx.apicustomerclient.bussines.dto;

public class ClienteReporteDto extends ClienteDto{
    private String idCliente;

    public ClienteReporteDto() {
    }

    public ClienteReporteDto(String nombreCompleto, String correoElectronico, String telefono, String idCliente) {
        super(nombreCompleto, correoElectronico, telefono);
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
}
