package com.mx.apicustomerclient.bussines.service;

import com.mx.apicustomerclient.bussines.dto.InventarioDto;
import com.mx.apicustomerclient.bussines.dto.SalidaDto;
import com.mx.apicustomerclient.bussines.until.UntilService;
import com.mx.apicustomerclient.bussines.value.BussinesValue;
import com.mx.apicustomerclient.dao.until.DaoUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class InventarioService {
    private static final Logger LOG = LogManager.getLogger(InventarioService.class);

    private final WebClient.Builder webClientBuilder;
    //Esto puede ir en prperties y leeerlo con @Value
    private static final String STR_URL_INVENTARIO = "http://localhost:8080/api/v1/invetario";
    private static final String STR_FORMAT_URI= "%s/%s";

    public InventarioService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
    public int consultarStock(String sku) {
        LOG.info("::::::::::::Consulta al endpoint Mock Inventario:::::::::::");
        try {
            SalidaDto respuesta = webClientBuilder.build()
                    .get()
                    .uri(String.format(STR_FORMAT_URI,STR_URL_INVENTARIO,sku) )
                    .retrieve()
                    .bodyToMono(SalidaDto.class)
                    .block();
            final InventarioDto inventarioDto = DaoUtil.convertirObjeto(respuesta.getResultado(), InventarioDto.class) ;
            return (inventarioDto != null) ? inventarioDto.getStockDisponible(): 0;

        } catch (Exception e) {
            final String metodo = e.getStackTrace()[BussinesValue.INT_VALOR_CERO].getMethodName();
            LOG.error(BussinesValue.STR_FROMATO_ERROR.formatted(metodo, e));
            return 0;
        }
    }
}
