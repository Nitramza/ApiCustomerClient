package com.mx.apicustomerclient.bussines.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReporteOrdenDto {
    private int folio;
    private LocalDateTime fecha;
    private double subtotal;
    private double iva;
    private double total;
    private ClienteReporteDto cliente;
    private List<ItemDto> productos;
    public ReporteOrdenDto() {
    }

    public ReporteOrdenDto(int folio, LocalDateTime fecha, double subtotal, double iva, double total, ClienteReporteDto cliente, List<ItemDto> productos) {
        this.folio = folio;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.cliente = cliente;
        this.productos = productos;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ClienteReporteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteReporteDto cliente) {
        this.cliente = cliente;
    }

    public List<ItemDto> getProductos() {
        return productos;
    }

    public void setProductos(List<ItemDto> productos) {
        this.productos = productos;
    }
}
