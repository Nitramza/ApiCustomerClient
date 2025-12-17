package com.mx.apicustomerclient.controller;

import com.mx.apicustomerclient.bussines.dto.ClienteDto;
import com.mx.apicustomerclient.bussines.dto.SalidaDto;
import com.mx.apicustomerclient.bussines.model.ClienteModel;
import com.mx.apicustomerclient.bussines.model.RespuestaModel;
import com.mx.apicustomerclient.bussines.service.BuscarClienteService;
import com.mx.apicustomerclient.bussines.service.RegistrarClienteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class ClienteController {
    private static final Logger LOG = LogManager.getLogger(ClienteController.class);

    @Autowired
    RegistrarClienteService registrarClienteService;

    @Autowired
    BuscarClienteService buscarClienteService;

    @PostMapping(value = "/customers")
    public ResponseEntity<Object> registroCliente(@RequestBody ClienteModel clienteModel) {
        final RespuestaModel respuestaModel = registrarClienteService.registrarCliente(clienteModel);
        return new ResponseEntity<>( new SalidaDto(respuestaModel.getFolio(),respuestaModel.getResultado()),respuestaModel.getEstatus());
    }

    @GetMapping(value = "/customers/{idCliente}")
    public ResponseEntity<Object> consultaCustomerPorId(@PathVariable("idCliente")  String idCliente) {
        final RespuestaModel respuestaModel = buscarClienteService.consultarCustomerId(idCliente);
        final ClienteDto clienteDto = new ClienteDto();
        return new ResponseEntity<>( new SalidaDto(respuestaModel.getFolio(),respuestaModel.getResultado()),respuestaModel.getEstatus());
    }
}
