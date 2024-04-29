package br.gov.caixa;

import br.gov.caixa.arquivo.CriacaoArquivo;
import br.gov.caixa.arquivo.LeituraArquivo;

import java.util.List;

public class Main {

    private static final String PATH_BASE = "C:/Users/anton/IdeaProjects/ada/projetoContaBanco/";
    public static void main(String[] args) {

        /* Rodrigo, no meu projeto a definição se é PF ou PJ é feita de acordo com a quantidade de dígitos.
           11 para CPF e 14 para CNPJ porque na vida real o que vai definir se um conta é uma ou outra é
           a numeração, para mim faz mais sentido assim. Desta forma, não há necessidade de instanciar ClientePF
           e ClientePJ. Se puder fazer a correção se isso é uma boa prática e o que pode incorrer em prejuízo
           para o projeto, agradeço. E aí fiz uma modificação no arquivo pessoas para que a numeração das linhas
           que sejam PJ tivesse 14 digítos, foi até fácil fiz a substituição simultânea no excel incluído 000 no
           final da numeração.
         */

        List<String> clientes = LeituraArquivo.lerClientesDoArquivo("pessoas.csv");
        CriacaoArquivo.criarAquivo(STR."\{PATH_BASE}contas.csv", clientes);
    }


}
