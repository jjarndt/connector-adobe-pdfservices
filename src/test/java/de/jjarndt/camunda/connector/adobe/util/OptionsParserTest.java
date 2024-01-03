package de.jjarndt.camunda.connector.adobe.util;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsParserTest {

    @ParameterizedTest
    @MethodSource("provideOptionsForTest")
    void testParseOptions(String input, Map<String, String> expected) {
        Map<String, String> result = OptionsParser.parseOptions(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideOptionsForTest() {
        return Stream.of(
                Arguments.of("option=EN_US; option2=SEARCH", Map.of("option", "EN_US", "option2", "SEARCH")),
                Arguments.of("option=en-us", Map.of("option", "EN_US")),
                Arguments.of("OPTION=EN_US", Map.of("option", "EN_US")),
                Arguments.of("option:EN_US", Map.of("option", "EN_US")),
                Arguments.of("option: EN_US", Map.of("option", "EN_US")),
                Arguments.of("option= EN_US", Map.of("option", "EN_US")),
                Arguments.of("optionenqwe; option=2", Map.of("option", "2")),
                Arguments.of("", Map.of()),
                Arguments.of(null, Map.of()),
                Arguments.of("optionA=SEARCH; optionB=test; optionC=FIND", Map.of("optiona", "SEARCH", "optionb", "TEST", "optionc", "FIND")),
                Arguments.of("alpha=beta gamma=delta; epsilon=zeta", Map.of("alpha", "BETA GAMMA=DELTA", "epsilon", "ZETA")),
                Arguments.of("key1: value1; key2 = value2", Map.of("key1", "VALUE1", "key2", "VALUE2")),
                Arguments.of("wrong-format", Map.of()),
                Arguments.of("anotherKey: ANOTHER_VALUE; yetAnotherKey=Yet_Another_Value", Map.of("anotherkey", "ANOTHER_VALUE", "yetanotherkey", "YET_ANOTHER_VALUE"))
        );
    }
}
