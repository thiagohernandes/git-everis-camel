package com.camel.code.route;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class R01PrimeiraRotaJettyTests {

	@EndpointInject(value = "mock:final")
    private ProducerTemplate sourceEndpoint;
	
	@Test
	public void test() {
		sourceEndpoint.sendBody("Olpa primeira rota!");
	}
}
