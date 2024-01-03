package de.jjarndt.camunda.connector.adobe.service.operations.createpdf;

import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.CreatePDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.createpdf.CreatePDFOptions;
import com.adobe.pdfservices.operation.pdfops.options.createpdf.PageLayout;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;
import de.jjarndt.camunda.connector.adobe.model.OperationInput;

import java.util.Map;

public final class CreatePDFFromStaticHTML extends AbstractPDFOperation {
    public CreatePDFFromStaticHTML(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(OperationInput input) throws Exception {
        CreatePDFOperation htmlToPDFOperation = CreatePDFOperation.createNew();
        htmlToPDFOperation.setInput(input.source());

        Map<String, String> options = input.options();
        CreatePDFOptions createPDFOptions = buildCreatePDFOptions(options);
        htmlToPDFOperation.setOptions(createPDFOptions);

        return htmlToPDFOperation.execute(input.executionContext());
    }

    private CreatePDFOptions buildCreatePDFOptions(Map<String, String> options) {
        boolean includeHeaderFooter = Boolean.parseBoolean(options.getOrDefault("includeheaderFooter", "true"));
        double width = Double.parseDouble(options.getOrDefault("pagewidth", "8"));
        double height = Double.parseDouble(options.getOrDefault("pageheight", "11.5"));

        PageLayout pageLayout = new PageLayout();
        pageLayout.setPageSize(width, height);

        return CreatePDFOptions.htmlOptionsBuilder()
                .includeHeaderFooter(includeHeaderFooter)
                .withPageLayout(pageLayout)
                .build();
    }
}

