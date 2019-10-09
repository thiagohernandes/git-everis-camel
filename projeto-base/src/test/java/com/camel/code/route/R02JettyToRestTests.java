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
public class R02JettyToRestTests {

	private static CamelContext camelContext;
	private Exchange exchange;
	private final String msgFrom8089 = "msgFrom8089";
	private final String msgFrom8089Value = "Olá serviço REST!";
	
	@Before
	public void init() throws Exception {
		camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new R01PrimeiraRotaJetty());
		exchange = new DefaultExchange(camelContext);
	}
	
	@Test
	public void msgFromm8089Test() {
		exchange.setProperty(msgFrom8089, msgFrom8089Value);
		assertEquals(exchange.getProperty(msgFrom8089),"Olá serviço REST!");
	}
	
}
