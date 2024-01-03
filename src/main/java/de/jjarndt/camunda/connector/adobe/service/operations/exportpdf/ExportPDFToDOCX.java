package de.jjarndt.camunda.connector.adobe.service.operations.exportpdf;

import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExportPDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.exportpdf.ExportOCRLocale;
import com.adobe.pdfservices.operation.pdfops.options.exportpdf.ExportPDFOptions;
import com.adobe.pdfservices.operation.pdfops.options.exportpdf.ExportPDFTargetFormat;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;
import de.jjarndt.camunda.connector.adobe.model.OperationInput;

import java.io.IOException;
import java.util.Map;

public final class ExportPDFToDOCX extends AbstractPDFOperation {
    public ExportPDFToDOCX(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(OperationInput input) throws ServiceApiException, IOException {
        Map<String, String> options = input.options();
        ExportPDFOptions exportPDFOptions = buildExportPDFOptions(options);

        ExportPDFOperation exportPdfOperation = ExportPDFOperation.createNew(ExportPDFTargetFormat.DOCX);
        exportPdfOperation.setInput(input.source());
        exportPdfOperation.setOptions(exportPDFOptions);
        return exportPdfOperation.execute(input.executionContext());
    }

    private ExportPDFOptions buildExportPDFOptions(Map<String, String> options) {
        ExportOCRLocale locale = ExportOCRLocale.valueOf(options.getOrDefault("locale", "EN_US"));
        return new ExportPDFOptions(locale);
    }
}

