package com.mx.apicustomerclient.bussines.dto;

public class InventarioDto {
    private String sku;
    private int stockDisponible;

    public InventarioDto() {
    }
    public InventarioDto(String sku, int stockDisponible) {
        this.sku = sku;
        this.stockDisponible = stockDisponible;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    @Override
    public String toString() {
        return "InventarioDto{" +
                "sku='" + sku + '\'' +
                ", stockDisponible=" + stockDisponible +
                '}';
    }
}
