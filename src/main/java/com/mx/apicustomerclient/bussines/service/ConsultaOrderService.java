package com.mx.apicustomerclient.bussines.service;

import com.mx.apicustomerclient.bussines.dto.*;
import com.mx.apicustomerclient.bussines.model.ClienteModel;
import com.mx.apicustomerclient.bussines.model.RespuestaModel;
import com.mx.apicustomerclient.bussines.persistence.BusquedaClientePersistence;
import com.mx.apicustomerclient.bussines.persistence.BusquedaOrdenPersistence;
import com.mx.apicustomerclient.bussines.until.FormatoService;
import com.mx.apicustomerclient.bussines.until.UntilService;
import com.mx.apicustomerclient.dao.value.DaoValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaOrderService {
    private static final Logger LOG = LogManager.getLogger(ConsultaOrderService.class);
    @Autowired
    FormatoService formatoService;

    @Autowired
    UntilService untilService;

    @Autowired
    BusquedaOrdenPersistence busquedaOrdenPersistence;

    public RespuestaModel consultarOrden(String ordenId){
        LOG.info("::::::::::::Inicia Busqueda de Orden:::::::::::");
        final RespuestaModel respuestaModel = new RespuestaModel();
        respuestaModel.setFolio(untilService.generarFolio());
        respuestaModel.setEstatus(HttpStatus.BAD_REQUEST);
        ReporteOrdenDto reporteOrdenDto = new ReporteOrdenDto();

        try {
            if (formatoService.validarNumeroEntero(ordenId)){
                List<ReportePlanoOrderDto> listaPlana = busquedaOrdenPersistence.buscarOrderPersistence(Integer.parseInt(ordenId));
                if(!listaPlana.isEmpty()){
                    LOG.info("::::::::::::Consulta exitosa:::::::::::");
                    respuestaModel.setEstatus(HttpStatus.OK);
                    reporteOrdenDto = this.obtenerOrden(listaPlana);
                    respuestaModel.setResultado(reporteOrdenDto);
                }else{
                    LOG.info("::::::::::::Sin datos::::::::::");
                    respuestaModel.setEstatus(HttpStatus.NOT_FOUND);
                    respuestaModel.setResultado("No se encontraron datos para el ID de orden proporcionado" );
                }

            }else{
                LOG.info("::::::::::::Error validaciones::::::::::");
                respuestaModel.setResultado("El ID de orden debe ser un numero entero valido");
            }
        } catch (RuntimeException e) {
            final String metodo = e.getStackTrace()[DaoValue.INT_VALOR_METODO].getMethodName();
            LOG.error(DaoValue.STR_FROMATO_DAO_ERROR.formatted(metodo, e));
        }

        return respuestaModel;
    }

    public ReporteOrdenDto obtenerOrden(List<ReportePlanoOrderDto> listaPlana) {

        ReportePlanoOrderDto primeraFila = listaPlana.get(0);

        ReporteOrdenDto respuesta = new ReporteOrdenDto();
        respuesta.setFolio(primeraFila.getFolio());
        respuesta.setFecha(untilService.convertirFecha(primeraFila.getFecha()));

        ClienteReporteDto cliente = new ClienteReporteDto();
        cliente.setIdCliente(String.valueOf(primeraFila.getIdCliente()));
        cliente.setNombreCompleto(primeraFila.getNombreCompleto());
        cliente.setCorreoElectronico(primeraFila.getCorreoElectronico());
        cliente.setTelefono(primeraFila.getTelefono());
        respuesta.setCliente(cliente);

        respuesta.setSubtotal(primeraFila.getSubtotal());
        respuesta.setIva(primeraFila.getIva());
        respuesta.setTotal(primeraFila.getTotal());

        List<ItemDto> listaProductos = new ArrayList<ItemDto>();

        for (ReportePlanoOrderDto ordenPlana : listaPlana) {
            ItemDto item = new ItemDto();
            item.setnSku(ordenPlana.getSku());
            item.setCantidad(String.valueOf(ordenPlana.getCantidad()));
            item.setPrecioUnitario(String.valueOf(ordenPlana.getPrecioUnitario()));
            item.setImporte(String.valueOf(ordenPlana.getImporte()));

            listaProductos.add(item);
        }

        respuesta.setProductos(listaProductos);

        return respuesta;
    }
}
