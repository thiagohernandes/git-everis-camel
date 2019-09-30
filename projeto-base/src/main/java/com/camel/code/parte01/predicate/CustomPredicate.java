package com.camel.code.parte01.predicate;

import org.apache.camel.Predicate;
import org.apache.camel.builder.PredicateBuilder;

public class CustomPredicate {

	public Predicate getSimulacaoPredicate() {
		Predicate validacao = new PredicateValidationLista();
		return PredicateBuilder.and(validacao);
	}
	
}
