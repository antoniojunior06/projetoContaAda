package br.gov.caixa.cliente;

import br.gov.caixa.status.Constantes;

public enum Classificacao {
    PF,
    PJ;

    public static Classificacao verificarClassificacao(String id) {
        return id.length() == Constantes.DIGITO_PF ? Classificacao.PF : Classificacao.PJ;
    }
}
