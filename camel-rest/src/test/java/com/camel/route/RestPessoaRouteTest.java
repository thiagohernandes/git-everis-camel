package com.camel.route;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.camel.processor.PessoaProcessorJsonToXml;
import com.camel.processor.PessoaProcessorXmlToJson;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
public class RestPessoaRouteTest {
	
	private static CamelContext camelContext;
	
	private PessoaProcessorJsonToXml processorJsonToXml;
	private PessoaProcessorXmlToJson processorXmlToJson;
	
	private static Exchange exchange;
	
	@BeforeClass
	public static void init() throws Exception {
		camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new RestPessoaRoute());
		exchange = new DefaultExchange(camelContext);
	}

    @Test
    public void okRouteJsonToXmlTest() throws Exception {
	    processorJsonToXml = new PessoaProcessorJsonToXml();
    	exchange.getIn().setBody("");
    	exchange.getIn().setBody("[" + 
    			"    {" + 
    			"        \"nome\": \"Beltrano\"," + 
    			"        \"idade\": 22" + 
    			"    }," + 
    			"    {" + 
    			"        \"nome\": \"Alana\"," + 
    			"        \"idade\": 27" + 
    			"    }," + 
    			"    {" + 
    			"        \"nome\": \"Filisbino\"," + 
    			"        \"idade\": 39" + 
    			"    }" + 
    			"]");
    	processorJsonToXml.process(exchange);
    }
    
    @Test(expected = JSONException.class)
    public void exceptionJsonTest() throws Exception {
    	processorJsonToXml = new PessoaProcessorJsonToXml();
    	exchange.getIn().setBody("");
    	exchange.getIn().setBody("[33" + 
    			"    {" + 
    			"        \"nome\": \"Beltrano\"," + 
    			"        \"idade\": 22" + 
    			"    }," + 
    			"    {" + 
    			"        \"nome\": \"Alana\"," + 
    			"        \"idade\": 27" + 
    			"    }," + 
    			"    {" + 
    			"        \"nome\": \"Filisbino\"," + 
    			"        \"idade\": 39" + 
    			"    }" + 
    			"]");
    	processorJsonToXml.process(exchange);
    }
    
    @Test
    public void okRouteXmlToJsonTest() throws Exception {
    	processorXmlToJson = new PessoaProcessorXmlToJson();
    	exchange.getIn().setBody("");
    	exchange.getIn().setBody("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + 
    			"    <pessoas>" + 
    			"        <pessoa>" + 
    			"            <idade>22</idade>" + 
    			"            <nome>Beltrano</nome>" + 
    			"        </pessoa>" + 
    			"        <pessoa>" + 
    			"            <idade>27</idade>" + 
    			"            <nome>Alana</nome>" + 
    			"        </pessoa>" + 
    			"        <pessoa>" + 
    			"            <idade>39</idade>" + 
    			"            <nome>Filisbino</nome>" + 
    			"        </pessoa>" + 
    			"    </pessoas>");
    	processorXmlToJson.process(exchange);
    }
    

}
