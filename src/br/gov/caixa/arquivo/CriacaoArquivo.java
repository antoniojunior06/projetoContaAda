package br.gov.caixa.arquivo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CriacaoArquivo {

    public static void criarAquivo(String arquivo, List<String> clientes) {
        try {
            Path destino = Path.of(arquivo);
            Files.write(destino, clientes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
