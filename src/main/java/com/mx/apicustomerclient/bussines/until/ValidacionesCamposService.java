package com.mx.apicustomerclient.bussines.until;

import com.mx.apicustomerclient.bussines.dto.EntradaOrdersDto;
import com.mx.apicustomerclient.bussines.dto.OrdenDto;
import com.mx.apicustomerclient.bussines.model.ClienteModel;
import com.mx.apicustomerclient.bussines.model.OrdenModel;
import com.mx.apicustomerclient.bussines.model.ValidacionModel;
import com.mx.apicustomerclient.bussines.service.InventarioService;
import com.mx.apicustomerclient.bussines.value.BussinesValue;
import com.mx.apicustomerclient.bussines.value.MensajeErrorValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidacionesCamposService {

    @Autowired
    FormatoService formatoService;

    @Autowired
    InventarioService inventarioService;

    public ValidacionModel validarNombre(String valor) {
        final ValidacionModel validacionModel = new ValidacionModel();
        validacionModel.setCodigo(BussinesValue.INT_CUATROCIENTOS);
        if (valor == null || valor.isBlank()) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_NULL_NOMBRE);
            return validacionModel;
        }
        if (!formatoService.validarFormatoNombres(valor)) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_FORM_NOMBRE);
            return validacionModel;
        }
        validacionModel.setValido(true);
        return validacionModel;
    }

    public ValidacionModel validarTelefono(String valor) {
        final ValidacionModel validacionModel = new ValidacionModel();
        validacionModel.setCodigo(BussinesValue.INT_CUATROCIENTOS);
        if (valor == null || valor.isBlank()) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_NULL_TELEFONO);
            return validacionModel;
        }
        if (!formatoService.validarFormatoTelefono(valor)) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_FORM_TELEFONO);
            return validacionModel;
        }
        validacionModel.setValido(true);
        return validacionModel;
    }

    public ValidacionModel validarEmail(String valor) {
        final ValidacionModel validacionModel = new ValidacionModel();
        validacionModel.setCodigo(BussinesValue.INT_CUATROCIENTOS);
        if (valor == null || valor.isBlank()) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_NULL_CORREO);
            return validacionModel;
        }
        if (!formatoService.validarFormatoCorreo(valor)) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_FORM_CORREO);
            return validacionModel;
        }
        validacionModel.setValido(true);
        return validacionModel;
    }

    public ValidacionModel validarSku(String valor) {
        final ValidacionModel validacionModel = new ValidacionModel();
        validacionModel.setCodigo(BussinesValue.INT_CUATROCIENTOS);
        if (valor == null || valor.isBlank()) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_NULL_SKU);
            return validacionModel;
        }
        if (!formatoService.validarFormatoSku(valor)) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_FORM_SKU);
            return validacionModel;
        }
        validacionModel.setValido(true);
        return validacionModel;
    }

    public ValidacionModel validarCantidades(String valor) {
        final ValidacionModel validacionModel = new ValidacionModel();
        validacionModel.setCodigo(BussinesValue.INT_CUATROCIENTOS);
        if (valor == null || valor.isBlank()) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_NULL_CANTIDAD);
            return validacionModel;
        }
        if (!formatoService.validarFormatoCantidades(valor)) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_FORM_CANTIDAD);
            return validacionModel;
        }
        validacionModel.setValido(true);
        return validacionModel;
    }

    public ValidacionModel validarId(String valor) {
        final ValidacionModel validacionModel = new ValidacionModel();
        validacionModel.setCodigo(BussinesValue.INT_CUATROCIENTOS);
        if (valor == null || valor.isBlank()) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_NULL_CUSTOMERID);
            return validacionModel;
        }
        if (!formatoService.validarNumeroEntero(valor)) {
            validacionModel.setDetalleError(MensajeErrorValue.STR_ERR_USR_FORM_CUSTOMERID);
            return validacionModel;
        }
        validacionModel.setValido(true);
        return validacionModel;
    }

    public ValidacionModel validarParametrosCliente(ClienteModel clienteModel) {
        ValidacionModel validacionModel = this.validarNombre(clienteModel.getNombre());
        if (!validacionModel.isValido()) {
            return validacionModel;
        }
        validacionModel = this.validarEmail(clienteModel.getEmail());
        if (!validacionModel.isValido()) {
            return validacionModel;
        }
        validacionModel = this.validarTelefono(clienteModel.getTelefono());
        if (!validacionModel.isValido()) {
            return validacionModel;
        }
        validacionModel.setValido(true);
        return validacionModel;
    }

    public ValidacionModel validarParametrosEntradaOrden(EntradaOrdersDto entradaOrdersDto) {
        ValidacionModel validacionModel = this.validarId(entradaOrdersDto.getCustomerId());
        if (!validacionModel.isValido()) {
            return validacionModel;
        }
        validacionModel = this.validarOrdenesSku(entradaOrdersDto.getOrdenes());
        if (!validacionModel.isValido()) {
            return validacionModel;
        }
        validacionModel.setValido(true);
        return validacionModel;
    }


    public ValidacionModel validarOrdenesSku(List<OrdenDto> ordenes) {
        ValidacionModel validacionModel = new ValidacionModel();
        validacionModel.setCodigo(BussinesValue.INT_CUATROCIENTOS);
        for(int i = 0; i < ordenes.size(); i++) {
            validacionModel = this.validarSku(ordenes.get(i).getnSku());
            if (!validacionModel.isValido()) {
                validacionModel.setDetalleError(
                        String.format(BussinesValue.STR_FORMATO_ORDEN, validacionModel.getDetalleError(), i + 1));
                return validacionModel;
            }
        }
        validacionModel = this.validarOrdenesCantidad(ordenes);
        if (!validacionModel.isValido()) {
            return validacionModel;
        }
        validacionModel.setValido(true);
        return validacionModel;
    }

    public ValidacionModel validarOrdenesCantidad(List<OrdenDto> ordenes) {
        ValidacionModel validacionModel = new ValidacionModel();
        validacionModel.setCodigo(BussinesValue.INT_CUATROCIENTOS);
        for(int i = 0; i < ordenes.size(); i++) {
            validacionModel = this.validarId(ordenes.get(i).getCantidad());
            if (!validacionModel.isValido()) {
                validacionModel.setDetalleError(
                        String.format(BussinesValue.STR_FORMATO_ORDEN, validacionModel.getDetalleError(), i + 1));
                return validacionModel;
            }
        }
        validacionModel = this.validarOrdenesPrecio(ordenes);
        if (!validacionModel.isValido()) {
            return validacionModel;
        }
        validacionModel.setValido(true);
        return validacionModel;
    }

    public ValidacionModel validarOrdenesPrecio(List<OrdenDto> ordenes) {
        ValidacionModel validacionModel = new ValidacionModel();
        validacionModel.setCodigo(BussinesValue.INT_CUATROCIENTOS);
        for(int i = 0; i < ordenes.size(); i++) {
            validacionModel = this.validarCantidades(ordenes.get(i).getPrecioUnitario());
            if (!validacionModel.isValido()) {
                validacionModel.setDetalleError(
                        String.format(BussinesValue.STR_FORMATO_ORDEN, validacionModel.getDetalleError(), i + 1));
                return validacionModel;
            }
        }
        validacionModel.setValido(true);
        return validacionModel;
    }

    public ValidacionModel validarStock(List<OrdenModel> ordenes) {
        ValidacionModel validacionModel = new ValidacionModel();
        validacionModel.setCodigo(BussinesValue.INT_CUATROCIENTOS_NUEVE);
        for(int i = 0; i < ordenes.size(); i++) {
            final int stockDisponible = inventarioService.consultarStock(ordenes.get(i).getSku());
            if (stockDisponible < ordenes.get(i).getCantidad()) {
                validacionModel.setDetalleError(
                        String.format(MensajeErrorValue.STR_ERR_STOCK, ordenes.get(i).getSku()));
                return validacionModel;
            }
        }
        validacionModel.setValido(true);
        return validacionModel;
    }
}
