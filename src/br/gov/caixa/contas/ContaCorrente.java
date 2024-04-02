package br.gov.caixa.contas;

import br.gov.caixa.Banco;

public class ContaCorrente extends Conta {

    private final TipoConta tipoConta = TipoConta.CORRENTE;

    public ContaCorrente(String usuarioId) {
        super(usuarioId);
    }


    public void investir(double valor, ContaInvestimento conta, Banco banco) {
        verificarStatusConta();
        if ((conta.getUsuarioId().equals(getUsuarioId()))) {
            transferir(valor, conta, banco);
        }
    }

    public TipoConta getTipo() {
        return tipoConta;
    }
}
