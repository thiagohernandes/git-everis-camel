package com.everis.camel.route;

import java.math.BigDecimal;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.everis.camel.converter.PedidoConverter;
import com.everis.camel.domain.Pedido;

public class C00RoutePedido extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		final Pedido pedido = new Pedido("Lanches..",new BigDecimal("32.11"),2);
		final String rotaInicial = "jetty://http://localhost:9101/pedido";
		
		from("direct:inicia").to(rotaInicial);
		
		from(rotaInicial)
		.process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
               exchange.getIn().setBody(pedido);
            }
        })
		.bean(new PedidoConverter(),"toFazerAlgumaCoisa")
		.convertBodyTo(String.class)
		.log("${body}").log("Ok").to("mock:a1");
		
	}	

}
