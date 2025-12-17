package com.mx.apicustomerclient.bussines.model;

public class OrdenModel {
    private String sku;
    private int cantidad;
    private double precioUnitario;

    public OrdenModel() {
    }

    public OrdenModel(String sku, int cantidad, double precioUnitario) {
        this.sku = sku;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
