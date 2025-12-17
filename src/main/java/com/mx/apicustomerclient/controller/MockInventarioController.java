package com.mx.apicustomerclient.controller;

import com.mx.apicustomerclient.bussines.dto.SalidaDto;
import com.mx.apicustomerclient.bussines.until.UntilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class MockInventarioController {

    @Autowired
    UntilService untilService;

    @GetMapping("invetario/{sku}")
    public ResponseEntity<Object> consultarStock(@PathVariable String sku) {

        int stockSimulado = sku.startsWith("CASC") ? 0 : 100;

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("sku", sku);
        respuesta.put("stockDisponible", stockSimulado);

        return  new ResponseEntity<>( new SalidaDto(untilService.generarFolio(),respuesta), HttpStatus.OK);
    }
}
