package br.gov.caixa.usuario;

import br.gov.caixa.Constantes;

public enum Classificacao {
    PF,
    PJ;

    public static Classificacao verificarClassificacao(String id) {
        return id.length() == Constantes.DIGITO_PF ? Classificacao.PF : Classificacao.PJ;
    }
}
