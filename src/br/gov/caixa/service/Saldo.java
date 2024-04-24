package br.gov.caixa.service;

import br.gov.caixa.cliente.Cliente;

import java.math.BigDecimal;

public interface Saldo<T extends Cliente> {

    BigDecimal consultarSaldo(T cliente, Integer numeroConta);

}
