package com.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.camel.processor.PessoaProcessorJsonToXml;
import com.camel.processor.PessoaProcessorXmlToJson;

@Component
@Configuration
public class RestPessoaRoute extends RouteBuilder{

	private final String localhost8081 = "jetty://http://localhost:8081/json-to-xml";
	private final String localhost8083 = "jetty://http://localhost:8083/xml-to-json";
	private final String localhost8000XML = "jetty://http://localhost:8000/api/xml";
	private final String localhost8000JSON = "jetty://http://localhost:8000/api/json";
	
	@Override
	public void configure() throws Exception {

	 // JSON TO XML -------------------------------------------------------------------
	    
	    from(localhost8081)
		.to(localhost8000JSON+"?bridgeEndpoint=true")
		.convertBodyTo(String.class)
		.log(" LOG 1 ---------> ${body}")
			.doTry()
			.process(new PessoaProcessorJsonToXml())
	        .doCatch(Exception.class)
	        	.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
						System.out.println(cause);
					}
	        	});
		
	// XML TO JSON -------------------------------------------------------------------
	    
		from(localhost8083)        
		.to(localhost8000XML+"?bridgeEndpoint=true")
		.convertBodyTo(String.class)
			.doTry()
			.process(new PessoaProcessorXmlToJson())
	        .doCatch(Exception.class)
	        	.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
						System.out.println(cause);
					}
	        	});
	}
}
