package com.mx.apicustomerclient.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:ApiCustomerClient.properties")//RECOMENDADO QUE ESTE APRTE FUERA DEL PROYECTO POR SEGURIDAD
public class PropertieConfiguration {
	//Metodos de obtencion de datos, como leer un string o datos
}
