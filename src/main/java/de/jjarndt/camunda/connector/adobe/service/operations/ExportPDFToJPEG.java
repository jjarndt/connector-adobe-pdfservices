package de.jjarndt.camunda.connector.adobe.service.operations;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExportPDFToImagesOperation;
import com.adobe.pdfservices.operation.pdfops.options.exportpdftoimages.ExportPDFToImagesTargetFormat;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import java.util.List;

public final class ExportPDFToJPEG extends AbstractPDFOperation {
    public ExportPDFToJPEG(PDFClient client) {
        super(client);
    }

    //TODO: continue
    @Override
    protected FileRef performOperation(ExecutionContext executionContext, FileRef source) throws Exception {
        ExportPDFToImagesOperation exportPDFToImagesOperation = ExportPDFToImagesOperation.createNew(ExportPDFToImagesTargetFormat.JPEG);
        exportPDFToImagesOperation.setInput(source);
        List<FileRef> results = exportPDFToImagesOperation.execute(executionContext);

        return null;
    }
}
