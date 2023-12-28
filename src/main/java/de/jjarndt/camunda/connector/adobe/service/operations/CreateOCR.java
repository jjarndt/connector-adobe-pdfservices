package de.jjarndt.camunda.connector.adobe.service.operations;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.OCROperation;
import de.jjarndt.camunda.connector.adobe.model.ConnectorRequest;
import de.jjarndt.camunda.connector.adobe.model.ConnectorResponse;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;

public final class CreateOCR implements Operation {

    private final PDFClient client;

    public CreateOCR(PDFClient client) {
        this.client = client;
    }

    @Override
    public ConnectorResponse execute(ConnectorRequest request) {
        try {
            ExecutionContext executionContext = client.createExecutionContext();
            OCROperation ocrOperation = OCROperation.createNew();
            FileRef source = FileRef.createFromLocalFile(request.requestDetails().sourcePath());
            ocrOperation.setInput(source);

            FileRef result = ocrOperation.execute(executionContext);
            result.saveAs(request.requestDetails().destinationPath());
            return new ConnectorResponse(true, "OCR erfolgreich ausgeführt", request.requestDetails().destinationPath());
        } catch (Exception e) {
            return new ConnectorResponse(false, "OCR-Fehler: " + e.getMessage(), null);
        }
    }

}
