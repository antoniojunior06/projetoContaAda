package br.gov.caixa.contas;

import br.gov.caixa.Banco;

public interface Investimento {

    void investir(double valor, ContaInvestimento conta, Banco banco);
}
