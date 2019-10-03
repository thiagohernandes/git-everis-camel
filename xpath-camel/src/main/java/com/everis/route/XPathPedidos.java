package com.everis.route;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XPathPedidos {

        @Bean
        public void init() throws Exception {
        	
        	StringBuilder resultado = new StringBuilder();
        	
        	CamelContext camelContext = new DefaultCamelContext();
            try {
                camelContext.addRoutes(new RouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        from("direct:start")
                        .split(xpath("//pedido[@categoria='eletrônicos']/items/item/text()"))
                        .to("stream:out")
                        .process(new Processor() {
            				@Override
            		          public void process(Exchange exchange) throws Exception {
            	            	  log.info("---> BODY '{}' ", exchange.getIn().getBody());
            	            	  resultado.append(exchange.getIn().getBody().toString().replace("[#text:","").replace("]","")+ "\r");
            		          }
            			})
                        .log("${body}")
                        .end();
                    }
                });
                InputStream is = new FileInputStream("src/main/resources/pedidos.xml");          
                camelContext.start();
                ProducerTemplate template = camelContext.createProducerTemplate();
                template.sendBody("direct:start", is);
            } finally {
                camelContext.stop();
                System.out.println("RESULTADO: \r" + resultado);
            }
        }
}
