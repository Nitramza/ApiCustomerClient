package com.mx.apicustomerclient.bussines.service;

import com.mx.apicustomerclient.bussines.model.ClienteModel;
import com.mx.apicustomerclient.bussines.model.RespuestaModel;
import com.mx.apicustomerclient.bussines.model.ValidacionModel;
import com.mx.apicustomerclient.bussines.persistence.RegistraClientPersistence;
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

@Service
public class RegistrarClienteService {
    private static final Logger LOG = LogManager.getLogger(RegistrarClienteService.class);

    @Autowired
    RegistraClientPersistence registraClientPersistence;

    @Autowired
    ValidacionesCamposService validacionesCamposService;

    @Autowired
    UntilService untilService;

    public RespuestaModel registrarCliente(ClienteModel clienteModel){
        LOG.info("::::::::::::Inicia registro de Cliente:::::::::::");
        final RespuestaModel respuestaModel = new RespuestaModel();
        respuestaModel.setFolio(untilService.generarFolio());
        respuestaModel.setEstatus(HttpStatus.BAD_REQUEST);
        RespuestaBdModel respuestaBdModel = new RespuestaBdModel();
        try {
                final ValidacionModel validacionModel = validacionesCamposService.validarParametrosCliente(clienteModel);
                if (validacionModel.isValido()){
                    respuestaBdModel = registraClientPersistence.registraClientePersistence(clienteModel);
                    if(respuestaBdModel.getExito() == BussinesValue.INT_VALOR_UNO){
                        LOG.info("::::::::::::Registro de Cliente Exitoso:::::::::::");
                        respuestaModel.setEstatus(HttpStatus.CREATED);
                        respuestaModel.setResultado(respuestaBdModel.getIdentificador());
                    }else{
                        LOG.info("::::::::::::Error al insertar::::::::::");
                        respuestaModel.setEstatus(HttpStatus.CONFLICT);
                        respuestaModel.setResultado(respuestaBdModel.getMensaje());
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
}
