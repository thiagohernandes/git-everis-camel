package com.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONArray;
import org.json.XML;

public class PessoaProcessorJsonToXml implements Processor {
	
	public void process(Exchange exchange) throws Exception {
		System.out.println("-------------------- PROCESSOR --------------------" + exchange.getIn().getBody());
		JSONArray array = new JSONArray(exchange.getIn().getBody().toString());
	    String xml = XML.toString(array, "pessoa");
	    System.out.println("-------------------- AFTER PROCESSOR --------------------" + mountXMLPessoa(xml));
		
	}
	
	private String mountXMLPessoa(String xmlStr) {
		String beginPessoas = "<pessoas>";
		String endPessoas = "</pessoas>";
		String headerXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		return headerXML.concat(beginPessoas).concat(xmlStr).concat(endPessoas);
	}

}
