package de.jjarndt.camunda.connector.adobe.service.operations.exportpdf;

import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExportPDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.exportpdf.ExportPDFTargetFormat;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;
import de.jjarndt.camunda.connector.adobe.model.OperationInput;

public final class ExportPDFToDOCX extends AbstractPDFOperation {
    public ExportPDFToDOCX(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(OperationInput input) throws Exception {
        ExportPDFOperation exportPdfOperation = ExportPDFOperation.createNew(ExportPDFTargetFormat.DOCX);
        exportPdfOperation.setInput(input.source());
        return exportPdfOperation.execute(input.executionContext());
    }
}

