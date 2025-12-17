package com.mx.apicustomerclient.bussines.service;

import com.mx.apicustomerclient.bussines.dto.ClienteDto;
import com.mx.apicustomerclient.bussines.model.ClienteModel;
import com.mx.apicustomerclient.bussines.model.RespuestaModel;
import com.mx.apicustomerclient.bussines.persistence.BusquedaClientePersistence;
import com.mx.apicustomerclient.bussines.until.FormatoService;
import com.mx.apicustomerclient.bussines.until.UntilService;
import com.mx.apicustomerclient.dao.value.DaoValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BuscarClienteService {
    private static final Logger LOG = LogManager.getLogger(BuscarClienteService.class);

    @Autowired
    FormatoService formatoService;

    @Autowired
    UntilService untilService;

    @Autowired
    BusquedaClientePersistence busquedaClientePersistence;

    public RespuestaModel consultarCustomerId(String idCliente){
        LOG.info("::::::::::::Inicia Busqueda de Cliente:::::::::::");
        final RespuestaModel respuestaModel = new RespuestaModel();
        respuestaModel.setFolio(untilService.generarFolio());
        respuestaModel.setEstatus(HttpStatus.BAD_REQUEST);
        ClienteModel clienteModel = new ClienteModel();
        final ClienteDto clienteDto = new ClienteDto();
        try {
            if (formatoService.validarNumeroEntero(idCliente)){
                clienteModel = busquedaClientePersistence.buscarlientePersistence(Integer.parseInt(idCliente));
                if(!formatoService.esObjetoVacio(clienteModel)){
                    LOG.info("::::::::::::Consulta exitosa:::::::::::");
                    respuestaModel.setEstatus(HttpStatus.OK);
                    clienteDto.setNombreCompleto(clienteModel.getNombre());
                    clienteDto.setCorreoElectronico(clienteModel.getEmail());
                    clienteDto.setTelefono(clienteModel.getTelefono());
                    respuestaModel.setResultado(clienteDto);
                }else{
                    LOG.info("::::::::::::Sin datos::::::::::");
                    respuestaModel.setEstatus(HttpStatus.NOT_FOUND);
                    respuestaModel.setResultado("No se encontraron datos para el ID de cliente proporcionado" );
                }

            }else{
                LOG.info("::::::::::::Error validaciones::::::::::");
                respuestaModel.setResultado("El ID de cliente debe ser un numero entero valido");
            }
        } catch (RuntimeException e) {
            final String metodo = e.getStackTrace()[DaoValue.INT_VALOR_METODO].getMethodName();
            LOG.error(DaoValue.STR_FROMATO_DAO_ERROR.formatted(metodo, e));
        }

        return respuestaModel;
    }

}
