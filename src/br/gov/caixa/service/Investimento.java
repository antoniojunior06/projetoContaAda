package br.gov.caixa.service;


import br.gov.caixa.cliente.Cliente;
import br.gov.caixa.contas.Conta;

import java.math.BigDecimal;

public interface Investimento {

    void investir(Cliente cliente, Integer numeroConta, BigDecimal valor);

}
