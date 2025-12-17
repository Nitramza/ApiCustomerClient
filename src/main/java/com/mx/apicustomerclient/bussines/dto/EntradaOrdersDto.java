package com.mx.apicustomerclient.bussines.dto;

import java.util.List;

public class EntradaOrdersDto {
    private String customerId;
    private List<OrdenDto> ordenes;

    public EntradaOrdersDto() {
    }

    public EntradaOrdersDto(String customerId, List<OrdenDto> ordenes) {
        this.customerId = customerId;
        this.ordenes = ordenes;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrdenDto> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<OrdenDto> ordenes) {
        this.ordenes = ordenes;
    }
}
