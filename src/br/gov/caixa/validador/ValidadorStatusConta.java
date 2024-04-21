package br.gov.caixa.validador;

import br.gov.caixa.contas.Conta;
import br.gov.caixa.status.Status;

public abstract class ValidadorStatusConta {

    public static void validarStatusConta(Conta conta) {
        if (conta.getStatus().equals(Status.INATIVO)) {
            throw new IllegalArgumentException("A conta está inativa");

        }
    }
}
