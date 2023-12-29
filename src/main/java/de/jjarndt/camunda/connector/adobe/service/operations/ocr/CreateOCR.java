package de.jjarndt.camunda.connector.adobe.service.operations.ocr;

import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.OCROperation;
import com.adobe.pdfservices.operation.pdfops.options.ocr.OCROptions;
import com.adobe.pdfservices.operation.pdfops.options.ocr.OCRSupportedLocale;
import com.adobe.pdfservices.operation.pdfops.options.ocr.OCRSupportedType;
import de.jjarndt.camunda.connector.adobe.model.OperationInput;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;

import java.io.IOException;
import java.util.Map;

public final class CreateOCR extends AbstractPDFOperation {
    public CreateOCR(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(OperationInput input)  throws ServiceApiException, IOException {
        Map<String, String> options = input.options();
        OCROptions ocrOptions = buildOCROptions(options);

        OCROperation ocrOperation = OCROperation.createNew();
        ocrOperation.setInput(input.source());
        ocrOperation.setOptions(ocrOptions);

        return ocrOperation.execute(input.executionContext());
    }

    private OCROptions buildOCROptions(Map<String, String> options) {
        OCRSupportedLocale locale = OCRSupportedLocale.valueOf(options.getOrDefault("locale", "EN_US"));
        OCRSupportedType type = OCRSupportedType.valueOf(options.getOrDefault("type", "SEARCHABLE_IMAGE_EXACT"));

        return OCROptions.ocrOptionsBuilder()
                .withOCRLocale(locale)
                .withOCRType(type)
                .build();
    }
}
