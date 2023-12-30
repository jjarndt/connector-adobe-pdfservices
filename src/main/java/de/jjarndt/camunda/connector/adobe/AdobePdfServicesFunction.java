package de.jjarndt.camunda.connector.adobe;

import de.jjarndt.camunda.connector.adobe.model.ConnectorRequest;
import de.jjarndt.camunda.connector.adobe.model.ConnectorResponse;
import de.jjarndt.camunda.connector.adobe.service.AdobePDFService;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.Operation;
import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.generator.java.annotation.ElementTemplate;

@OutboundConnector(
        name = "AdobePDFServices",
        inputVariables = {"authentication", "requestDetails"},
        type = "de.jjarndt.camunda.connector:adobe-pdfservices:1")
@ElementTemplate(
        id = "de.jjarndt.camunda.connector.adobe.PDFConnector.v1",
        name = "Adobe PDF Connector",
        version = 1,
        description = "Connector to interact with Adobe PDF Services",
        icon = "adobe-icon.svg",
        documentationRef = "https://developer.adobe.com/document-services/apis/pdf-services/",
        propertyGroups = {
                @ElementTemplate.PropertyGroup(id = "authentication", label = "Authentication"),
                @ElementTemplate.PropertyGroup(id = "source", label = "Source Information"),
                @ElementTemplate.PropertyGroup(id = "destination", label = "Destination Information"),
                @ElementTemplate.PropertyGroup(id = "operation", label = "Operation Details")
        },
        inputDataClass = ConnectorRequest.class)
public class AdobePdfServicesFunction implements OutboundConnectorFunction {

    private AdobePDFService adobePDFService;

    public AdobePdfServicesFunction(AdobePDFService adobePDFService) {
        this.adobePDFService = adobePDFService;
    }

    public AdobePdfServicesFunction(){}

    @Override
    public Object execute(OutboundConnectorContext context) throws Exception {
        ConnectorRequest request = context.bindVariables(ConnectorRequest.class);
        PDFClient pdfClient = new PDFClient(request.authentication());
        return executeConnector(request, pdfClient);
    }

    private ConnectorResponse executeConnector(ConnectorRequest request, PDFClient pdfClient) {
        Operation operation = adobePDFService.createOperation(request.requestDetails().operationType(), pdfClient);
        return operation.execute(request);
    }
}
