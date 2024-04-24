package br.gov.caixa.service;


import br.gov.caixa.cliente.Cliente;

import java.math.BigDecimal;

public interface Investimento {

    void investir(Cliente cliente, BigDecimal valor);

}
