package com.mx.apicustomerclient.bussines.until;

import com.mx.apicustomerclient.bussines.model.OrdenModel;
import com.mx.apicustomerclient.bussines.value.BussinesValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;
import com.google.gson.Gson;

@Service
public class UntilService {
    private static final Logger LOG = LogManager.getLogger(UntilService.class);
    private static final String STR_PREFIJO_FOLIO = "LYV";
    private static final int INT_VALOR_TRES = 3;
    public static final String STR_FORMATO_FECHA_FOLIO = "yyyyMMddHHmmssS";
    public static final String STR_FORMATO_FOLIO = "%s%s%s";
    private static final DateTimeFormatter SQL_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]");
    private static final double TASA_IVA = 0.16;

    public String generarFolio() {
        final String cadena = String.valueOf(
                Math.abs((UUID.fromString(UUID.randomUUID().toString()).hashCode()) + BussinesValue.INT_VALOR_UNO));
        final Integer aleatorio = Integer.parseInt(cadena.substring(BussinesValue.INT_VALOR_CERO, INT_VALOR_TRES));
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STR_FORMATO_FECHA_FOLIO);
        final String fechaFolio = LocalDateTime.now().format(formatter);
        return  STR_FORMATO_FOLIO.formatted(STR_PREFIJO_FOLIO, fechaFolio, aleatorio.toString());
    }

    public String convertirObjetoAjson(Object objeto) {
        try {
            final Gson gson = new Gson();
            return gson.toJson(objeto);
        }
        catch (RuntimeException e) {
            final String metodo = e.getStackTrace()[BussinesValue.INT_VALOR_CERO].getMethodName();
            LOG.error(BussinesValue.STR_FROMATO_ERROR.formatted(metodo, e));
        }
        return BussinesValue.STR_VACIO;
    }

    public LocalDateTime convertirFecha(String fechaStr) {
        if (fechaStr == null) return null;
        try {
            return LocalDateTime.parse(fechaStr, SQL_FORMATTER);
        } catch (DateTimeParseException e) {
            final String metodo = e.getStackTrace()[BussinesValue.INT_VALOR_CERO].getMethodName();
            LOG.error(BussinesValue.STR_FROMATO_ERROR.formatted(metodo, e));
            return null;
        }
    }

    public double calcularSubtotal(List<OrdenModel> ordenes) {
        double subtotal = 0.0;
        if (ordenes != null) {
            for (OrdenModel item : ordenes) {
                double precio = item.getPrecioUnitario();
                double cantidad =item.getCantidad();

                double importeLinea = precio*cantidad;

                subtotal = subtotal+importeLinea;
            }
        }
        return Math.round(subtotal* 100.0) / 100.0;
    }

    public double calcularIva(double subtotal) {
        final double iva = (subtotal * TASA_IVA);
        return Math.round(iva * 100.0) / 100.0;
    }

    public double calcularTotal(double subtotal, double iva) {
        final double total = (subtotal + iva);
        return Math.round(total * 100.0) / 100.0;
    }
}
