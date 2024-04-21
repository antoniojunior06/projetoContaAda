package br.gov.caixa.contas;

import br.gov.caixa.Banco;
import br.gov.caixa.usuario.Usuario;

public class ContaCorrente extends Conta implements Investimento{

    public ContaCorrente(Usuario usuario) {
        super(usuario.getId());
    }

    @Override
    public TipoConta getTipo() {
        return TipoConta.CORRENTE;
    }

    @Override
    public void investir(double valor, ContaInvestimento conta, Banco banco) {
        verificarStatusConta();
        if ((conta.getUsuarioId().equals(getUsuarioId()))) {
            transferir(valor, conta, banco);
        }
    }
}
