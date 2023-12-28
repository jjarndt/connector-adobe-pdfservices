package de.jjarndt.camunda.connector.adobe.service.operations;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import de.jjarndt.camunda.connector.adobe.model.ConnectorRequest;
import de.jjarndt.camunda.connector.adobe.model.ConnectorResponse;
import de.jjarndt.camunda.connector.adobe.model.DestinationType;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractPDFOperation implements Operation {
    protected final PDFClient client;

    public AbstractPDFOperation(PDFClient client) {
        this.client = client;
    }

    protected abstract FileRef performOperation(ExecutionContext executionContext, FileRef source) throws Exception;

    @Override
    public ConnectorResponse execute(ConnectorRequest request) {
        try {
            ExecutionContext executionContext = client.createExecutionContext();
            FileRef source = createSourceFileRef(request);

            FileRef result = performOperation(executionContext, source);
            saveResultFile(result, request);

            return new ConnectorResponse(true, "Operation succesfully", request.requestDetails().destinationPath());
        } catch (Exception e) {
            return new ConnectorResponse(false, "Operation-Error: " + e.getMessage(), null);
        }
    }

    private FileRef createSourceFileRef(ConnectorRequest request) throws Exception {
        return switch (request.requestDetails().sourceType()) {
            case LOCAL_FILE -> FileRef.createFromLocalFile(request.requestDetails().sourcePath());
            case URL -> FileRef.createFromURL(new URL(request.requestDetails().sourcePath()));
        };
    }

    //TODO: save in external storage
    private void saveResultFile(FileRef result, ConnectorRequest request) throws IOException {
        if (request.requestDetails().destinationType() == DestinationType.LOCAL_FILE) {
            result.saveAs(request.requestDetails().destinationPath());
        }
        // needs impl.
    }
}

