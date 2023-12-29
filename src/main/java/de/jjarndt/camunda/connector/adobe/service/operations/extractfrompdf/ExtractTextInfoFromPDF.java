package de.jjarndt.camunda.connector.adobe.service.operations.extractfrompdf;

import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExtractPDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractElementType;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractPDFOptions;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;
import de.jjarndt.camunda.connector.adobe.util.OperationInput;

import java.util.Arrays;

public final class ExtractTextInfoFromPDF extends AbstractPDFOperation {
    public ExtractTextInfoFromPDF(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(OperationInput input) throws Exception {
        ExtractPDFOperation extractPDFOperation = ExtractPDFOperation.createNew();
        extractPDFOperation.setInputFile(input.source());

        ExtractPDFOptions extractPDFOptions = ExtractPDFOptions.extractPdfOptionsBuilder()
                .addElementsToExtract(Arrays.asList(ExtractElementType.TEXT))
                .build();
        extractPDFOperation.setOptions(extractPDFOptions);

        return extractPDFOperation.execute(input.executionContext());
    }
}

