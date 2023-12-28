package de.jjarndt.camunda.connector.adobe.service.operations.ocr;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.OCROperation;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;

public final class CreateOCR extends AbstractPDFOperation {
    public CreateOCR(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(ExecutionContext executionContext, FileRef source) throws Exception {
        OCROperation ocrOperation = OCROperation.createNew();
        ocrOperation.setInput(source);
        return ocrOperation.execute(executionContext);
    }
}
