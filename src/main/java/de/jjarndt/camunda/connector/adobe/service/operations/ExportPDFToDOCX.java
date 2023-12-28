package de.jjarndt.camunda.connector.adobe.service.operations;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExportPDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.exportpdf.ExportPDFTargetFormat;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;

public final class ExportPDFToDOCX extends AbstractPDFOperation {
    public ExportPDFToDOCX(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(ExecutionContext executionContext, FileRef source) throws Exception {
        ExportPDFOperation exportPdfOperation = ExportPDFOperation.createNew(ExportPDFTargetFormat.DOCX);
        exportPdfOperation.setInput(source);
        return exportPdfOperation.execute(executionContext);
    }
}

