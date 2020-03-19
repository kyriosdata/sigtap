package com.github.kyriosdata.sigtap;

import com.univocity.parsers.fixed.FixedWidthFields;
import com.univocity.parsers.fixed.FixedWidthParser;
import com.univocity.parsers.fixed.FixedWidthParserSettings;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class LeitorFormatoSigtap {

    public static void main(String[] args) {
        tamanhos();
    }

    public static void tamanhos() {
        int[] tamanhos = {10, 10, 2, 10, 2, 1, 6};
        FixedWidthParser parser = getParserFor(tamanhos);

        List<String[]> allRows = parser.parseAll(new File("target" +
                "/rl_excecao_compatibilidade.txt"));
        allRows.stream()
                .map(l -> Arrays.toString(l))
                .forEach(System.out::println);
    }

    private static FixedWidthParser getParserFor(int[] tamanhos) {
        FixedWidthFields lengths = new FixedWidthFields(tamanhos);
        FixedWidthParserSettings settings =
                new FixedWidthParserSettings(lengths);
        settings.getFormat().setPadding(' ');
        settings.getFormat().setLineSeparator("\n");
        return new FixedWidthParser(settings);
    }
}
