package com.mx.apicustomerclient.bussines.service;

import com.mx.apicustomerclient.bussines.dto.EntradaOrdersDto;
import com.mx.apicustomerclient.bussines.model.*;
import com.mx.apicustomerclient.bussines.persistence.RegistrarOrdenPersistence;
import com.mx.apicustomerclient.bussines.until.UntilService;
import com.mx.apicustomerclient.bussines.until.ValidacionesCamposService;
import com.mx.apicustomerclient.bussines.value.BussinesValue;
import com.mx.apicustomerclient.dao.model.RespuestaBdModel;
import com.mx.apicustomerclient.dao.value.DaoValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrarOrdenService {
    private static final Logger LOG = LogManager.getLogger(RegistrarOrdenService.class);

    @Autowired
    RegistrarOrdenPersistence registrarOrdenPersistence;

    @Autowired
    ValidacionesCamposService validacionesCamposService;

    @Autowired
    UntilService untilService;

    public RespuestaModel registrarOrden(EntradaOrdersDto entradaOrdersDto){
        LOG.info("::::::::::::Inicia registro de Orden:::::::::::");
        final RespuestaModel respuestaModel = new RespuestaModel();
        respuestaModel.setFolio(untilService.generarFolio());
        respuestaModel.setEstatus(HttpStatus.BAD_REQUEST);
        RespuestaBdModel respuestaBdModel = new RespuestaBdModel();
        try {
            final ValidacionModel validacionModel = validacionesCamposService.validarParametrosEntradaOrden(entradaOrdersDto);
            if (validacionModel.isValido()){
                final DatosOrdenModel datosOrdenModel = this.convertirAModel(entradaOrdersDto);
                final ValidacionModel validacionStock = validacionesCamposService.validarStock(datosOrdenModel.getOrdenes());
                if (validacionStock.isValido()){
                    LOG.info("::::::::::::Stock disponible para la orden:::::::::::");
                    respuestaBdModel = registrarOrdenPersistence.registraorderPersistence(datosOrdenModel);
                    if(respuestaBdModel.getExito() == BussinesValue.INT_VALOR_UNO){
                        LOG.info("::::::::::::Registro de Orden Exitoso:::::::::::");
                        respuestaModel.setEstatus(HttpStatus.CREATED);
                        respuestaModel.setResultado(respuestaBdModel.getIdentificador());
                    }else{
                        LOG.info("::::::::::::Error al insertar::::::::::");
                        respuestaModel.setEstatus(HttpStatus.NOT_FOUND);
                        respuestaModel.setResultado(respuestaBdModel.getMensaje());
                    }
                }else{
                    LOG.info("::::::::::::Stock no disponible para la orden:::::::::::");
                    respuestaModel.setEstatus(HttpStatus.CONFLICT);
                    respuestaModel.setResultado(validacionStock.getDetalleError());
                    return respuestaModel;
                }
            }else{
                LOG.info("::::::::::::Error validaciones::::::::::");
                respuestaModel.setResultado(validacionModel.getDetalleError());
            }
        } catch (RuntimeException e) {
            final String metodo = e.getStackTrace()[DaoValue.INT_VALOR_METODO].getMethodName();
            LOG.error(DaoValue.STR_FROMATO_DAO_ERROR.formatted(metodo, e));
        }

        return respuestaModel;
    }

    public DatosOrdenModel convertirAModel(EntradaOrdersDto entradaOrdersDto) {

        final DatosOrdenModel datosOrdenModel = new DatosOrdenModel();

        datosOrdenModel.setCustomerId(Integer.parseInt(entradaOrdersDto.getCustomerId()));

        if (entradaOrdersDto.getOrdenes() != null) {
            List<OrdenModel> listaModel = entradaOrdersDto.getOrdenes().stream()
                    .map(ordenDto -> {
                        OrdenModel ordenModel = new OrdenModel();
                        ordenModel.setSku(ordenDto.getnSku());
                        ordenModel.setCantidad(Integer.parseInt(ordenDto.getCantidad()));
                        ordenModel.setPrecioUnitario(Double.parseDouble(ordenDto.getPrecioUnitario()));
                        return ordenModel;
                    })
                    .collect(Collectors.toList());
            final double subtotal = untilService.calcularSubtotal(listaModel);
            final double iva = untilService.calcularIva(subtotal);
            final double total = untilService.calcularTotal(subtotal, iva);
            datosOrdenModel.setOrdenes(listaModel);
            datosOrdenModel.setIva(iva);
            datosOrdenModel.setSubtotal(subtotal);
            datosOrdenModel.setTotal(total);
        } else {
            datosOrdenModel.setOrdenes(Collections.emptyList());
        }

        return datosOrdenModel;
    }
}
