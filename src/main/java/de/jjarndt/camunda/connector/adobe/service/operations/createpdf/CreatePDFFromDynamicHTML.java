package de.jjarndt.camunda.connector.adobe.service.operations.createpdf;

import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.CreatePDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.createpdf.CreatePDFOptions;
import com.adobe.pdfservices.operation.pdfops.options.createpdf.PageLayout;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;
import de.jjarndt.camunda.connector.adobe.model.OperationInput;
import org.json.JSONObject;

import java.util.Map;

public final class CreatePDFFromDynamicHTML extends AbstractPDFOperation {
    public CreatePDFFromDynamicHTML(PDFClient client) {
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
        boolean includeHeaderFooter = Boolean.parseBoolean(options.getOrDefault("includeHeaderFooter", "true"));
        double width = Double.parseDouble(options.getOrDefault("pageWidth", "8"));
        double height = Double.parseDouble(options.getOrDefault("pageHeight", "11.5"));
        String dataToMerge = options.getOrDefault("dataToMerge", "{}");

        PageLayout pageLayout = new PageLayout();
        pageLayout.setPageSize(width, height);

        JSONObject dataToMergeJSON = new JSONObject(dataToMerge);

        return CreatePDFOptions.htmlOptionsBuilder()
                .includeHeaderFooter(includeHeaderFooter)
                .withPageLayout(pageLayout)
                .withDataToMerge(dataToMergeJSON)
                .build();
    }
}

