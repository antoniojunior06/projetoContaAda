package br.gov.caixa.contas;

import br.gov.caixa.Constantes;
import br.gov.caixa.usuario.Classificacao;

public class ContaInvestimento extends Conta {

    private final TipoConta tipoConta = TipoConta.INVESTIMENTO;

    private double taxaRendimentoAoMes;

    public ContaInvestimento(String usuarioId) {
        super(usuarioId);
        this.taxaRendimentoAoMes = getClassificacao() == Classificacao.PF ? Constantes.TAXA_RENDIMENTO_PF : Constantes.TAXA_RENDIMENTO_PJ;
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
