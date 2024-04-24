package br.gov.caixa.contas;

import br.gov.caixa.cliente.Cliente;

public class ContaPoupanca extends Conta {

    private Cliente cliente;

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }
//
//    @Override
//    public TipoConta getTipo() {
//        return TipoConta.POUPANCA;
//    }
}
