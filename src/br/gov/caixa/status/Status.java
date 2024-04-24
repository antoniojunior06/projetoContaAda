package br.gov.caixa.status;

import br.gov.caixa.contas.Conta;

public enum Status {
    ATIVO,
    INATIVO;

    public <T extends Conta> void alterarStatusConta(T conta) {
        if (conta.getStatus() == Status.ATIVO) {
            conta.setStatus(Status.INATIVO);
        }else {
            conta.setStatus(Status.ATIVO);
        }
    }
}
