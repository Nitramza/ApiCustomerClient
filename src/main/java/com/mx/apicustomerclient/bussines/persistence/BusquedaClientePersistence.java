package com.mx.apicustomerclient.bussines.persistence;

import com.mx.apicustomerclient.bussines.model.ClienteModel;
import com.mx.apicustomerclient.bussines.value.BussinesValue;
import com.mx.apicustomerclient.dao.dto.DaoDto;
import com.mx.apicustomerclient.dao.dto.InfoJdbcDto;
import com.mx.apicustomerclient.dao.dto.InputOutputDto;
import com.mx.apicustomerclient.dao.dto.ParametroDto;
import com.mx.apicustomerclient.dao.model.RespuestaBdModel;
import com.mx.apicustomerclient.dao.repository.SqlDao;
import com.mx.apicustomerclient.dao.service.RowMaperService;
import com.mx.apicustomerclient.dao.until.DaoUtil;
import com.mx.apicustomerclient.dao.value.DaoValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BusquedaClientePersistence {
    private static final Logger LOG = LogManager.getLogger(BusquedaClientePersistence.class);
    private static final String STR_INPUT_CLIENTE_ID = "PAIIDCLIENTE";
    private static final String STR_CLIENTE_NOMBRE = "FCNOMBRE";
    private static final String STR_CLIENTE_MAIL = "FCEMAIL";
    private static final String STR_CLIENTE_TELEFONO = "FCTELEFONO";
    private static final String STR_NAME_RESULT = "respuesta";
    private static final int INT_RENGLON_CERO = 0;

    @Autowired
    DataSource dataSource;

    @Autowired
    RowMaperService rowMaperService;

    public ClienteModel buscarlientePersistence(int idCliente) {
        LOG.info("::::::::::::Consulta Persistencia:::::::::::");
        final RespuestaBdModel respuestaBdModel = new RespuestaBdModel();
        try {
            final InfoJdbcDto infoJdbcDto = new InfoJdbcDto();
            infoJdbcDto.setCatalogName(DaoValue.STR_NAME_BD);
            infoJdbcDto.setSchemaName(DaoValue.STR_SCHEMA_BD);
            infoJdbcDto.setProcedureName(DaoValue.STR_SP_BUSQUEDA_CLIENTE_ID);
            infoJdbcDto.setResultName(STR_NAME_RESULT);

            final List<ParametroDto> parametroDto = new ArrayList<>();
            parametroDto
                    .add(new ParametroDto(STR_INPUT_CLIENTE_ID, DaoValue.STR_TIPO_INT, BussinesValue.BOOL_VALOR_TRUE));

            final List<InputOutputDto> listaIoDto = new ArrayList<>();
            listaIoDto.add(new InputOutputDto(STR_INPUT_CLIENTE_ID, idCliente));

            final RowMapper<Object> rowMapper = this.mapearResulSet();
            final SqlDao dao = new SqlDao(dataSource);
            final Map<String, Object> respuesta = dao
                    .ejecutar(new DaoDto(infoJdbcDto, parametroDto, listaIoDto, rowMapper));

            final Object data = DaoUtil.obtenerObjeto(respuesta, infoJdbcDto.getResultName());

            final ClienteModel clienteModel = DaoUtil.convertirLista(data, ClienteModel.class).get(INT_RENGLON_CERO);
            LOG.info(respuesta.toString());
            return clienteModel;

        } catch (RuntimeException e) {
            final String metodo = e.getStackTrace()[DaoValue.INT_VALOR_METODO].getMethodName();
            LOG.error(DaoValue.STR_FROMATO_DAO_ERROR.formatted(metodo, e));
        }
        return new ClienteModel();
    }

    public RowMapper<Object> mapearResulSet() {
        LOG.info("::: Mapea resulset:::");
        try {
            return (ResultSet resulset, int rowNum) -> new ClienteModel(resulset.getString(STR_CLIENTE_NOMBRE),
                    resulset.getString(STR_CLIENTE_MAIL),resulset.getString(STR_CLIENTE_TELEFONO));
        }
        catch (RuntimeException e) {
            final String metodo = e.getStackTrace()[DaoValue.INT_VALOR_METODO].getMethodName();
            LOG.error(DaoValue.STR_FROMATO_DAO_ERROR.formatted(metodo, e));
        }
        return null;
    }
}
