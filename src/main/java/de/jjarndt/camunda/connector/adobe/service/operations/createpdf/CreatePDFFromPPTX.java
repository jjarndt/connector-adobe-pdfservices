package de.jjarndt.camunda.connector.adobe.service.operations.createpdf;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.CreatePDFOperation;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;

public final class CreatePDFFromPPTX extends AbstractPDFOperation {
    public CreatePDFFromPPTX(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(ExecutionContext executionContext, FileRef source) throws Exception {
        CreatePDFOperation createPdfOperation = CreatePDFOperation.createNew();
        createPdfOperation.setInput(source);
        return createPdfOperation.execute(executionContext);
    }
}

