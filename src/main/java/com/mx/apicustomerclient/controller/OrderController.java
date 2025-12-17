package com.mx.apicustomerclient.controller;

import com.mx.apicustomerclient.bussines.dto.EntradaOrdersDto;
import com.mx.apicustomerclient.bussines.dto.SalidaDto;
import com.mx.apicustomerclient.bussines.model.RespuestaModel;
import com.mx.apicustomerclient.bussines.service.ConsultaOrderService;
import com.mx.apicustomerclient.bussines.service.RegistrarOrdenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ordenes/")
public class OrderController {

    private static final Logger LOG = LogManager.getLogger(OrderController.class);

    @Autowired
    RegistrarOrdenService registrarOrdenService;

    @Autowired
    ConsultaOrderService consultaOrderService;

    @PostMapping(value = "/orders")
    public ResponseEntity<Object> registroOrder(@RequestBody EntradaOrdersDto entradaOrdersDto) {
        final RespuestaModel respuestaModel = registrarOrdenService.registrarOrden(entradaOrdersDto);
        return new ResponseEntity<>( new SalidaDto(respuestaModel.getFolio(),respuestaModel.getResultado()),respuestaModel.getEstatus());
    }

    @GetMapping(value = "/orders/{idOrder}")
    public ResponseEntity<Object> busquedaOrderId(@PathVariable("idOrder")  String idOrder) {
        final RespuestaModel respuestaModel = consultaOrderService.consultarOrden(idOrder);
        return new ResponseEntity<>( new SalidaDto(respuestaModel.getFolio(),respuestaModel.getResultado()),respuestaModel.getEstatus());
    }
}
