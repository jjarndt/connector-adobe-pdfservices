package de.jjarndt.camunda.connector.adobe.service.operations.extractfrompdf;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExtractPDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractElementType;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractPDFOptions;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;

import java.util.Arrays;

public final class ExtractTextTableInfoFromPDF extends AbstractPDFOperation {
    public ExtractTextTableInfoFromPDF(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(ExecutionContext executionContext, FileRef source) throws Exception {
        ExtractPDFOperation extractPDFOperation = ExtractPDFOperation.createNew();
        extractPDFOperation.setInputFile(source);

        ExtractPDFOptions extractPDFOptions = ExtractPDFOptions.extractPdfOptionsBuilder()
                .addElementsToExtract(Arrays.asList(ExtractElementType.TEXT, ExtractElementType.TABLES))
                .build();
        extractPDFOperation.setOptions(extractPDFOptions);

        return extractPDFOperation.execute(executionContext);
    }
}
