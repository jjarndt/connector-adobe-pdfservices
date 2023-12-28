package de.jjarndt.camunda.connector.adobe.model;

public record ConnectorResponse(
        boolean success,
        String message,
        String result
) {
}
