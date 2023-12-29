package de.jjarndt.camunda.connector.adobe.service.operations.createpdf;

import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.CreatePDFOperation;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;
import de.jjarndt.camunda.connector.adobe.model.OperationInput;

public final class CreatePDFFromDOCX extends AbstractPDFOperation {
    public CreatePDFFromDOCX(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(OperationInput input) throws Exception {
        CreatePDFOperation createPdfOperation = CreatePDFOperation.createNew();
        createPdfOperation.setInput(input.source());
        return createPdfOperation.execute(input.executionContext());
    }
}
