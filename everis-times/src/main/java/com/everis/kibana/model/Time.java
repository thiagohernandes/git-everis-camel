package com.everis.kibana.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Time {
	
	private @NonNull String nome;
	private @NonNull Integer anoFundacao;
	private @NonNull Integer mundial;

}
