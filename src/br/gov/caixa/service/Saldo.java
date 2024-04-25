package br.gov.caixa.service;

import br.gov.caixa.cliente.Cliente;

import java.math.BigDecimal;

public interface Saldo {

    BigDecimal consultarSaldo(Cliente cliente, Integer numeroConta);

}
