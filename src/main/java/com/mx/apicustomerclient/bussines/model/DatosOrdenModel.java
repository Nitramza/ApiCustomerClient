package com.mx.apicustomerclient.bussines.model;

import com.mx.apicustomerclient.bussines.dto.OrdenDto;

import java.util.List;

public class DatosOrdenModel {
    private int customerId;
    private double iva;
    private double subtotal;
    private double total;
    private List<OrdenModel> ordenes;

    public DatosOrdenModel() {
    }

    public DatosOrdenModel(int customerId, double iva, double subtotal, double total, List<OrdenModel> ordenes) {
        this.customerId = customerId;
        this.iva = iva;
        this.subtotal = subtotal;
        this.total = total;
        this.ordenes = ordenes;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrdenModel> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<OrdenModel> ordenes) {
        this.ordenes = ordenes;
    }
}
