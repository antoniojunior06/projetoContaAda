package br.gov.caixa.arquivo;

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

public class LeituraArquivo {

    public static List<String> lerClientesDoArquivo(String arquivo) {

        Path path = Path.of(arquivo);

        try {
            return Files.lines(path)
                    .skip(1)
                    .map(linha -> linha.split(","))
                    .filter(coluna -> ("2".equals(coluna[3]) &&
                            LocalDate.parse(coluna[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                                    .until(LocalDate.now(), ChronoUnit.YEARS) >= 18) ||
                            "1".equals(coluna[3]))
                    .map(coluna -> {
                        Cliente cliente = new Cliente(coluna[2], coluna[0]);
                        deposito(cliente);
                        return cliente;
                    })
                    .map(cliente -> STR."\{cliente.getNome()};\{cliente.getId()};\{cliente.getClassificacao()};\{cliente.getNumeroConta()};\{getSaldo(cliente)}")
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static BigDecimal getSaldo(Cliente cliente) {
        return cliente.getContas().getFirst().getSaldo();
    }

    private static void deposito(Cliente cliente) {
        new TransacaoBancaria().depositar(cliente, cliente.getNumeroConta(), new BigDecimal("50.00"));
    }




}
