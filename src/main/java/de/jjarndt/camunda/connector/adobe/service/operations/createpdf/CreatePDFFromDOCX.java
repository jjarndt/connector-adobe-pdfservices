package de.jjarndt.camunda.connector.adobe.service.operations.createpdf;

import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.CreatePDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.createpdf.CreatePDFOptions;
import com.adobe.pdfservices.operation.pdfops.options.createpdf.word.SupportedDocumentLanguage;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;
import de.jjarndt.camunda.connector.adobe.model.OperationInput;

import java.io.IOException;
import java.util.Map;

public final class CreatePDFFromDOCX extends AbstractPDFOperation {
    public CreatePDFFromDOCX(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(OperationInput input) throws ServiceApiException, IOException {
        Map<String, String> options = input.options();
        CreatePDFOptions pdfOptions = buildCreatePDFOptions(options);

        CreatePDFOperation createPdfOperation = CreatePDFOperation.createNew();
        createPdfOperation.setInput(input.source());
        createPdfOperation.setOptions(pdfOptions);

        return createPdfOperation.execute(input.executionContext());
    }

    private CreatePDFOptions buildCreatePDFOptions(Map<String, String> options) {
        SupportedDocumentLanguage documentLanguage = SupportedDocumentLanguage.valueOf(
                options.getOrDefault("documentlanguage", "EN_US"));

        return CreatePDFOptions.wordOptionsBuilder()
                .withDocumentLanguage(documentLanguage)
                .build();
    }
}
