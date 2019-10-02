package com.camel.code.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camel.code.domain.Funcionario;
import com.camel.code.util.UtilApp;
import com.google.gson.Gson;

@Component
public class R02JettyToRest extends RouteBuilder {

	private final String rotaJetty8089 = "jetty://http://localhost:8089/msg-to-rest";
	private final String rotaJetty8090 = "jetty://http://localhost:8090/rota90";
	private final String rotaRestFuncionarios = "jetty://http://localhost:8080/api/funcionarios/todos?bridgeEndpoint=true";
	private final String msgFrom8089 = "msgFrom8089";
	private final String valorOut8089 = "Esse valor foi setado na chamada do serviço da porta 8089";
	private final String msgFrom8089Value = "Olá serviço REST!";
	private String valorTeste = "";
	
	@Autowired
	UtilApp utilApp;
	
	@Override
	public void configure() throws Exception {
		
		from(rotaJetty8089)
			.process(new Processor() {
	            @Override
	            public void process(Exchange exchange) throws Exception {
	               log.info("---> Log 8089");
	               exchange.setProperty(msgFrom8089, msgFrom8089Value);
	            }
	        })
	    	.to(rotaRestFuncionarios)
	    	.process(new Processor() {
	            @Override
	            public void process(Exchange exchange) throws Exception {
	            	log.info("---> Isso veio da URL 8089: '{}' ", exchange.getProperty(msgFrom8089));
	            	log.info("---> Isso será a saída para outro redirecionamento (String): '{}' ", exchange.getIn().getBody(String.class));
	            	Gson g = new Gson();
	            	Funcionario[] p = g.fromJson(exchange.getIn().getBody(String.class), Funcionario[].class);
	            	log.info("---> JSON de objetos de Funcionários '{}' ", g.toJson(p));
	            	exchange.getOut().setBody(valorOut8089);
	            	valorTeste = "Valor setado na chamada da rota 8090";
	            }
	        })
	    	.to(rotaJetty8090);
		
		from(rotaJetty8090)
			.convertBodyTo(String.class)
			.process(new Processor() {
	            @Override
	            public void process(Exchange exchange) throws Exception {
	               log.info("---> Log 8090 '{}'", valorTeste);
	            }
	        });
	}
	
}
