package com.camel.code.route;

import static org.junit.Assert.assertEquals;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
public class R01PrimeiraRotaJettyTests {

	private static CamelContext camelContext;
	private Exchange exchange;
	
	@Before
	public void init() throws Exception {
		camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new R01PrimeiraRotaJetty());
		exchange = new DefaultExchange(camelContext);
	}
	
	@Test
	public void bodyMensagemTest() {
		exchange.getIn().setBody("Olá primeira rota!");
		assertEquals(exchange.getIn().getBody().toString(), "Olá primeira rota!");
	}
	
}
