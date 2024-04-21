package br.gov.caixa.contas;

import br.gov.caixa.usuario.Usuario;

public class ContaPoupanca extends Conta {

    private Usuario usuario;

    public ContaPoupanca(String usuarioId) {
        super(usuarioId);
    }

    @Override
    public TipoConta getTipo() {
        return TipoConta.POUPANCA;
    }
}
