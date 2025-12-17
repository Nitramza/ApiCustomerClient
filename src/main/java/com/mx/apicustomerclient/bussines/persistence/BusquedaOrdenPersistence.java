package com.mx.apicustomerclient.bussines.persistence;

import com.mx.apicustomerclient.bussines.dto.ReportePlanoOrderDto;
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
public class BusquedaOrdenPersistence {
    private static final Logger LOG = LogManager.getLogger(BusquedaOrdenPersistence.class);
    private static final String STR_INPUT_ORDER_ID = "PAIIDORDEN";
    private static final String STR_NAME_RESULT = "respuesta";
    private static final int INT_RENGLON_CERO = 0;

    private static final String STR_PARAM_ORDER_ID = "FIIDORDEN";
    private static final String STR_PARAM_ORDER_FECHA = "FDFECHAORDEN";
    private static final String STR_PARAM_ORDER_SUBTOTAL = "FDSUBTOTAL";
    private static final String STR_PARAM_ORDER_IVA = "FDIVA";
    private static final String STR_PARAM_ORDER_TOTAL = "FDTOTAL";
    private static final String STR_PARAM_CLIENTE_ID = "FIIDCLIENTE";
    private static final String STR_PARAM_CLIENTE_NOMBRE = "FCNOMBRE";
    private static final String STR_PARAM_CLIENTE_EMAIL = "FCEMAIL";
    private static final String STR_PARAM_CLIENTE_TELEFONO = "FCTELEFONO";
    private static final String STR_PARAM_ORDER_SKU = "FCSKU";
    private static final String STR_PARAM_ORDER_CANTIDAD = "FICANTIDAD";
    private static final String STR_PARAM_ORDER_UNITARIO = "FDPRECIOUNITARIO";
    private static final String STR_PARAM_ORDER_IMPORTE = "FDIMPORTE";

    @Autowired
    DataSource dataSource;

    @Autowired
    RowMaperService rowMaperService;

    @Autowired
    UntilService untilService;

    public List<ReportePlanoOrderDto> buscarOrderPersistence(int orderId) {
        LOG.info("::::::::::::Consulta Persistencia:::::::::::");
        final RespuestaBdModel respuestaBdModel = new RespuestaBdModel();
        try {
            final InfoJdbcDto infoJdbcDto = new InfoJdbcDto();
            infoJdbcDto.setCatalogName(DaoValue.STR_NAME_BD);
            infoJdbcDto.setSchemaName(DaoValue.STR_SCHEMA_BD);
            infoJdbcDto.setProcedureName(DaoValue.STR_SP_BUSQUEDA_ORDEN);
            infoJdbcDto.setResultName(STR_NAME_RESULT);

            final List<ParametroDto> parametroDto = new ArrayList<>();
            parametroDto
                    .add(new ParametroDto(STR_INPUT_ORDER_ID, DaoValue.STR_TIPO_INT, BussinesValue.BOOL_VALOR_TRUE));

            final List<InputOutputDto> listaIoDto = new ArrayList<>();
            listaIoDto.add(new InputOutputDto(STR_INPUT_ORDER_ID, orderId));

            final RowMapper<Object> rowMapper = this.mapearResulSet();
            final SqlDao dao = new SqlDao(dataSource);
            final Map<String, Object> respuesta = dao
                    .ejecutar(new DaoDto(infoJdbcDto, parametroDto, listaIoDto, rowMapper));

            final Object data = DaoUtil.obtenerObjeto(respuesta, infoJdbcDto.getResultName());

            List<ReportePlanoOrderDto>  listaPlana = DaoUtil.convertirLista(data, ReportePlanoOrderDto.class);
            return listaPlana;

        } catch (RuntimeException e) {
            final String metodo = e.getStackTrace()[DaoValue.INT_VALOR_METODO].getMethodName();
            LOG.error(DaoValue.STR_FROMATO_DAO_ERROR.formatted(metodo, e));
        }
        return new ArrayList<ReportePlanoOrderDto>();
    }

    public RowMapper<Object> mapearResulSet() {
        LOG.info("::: Mapea resulset:::");
        try {
            return (ResultSet rs, int rowNum) -> {
                ReportePlanoOrderDto reportePlanoOrderDto = new ReportePlanoOrderDto();

                reportePlanoOrderDto.setFolio(rs.getInt(STR_PARAM_ORDER_ID));
                reportePlanoOrderDto.setFecha(rs.getString(STR_PARAM_ORDER_FECHA));

                reportePlanoOrderDto.setSubtotal(rs.getDouble(STR_PARAM_ORDER_SUBTOTAL));
                reportePlanoOrderDto.setIva(rs.getDouble(STR_PARAM_ORDER_IVA));
                reportePlanoOrderDto.setTotal(rs.getDouble(STR_PARAM_ORDER_TOTAL));

                reportePlanoOrderDto.setIdCliente(rs.getInt(STR_PARAM_CLIENTE_ID));
                reportePlanoOrderDto.setNombreCompleto(rs.getString(STR_PARAM_CLIENTE_NOMBRE));
                reportePlanoOrderDto.setCorreoElectronico(rs.getString(STR_PARAM_CLIENTE_EMAIL));
                reportePlanoOrderDto.setTelefono(rs.getString(STR_PARAM_CLIENTE_TELEFONO));

                reportePlanoOrderDto.setSku(rs.getString(STR_PARAM_ORDER_SKU));
                reportePlanoOrderDto.setCantidad(rs.getInt(STR_PARAM_ORDER_CANTIDAD));
                reportePlanoOrderDto.setPrecioUnitario(rs.getDouble(STR_PARAM_ORDER_UNITARIO));
                reportePlanoOrderDto.setImporte(rs.getDouble(STR_PARAM_ORDER_IMPORTE));

                return reportePlanoOrderDto;
            };
        }
        catch (RuntimeException e) {
            final String metodo = e.getStackTrace()[DaoValue.INT_VALOR_METODO].getMethodName();
            LOG.error(DaoValue.STR_FROMATO_DAO_ERROR.formatted(metodo, e));
        }
        return null;
    }
}
