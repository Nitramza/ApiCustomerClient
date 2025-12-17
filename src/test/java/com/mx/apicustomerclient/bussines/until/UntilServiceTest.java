package com.mx.apicustomerclient.bussines.until;

import com.mx.apicustomerclient.bussines.model.OrdenModel;
import com.mx.apicustomerclient.bussines.model.ValidacionModel;
import com.mx.apicustomerclient.bussines.value.BussinesValue;
import com.mx.apicustomerclient.bussines.value.MensajeErrorValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UntilServiceTest {
    @InjectMocks
    UntilService untilService;

    @Mock
    FormatoService formatoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("VALIDACION DE CALCULAR SUBTOTAL DE ORDENES")
    void testValidarCalcularSubtotal() {
        List<OrdenModel> ordenes = List.of(
                new OrdenModel("SKU1", 2, 100.0),
                new OrdenModel("SKU2", 1, 200.0),
                new OrdenModel("SKU3", 3, 50.0)
        );
        double subtotal = untilService.calcularSubtotal(ordenes);
        assertEquals(550.0, subtotal, "El subtotal calculado debe ser correcto");
    }

    @Test
    @DisplayName("VALIDACION DE CALCULAR TOTAL DE ORDENES CON IVA")
    void testValidarCalcularTotal() {
        final double iva = 150.0;
        final double subtotal = 550.0;
        double total = untilService.calcularTotal(subtotal, iva);
        assertEquals(700.0, total, "El total calculado debe ser correcto");
    }

    @Test
    @DisplayName("VALIDACION DE CALCULAR IVA")
    void testValidarCalcularIva() {
        final double iva = 0.16;
        final double subtotal = 550.0;
        double ivaTotal = untilService.calcularIva(subtotal);
        assertEquals((550.0 * 0.16), ivaTotal, "El iva total calculado debe ser correcto");
    }
}
