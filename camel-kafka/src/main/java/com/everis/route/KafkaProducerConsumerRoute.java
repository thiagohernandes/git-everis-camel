package com.everis.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.context.annotation.Configuration;

import com.everis.model.Mensagem;
import com.google.gson.Gson;

/*
 * ------------------------------ COMANDOS KAFKA ------------------------------ 
 * zookeeper-server-start.bat zookeeper.properties
 * kafka-server-start.bat server.properties
 * kafka-console-producer.bat --broker-list localhost:9092 --topic everis-topic
 * kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic everis-topic --from-beginning
 * */

@Configuration
public class KafkaProducerConsumerRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		Mensagem msg = new Mensagem("Teste", "Corpo");
		
		from("timer:everis?period=2s")
		.setBody(constant(msg))          
		.setHeader(KafkaConstants.KEY, constant("Camel")) 
	    .to("kafka:everis-topic?brokers=localhost:9092");
		
		from("kafka:everis-topic?brokers=localhost:9092")
		.process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				Gson gson = new Gson();
				String jsonInString = gson.toJson(msg);
				exchange.getIn().setBody(jsonInString);
			}
    	})
	    .log("Mensagem vinda do Kafka : ${body}")
	    .log("    topic ${headers[kafka.TOPIC]}")
	    .log("    partition ${headers[kafka.PARTITION]}")
	    .log("    offset ${headers[kafka.OFFSET]}")
	    .log("    key ${headers[kafka.KEY]}");
		
	}

}
