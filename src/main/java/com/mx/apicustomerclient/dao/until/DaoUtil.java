package com.mx.apicustomerclient.dao.until;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tools.jackson.databind.ObjectMapper;

public final class DaoUtil {
    private static final Logger LOG = LogManager.getLogger(DaoUtil.class);

    private DaoUtil() {
        throw new IllegalStateException("DaoUtil");
    }

    public static <T> List<T> convertirLista(Object obj, Class<T> clase) {
        if (!(obj instanceof List<?>)) {
            return Collections.emptyList();
        }
        final List<T> result = new ArrayList<>();
        ((List<?>) obj).forEach((Object o) -> result.add(clase.cast(o)));
        return result;
    }

    public static boolean validarRespuesta(Map<String, Object> respuesta, String nombre) {
        boolean valido = false;
        try {
            if (respuesta.containsKey(nombre)) {
                valido = true;
            }
        }
        catch (RuntimeException e) {
            LOG.error("Error %s".formatted(e));
        }
        return valido;
    }

    public static Object obtenerObjeto(Map<String, Object> respuesta, String nombre) {
        Object data = null;
        if (respuesta != null) {
            data = respuesta.get(nombre);
        }
        return data;
    }

    public static <T> T convertirObjeto(Object obj, Class<T> clase) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.convertValue(obj, clase);
        }
        catch (RuntimeException e) {
            LOG.error("Error %s".formatted(e));
            return null;
        }
    }
}
