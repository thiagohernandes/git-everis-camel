package com.camel.code.parte01.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class UtilApp {

	 private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 private final String rotaKey = "rota";
	
	 public void runContextCamel(List<Object> listRoutes, int timeMilisegundos, boolean alwaysRunning) throws Exception {
		 	logger.info("*********** Iniciando o contexto do Camel");
	        CamelContext ctx = new DefaultCamelContext();
	        listRoutes.stream().forEach(i -> {
	            try {
	                ctx.addRoutes((RoutesBuilder) i);
	            } catch (Exception e) {
	            	logger.error("*********** Erro");
	                e.printStackTrace();
	            }
	        });
	        ctx.start();
	        logger.info("*********** Sucesso - contexto do Camel executando... ***********");
	        if (!alwaysRunning) {
		        Thread.sleep(timeMilisegundos);
		        ctx.stop();
		        logger.info("*********** Finalizado ***********");
	        }
	    }
}