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
                .map(s -> s.split("=", 2))
                .filter(arr -> arr.length == 2 && !arr[0].trim().isEmpty() && !arr[1].trim().isEmpty())
                .collect(Collectors.toMap(
                        arr -> arr[0].trim().toLowerCase(),
                        arr -> arr[1].trim().replace('-', '_').toUpperCase(),
                        (existing, replacement) -> existing
                ));
    }

}
