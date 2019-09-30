package com.everis.camel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.everis.camel.route.C00RoutePedido;
import com.everis.camel.util.UtilApp;

@SpringBootApplication
public class EverisCamelConversaoApplication {

	@Autowired
	UtilApp utilApp;
	
	public static void main(String[] args) {
		SpringApplication.run(EverisCamelConversaoApplication.class, args);
	}
	
	@PostConstruct
	private void init() throws Exception {
		List<Object> listaRoutes = new ArrayList<>();
		listaRoutes.add(new C00RoutePedido());
		utilApp.runContextCamel(listaRoutes,0,true);
		
	}  

}
