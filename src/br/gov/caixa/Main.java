package br.gov.caixa;

import br.gov.caixa.cliente.Cliente;
import br.gov.caixa.service.TransacaoBancaria;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Main {

    private static final String PATH_BASE = "C:/Users/anton/IdeaProjects/ada/projetoContaBanco/";
    public static void main(String[] args) throws IOException {

        TransacaoBancaria tb = new TransacaoBancaria();

        Path path = Path.of("pessoas.csv");

        List<Cliente> clientes = Files.lines(path)
                .skip(1)
                .map(linha -> linha.split(","))
                .filter(coluna -> ("2".equals(coluna[3]) &&
                        LocalDate.parse(coluna[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                                .until(LocalDate.now(), ChronoUnit.YEARS) >= 18) ||
                        "1".equals(coluna[3]))
                .map(coluna -> {
                    Cliente cliente = new Cliente(coluna[2], coluna[0]);
                    deposito(tb, cliente);
                    return cliente;
                })
                .toList();

        List<String> linhasClientes = clientes.stream()
                .map(cliente -> STR."\{cliente.getNome()};\{cliente.getId()};\{cliente.getClassificacao()};\{getNumero(cliente)};\{getSaldo(cliente)}")
                .toList();

        Path destino = Path.of(STR."\{PATH_BASE}contas.csv");
        Files.write(destino, linhasClientes);

    }

    private static BigDecimal getSaldo(Cliente cliente) {
        return cliente.getContas().getFirst().getSaldo();
    }

    private static Integer getNumero(Cliente cliente) {
        return cliente.getContas().getFirst().getNumero();
    }

    private static void deposito(TransacaoBancaria tb, Cliente cliente) {
        tb.depositar(cliente, getNumero(cliente), new BigDecimal("50.00"));
    }
}
