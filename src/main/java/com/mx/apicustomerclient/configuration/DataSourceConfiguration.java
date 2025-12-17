package com.mx.apicustomerclient.configuration;

import com.mx.apicustomerclient.bussines.value.BussinesValue;
import com.mx.apicustomerclient.dao.value.DaoValue;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
	private static final Logger LOG = LogManager.getLogger(DataSourceConfiguration.class);

	@Value("${bd.api.uri}")
	private String url;

	@Value("${bd.api.user}")
	private String username;

	@Value("${bd.api.pass}")
	private String password;

	@Value("${bd.api.driver}")
	private String driverClassName;

	@Bean(name = "dsSql")
	@Primary
	DataSource getDataSource() {
		try {
			LOG.info("Inicia config DataSource");
			HikariDataSource ds = new HikariDataSource();
			ds.setDriverClassName(driverClassName);
			ds.setJdbcUrl(DaoValue.STR_FORMAT_DRIVER.formatted((url),
					DaoValue.STR_COMPLEMENT_DRIVER));
			ds.setUsername(username);
			ds.setPassword(password);

			ds.setMaximumPoolSize(10);
			ds.setMinimumIdle(2);
			ds.setIdleTimeout(60000); 
			ds.setMaxLifetime(300000); 
			ds.setConnectionTimeout(30000); 

			LOG.info("HikariCP DataSource creado");
			return ds;
		} catch (RuntimeException e) {
			final String metodo = e.getStackTrace()[BussinesValue.INT_VALOR_CERO].getMethodName();
			LOG.error(BussinesValue.STR_FROMATO_ERROR.formatted(metodo, e));
			
		} finally {
			LOG.info("Fin config DataSource");
		}
		return null;
	}
}
