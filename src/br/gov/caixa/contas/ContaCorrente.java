package br.gov.caixa.contas;

import br.gov.caixa.cliente.Cliente;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

//    @Override
//    public TipoConta getTipo() {
//        return TipoConta.CORRENTE;
//    }
//
//    @Override
//    public void investir(double valor, ContaInvestimento conta, Banco banco) {
//        boolean temContaInvestimento = conta.getUsuarioId().equals(getUsuarioId());
//        if (!temContaInvestimento) {
//            banco.criarContaInvestimento(conta.getUsuarioId());
//        }
//        transferir(valor, conta, banco);
//    }
}
