package com.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;
import org.json.XML;

public class PessoaProcessorXmlToJson implements Processor {
	
	public void process(Exchange exchange) throws Exception {
		System.out.println("-------------------- PROCESSOR --------------------" + exchange.getIn().getBody());
		JSONObject array = XML.toJSONObject(exchange.getIn().getBody().toString());
	    System.out.println("-------------------- AFTER PROCESSOR --------------------" + array.toString());
	}

}
