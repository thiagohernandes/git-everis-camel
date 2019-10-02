package com.camel.code.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.code.util.UtilApp;

@Component
public class R06JettyToRestIntegracao extends RouteBuilder {

	private final String rotaIntegracao = "jetty://http://localhost:9999/integracao";
	private final String rotaRestFuncionarios = "http4://localhost:8080/api/funcionarios/todos?bridgeEndpoint=true";
	private final String rotaRestTimes = "http4://localhost:8099/api/times/todos?bridgeEndpoint=true";
	
	@Autowired
	UtilApp utilApp;
	
	@Override
	public void configure() throws Exception {
		
		from(rotaIntegracao)
			.process(new Processor() {
	            @Override
	            public void process(Exchange exchange) throws Exception {
	               log.info("---> Log rotaIntegracao");
	            }
	        })
			.to(rotaRestFuncionarios)
	    	.process(new Processor() {
	            @Override
	            public void process(Exchange exchange) throws Exception {
	            	 log.info("---> Log rotaRestFuncionarios");
	            	 exchange.setProperty("funcionarios", exchange.getIn().getBody(String.class));
	            }
	        })
	    	.to(rotaRestTimes)
	    	.process(new Processor() {
	            @Override
	            public void process(Exchange exchange) throws Exception {
	            	log.info("---> rotaRestTimes Properties (String): '{}' ", exchange.getIn().getBody(String.class));
	            	log.info("---> rotaRestFuncionarios Properties (String): '{}' ", exchange.getProperty("funcionarios"));
	            	//exchange.getOut().setBody(exchange.getProperty("funcionarios"));
	            }
	        });
	}
	
}
