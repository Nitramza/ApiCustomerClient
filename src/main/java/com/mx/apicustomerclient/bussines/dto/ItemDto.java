package com.mx.apicustomerclient.bussines.dto;

public class ItemDto extends OrdenDto{
    private String importe;

    public ItemDto() {
    }

    public ItemDto(String sku, String cantidad, String precioUnitario, String importe) {
        super(sku, cantidad, precioUnitario);
        this.importe = importe;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }
}
