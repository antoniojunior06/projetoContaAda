package br.gov.caixa.acao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Acao {

    private LocalDate data;
    private TipoAcao tipo;
    private BigDecimal valor;
    private String observacao;

    public Acao(TipoAcao tipo, BigDecimal valor, String observacao) {
        this.data = LocalDate.now();
        this.tipo = tipo;
        this.valor = valor;
        this.observacao = observacao;
    }

    public LocalDate getData() {
        return data;
    }
    public TipoAcao getTipo() {
        return tipo;
    }
    public String getObservacao() {
        return observacao;
    }
    public BigDecimal getValor() {
        return valor;
    }
}
