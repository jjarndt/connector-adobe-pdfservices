package de.jjarndt.camunda.connector.adobe.util;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OptionsParser {

    public static Map<String, String> parseOptions(String input) {
        if (input == null || input.isEmpty()) {
            return Map.of();
        }

        return Arrays.stream(input.split("[,;\\s]+"))
                .map(s -> s.split("="))
                .filter(arr -> arr.length == 2)
                .collect(Collectors.toMap(
                        arr -> arr[0],
                        arr -> arr[1]
                ));
    }
}
