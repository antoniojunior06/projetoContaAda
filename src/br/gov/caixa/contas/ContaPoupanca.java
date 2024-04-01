package br.gov.caixa.contas;

public class ContaPoupanca extends Conta {

    private final TipoConta tipoConta = TipoConta.POUPANCA;

    public ContaPoupanca(String usuarioId) {
        super(usuarioId);
    }

    public TipoConta getTipo() {
        return tipoConta;
    }
}
