package de.jjarndt.camunda.connector.adobe.service.operations;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import de.jjarndt.camunda.connector.adobe.model.ConnectorRequest;
import de.jjarndt.camunda.connector.adobe.model.ConnectorResponse;
import de.jjarndt.camunda.connector.adobe.model.DestinationType;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.util.OperationInput;
import de.jjarndt.camunda.connector.adobe.util.OptionsParser;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

public abstract class AbstractPDFOperation implements Operation {
    protected final PDFClient client;

    public AbstractPDFOperation(PDFClient client) {
        this.client = client;
    }

    protected abstract FileRef performOperation(OperationInput input) throws Exception;

    @Override
    public ConnectorResponse execute(ConnectorRequest request) {
        try {
            FileRef result = performOperation(createOperationInput(request));
            saveResultFile(result, request);
            return new ConnectorResponse(true, "Operation successfully", request.requestDetails().destinationPath());
        } catch (Exception e) {
            return new ConnectorResponse(false, "Operation-Error: " + e.getMessage(), null);
        }
    }

    private OperationInput createOperationInput(ConnectorRequest request) throws Exception {
        ExecutionContext executionContext = client.createExecutionContext();
        FileRef source = createSourceFileRef(request);
        Map<String, Integer> options = OptionsParser.parseOptions(request.requestDetails().options());
        return new OperationInput(executionContext, source, options);
    }

    private FileRef createSourceFileRef(ConnectorRequest request) throws Exception {
        return switch (request.requestDetails().sourceType()) {
            case LOCAL_FILE -> FileRef.createFromLocalFile(request.requestDetails().sourcePath());
            case URL -> FileRef.createFromURL(new URL(request.requestDetails().sourcePath()));
        };
    }

    private void saveResultFile(FileRef result, ConnectorRequest request) throws IOException {
        if (request.requestDetails().destinationType() == DestinationType.LOCAL_FILE) {
            result.saveAs(request.requestDetails().destinationPath());
        } else if (request.requestDetails().destinationType() == DestinationType.EXTERNAL_STORAGE) {
            // needs impl
        }
    }
}
