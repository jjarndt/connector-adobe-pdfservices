package de.jjarndt.camunda.connector.adobe.model;

import io.camunda.connector.generator.java.annotation.TemplateProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ConnectorRequest(
        @Valid @NotNull @TemplateProperty(group = "authentication", label = "Authentication Data", description = "Authentication data for Adobe PDF Services")
        AuthenticationRequestData authentication,
        @Valid @NotNull @TemplateProperty(group = "requestDetails", label = "Request Details", description = "Details of the request")
        RequestDetails requestDetails
) {}
