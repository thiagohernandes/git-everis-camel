package com.camel.code.parte02.interceptor;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class XpathInterceptorRoute extends RouteBuilder{
	
	private final String rotaJetty9002 = "jetty://http://localhost:9002/xpath";
	
	@Override
	public void configure() throws Exception {
		from(rotaJetty9002)
		.to("file:src/data?noop=true")
         .choice().
             when(xpath("/pessoa/cidade = 'Uberlândia'")).to("file:mensagens/uberlandia").
             otherwise().to("file:mensagens/uberaba");
	}

}
