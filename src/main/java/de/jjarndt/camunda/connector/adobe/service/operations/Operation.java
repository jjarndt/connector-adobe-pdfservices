package de.jjarndt.camunda.connector.adobe.service.operations;

import de.jjarndt.camunda.connector.adobe.model.ConnectorRequest;
import de.jjarndt.camunda.connector.adobe.model.ConnectorResponse;

public interface Operation {
    ConnectorResponse execute(ConnectorRequest request);
}
