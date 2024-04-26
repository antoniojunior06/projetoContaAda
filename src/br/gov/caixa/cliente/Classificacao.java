package br.gov.caixa.cliente;

public enum Classificacao {
    PF,
    PJ;

    private static final int DIGITO_PF = 11;

    public static Classificacao verificarClassificacao(String id) {
        return id.length() == DIGITO_PF ? Classificacao.PF : Classificacao.PJ;
    }
}
