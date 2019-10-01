package com.camel.code;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.camel.code.parte01.util.UtilApp;

@SpringBootApplication
public class ProjetoBaseApplication {

	@Autowired
	UtilApp utilApp;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoBaseApplication.class, args);
	}
	
	@PostConstruct
	private void init() throws Exception {
		List<Object> listaRoutes = new ArrayList<>();
		// Part 1
//		listaRoutes.add(new MoveFilesBean());
//		utilApp.runContextCamel(listaRoutes,4000,false);
		// Part 2
//		listaRoutes.add(new R01PrimeiraRotaJetty());
//		listaRoutes.add(new R02JettyToRest());
//		listaRoutes.add(new R03Http4Processor());
//		listaRoutes.add(new R04Http4PredicateChoice());
//		listaRoutes.add(new R05Bean());
//		listaRoutes.add(new R06JettyToRestIntegracao());
	//	listaRoutes.add(new C00BeanClass());
		utilApp.runContextCamel(listaRoutes,4000,true);
		
	}  
	
}
