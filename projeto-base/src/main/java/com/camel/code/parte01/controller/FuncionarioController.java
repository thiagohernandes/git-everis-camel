package com.camel.code.parte01.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camel.code.parte01.domain.Funcionario;
import com.camel.code.parte01.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FuncionarioService service;
	
	@GetMapping("/todos")
	public List<Funcionario> todosFuncionarios() {
		logger.info("********** {}", "todosFuncionarios get - Controller - call - Todos Funcionários");
		return service.listaFuncionarios();
	}
	
	@PostMapping("/todos-post")
	public List<Funcionario> todosFuncionariosPost() {
		logger.info("********** {}", "todosFuncionarios post - Controller - call - Todos Funcionários");
		return service.listaFuncionarios();
	}
	
	@GetMapping("/todos-empty")
	public List<Funcionario> todosFuncionariosEmpty() {
		logger.info("********** {}", "todosFuncionariosEmpty - Controller - call - Todos Funcionários Empty");
		return Arrays.asList();
	}
}
