package de.jjarndt.camunda.connector.adobe.service.operations.ocr;

import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.OCROperation;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;
import de.jjarndt.camunda.connector.adobe.util.OperationInput;

public final class CreateOCR extends AbstractPDFOperation {
    public CreateOCR(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(OperationInput input) throws Exception {
        OCROperation ocrOperation = OCROperation.createNew();
        ocrOperation.setInput(input.source());
        return ocrOperation.execute(input.executionContext());
    }
}
