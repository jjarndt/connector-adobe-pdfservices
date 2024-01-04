package de.jjarndt.camunda.connector.adobe.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OptionsParser {

    public static Map<String, String> parseOptions(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Map.of();
        }

        return Arrays.stream(input.split(";"))
                .map(s -> s.replace(':', '=').trim())
                .map(s -> s.replaceAll("\\{", "").replaceAll("\\}", "")) // Entfernt Klammern
                .map(s -> s.split("=", 2))
                .filter(arr -> arr.length == 2 && !arr[0].trim().isEmpty() && !arr[1].trim().isEmpty())
                .collect(Collectors.toMap(
                        arr -> {
                            String key = arr[0].trim().toLowerCase();
                            // Nimmt nur den Teil nach dem letzten Unterstrich
                            int underscoreIndex = key.lastIndexOf('_');
                            if (underscoreIndex != -1) {
                                key = key.substring(underscoreIndex + 1);
                            }
                            return key;
                        },
                        arr -> arr[1].trim().replace('-', '_').toUpperCase(),
                        (existing, replacement) -> existing
                ));
    }
}
