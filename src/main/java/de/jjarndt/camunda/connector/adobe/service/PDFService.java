package de.jjarndt.camunda.connector.adobe.service;

import de.jjarndt.camunda.connector.adobe.model.ConnectorRequest;
import de.jjarndt.camunda.connector.adobe.model.ConnectorResponse;
import de.jjarndt.camunda.connector.adobe.service.operations.Operation;

public class PDFService {
    private final PDFClient client;

    public PDFService(PDFClient client) {
        this.client = client;
    }

    public ConnectorResponse executeOperation(ConnectorRequest request) {
        Operation operation = OperationFactory.createOperation(request.requestDetails().operationType(), client);
        return operation.execute(request);
    }
}
