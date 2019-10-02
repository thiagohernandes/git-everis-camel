package com.camel.route;

import javax.xml.bind.JAXBContext;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.camel.domain.Pessoa;
import com.camel.domain.PessoaJSON;

@Component
@Configuration
public class RestPessoaRouteMarshalUnmarshal extends RouteBuilder{

	private final String localhost9111 = "jetty://http://localhost:9111/json-to-xml";
	private final String localhost9112 = "jetty://http://localhost:9112/xml-to-json";
	private final String localhost6000 = "jetty://http://localhost:8000/api/xml";
	private final String localhost6001 = "jetty://http://localhost:8000/api/json";
	
	@Override
	public void configure() throws Exception {

	 // JSON TO XML -------------------------------------------------------------------
	    
	    from(localhost9111)
		.to(localhost6001+"?bridgeEndpoint=true")
		.convertBodyTo(String.class)
		.log(" LOG 1 ---------> ${body}");
//			.doTry()
//	        .doCatch(Exception.class)
//	        	.process(new Processor() {
//					public void process(Exchange exchange) throws Exception {
//						Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
//						System.out.println(cause);
//					}
//	        	});
		
	// XML TO JSON -------------------------------------------------------------------
	    
		from(localhost9112)        
		.to(localhost6000+"?bridgeEndpoint=true")
		.convertBodyTo(String.class)
		.log("log 1 ${body}")
		.process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				//exchange.getIn().getBody().toString().replace("\"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
				exchange.getIn().setBody("<pessoas><pessoa><idade>22</idade><nome>Beltrano</nome></pessoa><pessoa><idade>27</idade><nome>Alana</nome></pessoa><pessoa><idade>39</idade><nome>Filisbino</nome></pessoa></pessoas>");
			}
    	})
		.unmarshal().json(JsonLibrary.Jackson, PessoaJSON[].class)
		.log("log 2 ${body}")
	//	.unmarshal().json()
	//	
		
		.log("log 3 ${in.body}");
//			.doTry()
//	        .doCatch(Exception.class)
//	        	.process(new Processor() {
//					public void process(Exchange exchange) throws Exception {
//						Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
//						System.out.println(cause);
//					}
//	        	});
	}
}
