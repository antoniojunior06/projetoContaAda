package br.gov.caixa.contas;

import br.gov.caixa.usuario.Classificacao;

public class ContaInvestimento extends Conta {

    private final TipoConta tipoConta = TipoConta.INVESTIMENTO;

    private double taxaRendimentoAoMes;

    public ContaInvestimento(String usuarioId) {
        super(usuarioId);
        this.taxaRendimentoAoMes = getClassificacao() == Classificacao.PF ? 0.01 : 0.02;
    }

    public void gerarRendimento() {
        double rendimento = consultaSaldo() * taxaRendimentoAoMes;
        depositar(rendimento);
    }

    public TipoConta getTipo() {
        return tipoConta;
    }

    public double getTaxaRendimentoAoMes() {
        return taxaRendimentoAoMes;
    }

    public void setTaxaRendimentoAoMes(double taxaRendimentoAoMes) {
        this.taxaRendimentoAoMes = taxaRendimentoAoMes;
    }
}
