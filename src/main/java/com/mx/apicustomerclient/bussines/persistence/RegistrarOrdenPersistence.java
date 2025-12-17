package com.mx.apicustomerclient.bussines.persistence;

import com.mx.apicustomerclient.bussines.model.ClienteModel;
import com.mx.apicustomerclient.bussines.model.DatosOrdenModel;
import com.mx.apicustomerclient.bussines.until.UntilService;
import com.mx.apicustomerclient.bussines.value.BussinesValue;
import com.mx.apicustomerclient.dao.dto.DaoDto;
import com.mx.apicustomerclient.dao.dto.InfoJdbcDto;
import com.mx.apicustomerclient.dao.dto.InputOutputDto;
import com.mx.apicustomerclient.dao.dto.ParametroDto;
import com.mx.apicustomerclient.dao.model.RespuestaBdModel;
import com.mx.apicustomerclient.dao.repository.SqlDao;
import com.mx.apicustomerclient.dao.service.RowMaperService;
import com.mx.apicustomerclient.dao.value.DaoValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RegistrarOrdenPersistence {
    private static final Logger LOG = LogManager.getLogger(RegistrarOrdenPersistence.class);
    private static final String STR_INPUT_ORDER_CLIENTEID = "PAIIDCLIENTE";
    private static final String STR_INPUT_ORDER_SUBTOTAL = "PADSUBTOTAL";
    private static final String STR_INPUT_ORDER_IVA = "PADIVA";
    private static final String STR_INPUT_ORDER_TOTAL = "PADTOTAL";
    private static final String STR_INPUT_ORDER_ITEMS = "PACJSONITEMS";
    private static final String STR_OUTPUT_EXITO = "PAIEXITO";
    private static final String STR_OUTPUT_MENSAJE = "PACMENSAJE";
    private static final String STR_OUTPUT_ORDER_ID = "PAIIDORDEN";
    private static final String STR_NAME_RESULT = "respuesta";

    @Autowired
    DataSource dataSource;

    @Autowired
    RowMaperService rowMaperService;

    @Autowired
    UntilService untilService;

    public RespuestaBdModel registraorderPersistence(DatosOrdenModel datosOrdenModel) {
        LOG.info("::::::::::::Registro Persistencia:::::::::::");
        final RespuestaBdModel respuestaBdModel = new RespuestaBdModel();
        try {
            final InfoJdbcDto infoJdbcVo = new InfoJdbcDto();
            infoJdbcVo.setCatalogName(DaoValue.STR_NAME_BD);
            infoJdbcVo.setSchemaName(DaoValue.STR_SCHEMA_BD);
            infoJdbcVo.setProcedureName(DaoValue.STR_SP_REGISTRA_ORDEN);
            infoJdbcVo.setResultName(STR_NAME_RESULT);

            final List<ParametroDto> listaParametroVo = new ArrayList<>();
            listaParametroVo
                    .add(new ParametroDto(STR_INPUT_ORDER_CLIENTEID, DaoValue.STR_TIPO_INT, BussinesValue.BOOL_VALOR_TRUE));
            listaParametroVo
                    .add(new ParametroDto(STR_INPUT_ORDER_ITEMS, DaoValue.STR_TIPO_CHAR, BussinesValue.BOOL_VALOR_TRUE));
            listaParametroVo
                    .add(new ParametroDto(STR_INPUT_ORDER_SUBTOTAL, DaoValue.STR_TIPO_CHAR, BussinesValue.BOOL_VALOR_TRUE));
            listaParametroVo
                    .add(new ParametroDto(STR_INPUT_ORDER_TOTAL, DaoValue.STR_TIPO_CHAR, BussinesValue.BOOL_VALOR_TRUE));
            listaParametroVo
                    .add(new ParametroDto(STR_INPUT_ORDER_IVA, DaoValue.STR_TIPO_CHAR, BussinesValue.BOOL_VALOR_TRUE));
            listaParametroVo.add(new ParametroDto(STR_OUTPUT_EXITO, DaoValue.STR_TIPO_INT, BussinesValue.BOOL_VALOR_FALSE));
            listaParametroVo
                    .add(new ParametroDto(STR_OUTPUT_MENSAJE, DaoValue.STR_TIPO_CHAR, BussinesValue.BOOL_VALOR_FALSE));
            listaParametroVo
                    .add(new ParametroDto(STR_OUTPUT_ORDER_ID, DaoValue.STR_TIPO_INT, BussinesValue.BOOL_VALOR_FALSE));

            final String ordenesJson = untilService.convertirObjetoAjson(datosOrdenModel.getOrdenes());

            final List<InputOutputDto> listaIoVo = new ArrayList<>();
            listaIoVo.add(new InputOutputDto(STR_INPUT_ORDER_CLIENTEID, datosOrdenModel.getCustomerId()));
            listaIoVo.add(new InputOutputDto(STR_INPUT_ORDER_ITEMS, ordenesJson));
            listaIoVo.add(new InputOutputDto(STR_INPUT_ORDER_SUBTOTAL, datosOrdenModel.getSubtotal()));
            listaIoVo.add(new InputOutputDto(STR_INPUT_ORDER_TOTAL, datosOrdenModel.getTotal()));
            listaIoVo.add(new InputOutputDto(STR_INPUT_ORDER_IVA, datosOrdenModel.getIva()));
            listaIoVo.add(new InputOutputDto(STR_OUTPUT_EXITO, BussinesValue.INT_VALOR_CERO));
            listaIoVo.add(new InputOutputDto(STR_OUTPUT_MENSAJE, BussinesValue.STR_VACIO));
            listaIoVo.add(new InputOutputDto(STR_OUTPUT_ORDER_ID, BussinesValue.STR_VACIO));

            final RowMapper<Object> rowMapper = rowMaperService.obtenerMapeoId(STR_OUTPUT_ORDER_ID);
            final SqlDao dao = new SqlDao(dataSource);
            final Map<String, Object> respuesta = dao
                    .ejecutar(new DaoDto(infoJdbcVo, listaParametroVo, listaIoVo, rowMapper));

            respuestaBdModel.setExito(Integer.parseInt(respuesta.get(STR_OUTPUT_EXITO).toString()));
            respuestaBdModel.setMensaje(respuesta.get(STR_OUTPUT_MENSAJE).toString());
            respuestaBdModel.setIdentificador(respuesta.get(STR_OUTPUT_ORDER_ID).toString());
            return respuestaBdModel;

        } catch (RuntimeException e) {
            final String metodo = e.getStackTrace()[DaoValue.INT_VALOR_METODO].getMethodName();
            LOG.error(DaoValue.STR_FROMATO_DAO_ERROR.formatted(metodo, e));
        }
        return respuestaBdModel;
    }
}
