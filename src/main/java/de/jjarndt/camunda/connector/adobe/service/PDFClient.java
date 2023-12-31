package de.jjarndt.camunda.connector.adobe.service;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import de.jjarndt.camunda.connector.adobe.model.AuthenticationRequestData;

public class PDFClient {
    private final String clientId;
    private final String clientSecret;

    public PDFClient(AuthenticationRequestData authenticationData) {
        this.clientId = authenticationData.clientId();
        this.clientSecret = authenticationData.clientSecret();
    }

    public ExecutionContext createExecutionContext() {
        return ExecutionContext.create(getCredentials());
    }

    private Credentials getCredentials() {
        return Credentials.servicePrincipalCredentialsBuilder()
                .withClientId(clientId)
                .withClientSecret(clientSecret)
                .build();
    }

}
