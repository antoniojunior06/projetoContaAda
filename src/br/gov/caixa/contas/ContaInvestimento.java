package br.gov.caixa.contas;

import br.gov.caixa.status.Constantes;
import br.gov.caixa.usuario.Classificacao;

public class ContaInvestimento extends Conta {

    private double taxaRendimentoAoMes;

    public ContaInvestimento(String usuarioId) {
        super(usuarioId);
        this.taxaRendimentoAoMes = getClassificacao() == Classificacao.PF ? Constantes.TAXA_RENDIMENTO_PF : Constantes.TAXA_RENDIMENTO_PJ;
    }

    public void gerarRendimento() {
        double rendimento = consultaSaldo() * taxaRendimentoAoMes;
        depositar(rendimento);
    }

    @Override
    public TipoConta getTipo() {
        return TipoConta.INVESTIMENTO;
    }


}
