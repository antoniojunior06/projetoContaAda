package br.gov.caixa.service;

import br.gov.caixa.cliente.Cliente;

import java.math.BigDecimal;

public interface Deposito {

    void depositar(Cliente cliente, Integer numeroConta, BigDecimal valor);

}
