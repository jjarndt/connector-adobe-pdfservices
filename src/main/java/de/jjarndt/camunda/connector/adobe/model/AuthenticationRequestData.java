package de.jjarndt.camunda.connector.adobe.model;

import io.camunda.connector.generator.java.annotation.TemplateProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.ToString;

public record AuthenticationRequestData(
        @ToString.Exclude @NotEmpty @TemplateProperty(group = "authentication", label = "Client ID", description = "The client ID for authentication")
        String clientId,

        @NotEmpty @TemplateProperty(group = "authentication", label = "Client Secret", description = "The client secret for authentication")
        String clientSecret
) {
}

