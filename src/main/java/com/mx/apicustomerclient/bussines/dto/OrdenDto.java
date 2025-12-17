package com.mx.apicustomerclient.bussines.dto;

public class OrdenDto {
    private String sku;
    private String cantidad;
    private String precioUnitario;

    public OrdenDto() {
    }

    public OrdenDto(String sku, String cantidad, String precioUnitario) {
        this.sku = sku;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getnSku() {
        return sku;
    }

    public void setnSku(String sku) {
        this.sku = sku;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
