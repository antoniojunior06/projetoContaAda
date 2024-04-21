package br.gov.caixa.utils;

import br.gov.caixa.contas.Conta;
import br.gov.caixa.status.Constantes;
import br.gov.caixa.usuario.Classificacao;

public class CalculoTaxaConta {

    public static double calcularTaxa(double valor, Conta conta) {
        if (conta.getClassificacao() == Classificacao.PJ) {
            return valor * Constantes.TAXA_SAQUE_PJ;
        }
        return valor * Constantes.TAXA_SAQUE_PF;
    }
}