package com.everis.camel.converter;

import java.io.IOException;

import org.apache.camel.Converter;

import com.everis.camel.domain.Pedido;

@Converter
public final class PedidoConverter {

    @Converter
    public static void toFazerAlgumaCoisa(Pedido data) throws IOException, ClassNotFoundException {
        System.out.println(" -- - - - - - -> " + data.getDescricao());
    }

}
