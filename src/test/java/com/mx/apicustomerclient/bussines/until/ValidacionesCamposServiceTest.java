package com.mx.apicustomerclient.bussines.until;

import com.mx.apicustomerclient.bussines.model.OrdenModel;
import com.mx.apicustomerclient.bussines.model.ValidacionModel;
import com.mx.apicustomerclient.bussines.service.InventarioService;
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

class ValidacionesCamposServiceTest {

    @InjectMocks
    ValidacionesCamposService vCamposService;

    @Mock
    FormatoService formatoService;

    @Mock
    InventarioService inventarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("VALIDACION DE ATRIBUTOS: NOMBRE DE USUARIO")
    void testValidarNombre() {
        String cadena = null;
        ValidacionModel resultado = vCamposService.validarNombre(cadena);
        assertFalse(resultado.isValido(), "Es FALSE porque el valor no es valido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_NULL_NOMBRE, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "  ";
        resultado = vCamposService.validarNombre(cadena);
        assertFalse(resultado.isValido(), "Es FALSE porque el valor no es valido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_NULL_NOMBRE, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "Juande123";
        when(formatoService.validarFormatoNombres(cadena)).thenReturn(false);
        resultado = vCamposService.validarNombre(cadena);
        assertFalse(resultado.isValido(), "Es false porque el valor es invalido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_FORM_NOMBRE, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "Juan";
        when(formatoService.validarFormatoNombres(cadena)).thenReturn(true);
        resultado = vCamposService.validarNombre(cadena);
        assertTrue(resultado.isValido(), "Es true porque el valor es valido");
    }

    @Test
    @DisplayName("VALIDACION DE ATRIBUTOS: TELEFONO DE USUARIO")
    void testValidarTelefono() {
        String cadena = null;
        ValidacionModel resultado = vCamposService.validarTelefono(cadena);
        assertFalse(resultado.isValido(), "Es FALSE porque el valor no es valido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_NULL_TELEFONO, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "  ";
        resultado = vCamposService.validarTelefono(cadena);
        assertFalse(resultado.isValido(), "Es FALSE porque el valor no es valido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_NULL_TELEFONO, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "Juande123";
        when(formatoService.validarFormatoTelefono(cadena)).thenReturn(false);
        resultado = vCamposService.validarTelefono(cadena);
        assertFalse(resultado.isValido(), "Es false porque el valor es invalido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_FORM_TELEFONO, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "Juan";
        when(formatoService.validarFormatoTelefono(cadena)).thenReturn(true);
        resultado = vCamposService.validarTelefono(cadena);
        assertTrue(resultado.isValido(), "Es true porque el valor es valido");
    }

    @Test
    @DisplayName("VALIDACION DE ATRIBUTOS: EMAIL DE USUARIO")
    void testValidarEmail() {
        String cadena = null;
        ValidacionModel resultado = vCamposService.validarEmail(cadena);
        assertFalse(resultado.isValido(), "Es FALSE porque el valor no es valido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_NULL_CORREO, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "  ";
        resultado = vCamposService.validarEmail(cadena);
        assertFalse(resultado.isValido(), "Es FALSE porque el valor no es valido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_NULL_CORREO, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "coreo123.com";
        when(formatoService.validarFormatoCorreo(cadena)).thenReturn(false);
        resultado = vCamposService.validarEmail(cadena);
        assertFalse(resultado.isValido(), "Es false porque el valor es invalido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_FORM_CORREO, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "correo@correo.com";
        when(formatoService.validarFormatoCorreo(cadena)).thenReturn(true);
        resultado = vCamposService.validarEmail(cadena);
        assertTrue(resultado.isValido(), "Es true porque el valor es valido");
    }

    @Test
    @DisplayName("VALIDACION DE ATRIBUTOS: SKU DE PRODUCTO")
    void testValidarSku() {
        String cadena = null;
        ValidacionModel resultado = vCamposService.validarSku(cadena);
        assertFalse(resultado.isValido(), "Es FALSE porque el valor no es valido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_NULL_SKU, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "  ";
        resultado = vCamposService.validarSku(cadena);
        assertFalse(resultado.isValido(), "Es FALSE porque el valor no es valido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_NULL_SKU, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "MOTO33SAS234";
        when(formatoService.validarFormatoSku(cadena)).thenReturn(false);
        resultado = vCamposService.validarSku(cadena);
        assertFalse(resultado.isValido(), "Es false porque el valor es invalido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_FORM_SKU, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "CASC14525";
        when(formatoService.validarFormatoSku(cadena)).thenReturn(true);
        resultado = vCamposService.validarSku(cadena);
        assertTrue(resultado.isValido(), "Es true porque el valor es valido");
    }

    @Test
    @DisplayName("VALIDACION DE ATRIBUTOS: CANTIDADES DE PRODUCTO")
    void testValidarCantidades() {
        String cadena = null;
        ValidacionModel resultado = vCamposService.validarCantidades(cadena);
        assertFalse(resultado.isValido(), "Es FALSE porque el valor no es valido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_NULL_CANTIDAD, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "  ";
        resultado = vCamposService.validarCantidades(cadena);
        assertFalse(resultado.isValido(), "Es FALSE porque el valor no es valido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_NULL_CANTIDAD, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "1Z";
        when(formatoService.validarFormatoCantidades(cadena)).thenReturn(false);
        resultado = vCamposService.validarCantidades(cadena);
        assertFalse(resultado.isValido(), "Es false porque el valor es invalido");
        assertEquals(BussinesValue.INT_CUATROCIENTOS, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals(MensajeErrorValue.STR_ERR_USR_FORM_CANTIDAD, resultado.getDetalleError(),
                "Mensaje de error debe ser igual");

        cadena = "200.00";
        when(formatoService.validarFormatoCantidades(cadena)).thenReturn(true);
        resultado = vCamposService.validarCantidades(cadena);
        assertTrue(resultado.isValido(), "Es true porque el valor es valido");
    }

    @Test
    @DisplayName("VALIDACION DE STOCK DE PRODUCTO")
    void testValidarStock() {

        List<OrdenModel> ordenes = List.of(
                new OrdenModel("CASC12345678", 2, 100.0),
                new OrdenModel("MOTO123445458", 1, 200.0),
                new OrdenModel("MOTO12345678", 3, 50.0)
        );
        when(inventarioService.consultarStock(ordenes.get(0).getSku())).thenReturn(0);
        ValidacionModel resultado = vCamposService.validarStock(ordenes);
        assertFalse(resultado.isValido(), "Es FALSE porque no hay stock suficiente");
        assertFalse(resultado.isValido(), "Es FALSE porque no hay stock suficiente");
        assertEquals(BussinesValue.INT_CUATROCIENTOS_NUEVE, resultado.getCodigo(), "Codigo de error debe ser igual");
        assertEquals("No hay stock suficiente para el producto con SKU: CASC12345678",
                resultado.getDetalleError(), "Mensaje de error debe ser igual");

        when(inventarioService.consultarStock(ordenes.get(0).getSku())).thenReturn(100);
        when(inventarioService.consultarStock(ordenes.get(1).getSku())).thenReturn(100);
        when(inventarioService.consultarStock(ordenes.get(2).getSku())).thenReturn(100);
        ValidacionModel resultadoExito = vCamposService.validarStock(ordenes);
        assertTrue(resultadoExito.isValido(), "Es TRUE porque hay stock suficiente");
    }
}
