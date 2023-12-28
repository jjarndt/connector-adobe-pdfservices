package de.jjarndt.camunda.connector.adobe.service.operations.createpdf;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.CreatePDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.createpdf.CreatePDFOptions;
import com.adobe.pdfservices.operation.pdfops.options.createpdf.PageLayout;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;
import org.json.JSONObject;

public final class CreatePDFFromDynamicHTML extends AbstractPDFOperation {
    public CreatePDFFromDynamicHTML(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(ExecutionContext executionContext, FileRef source) throws Exception {
        CreatePDFOperation htmlToPDFOperation = CreatePDFOperation.createNew();
        htmlToPDFOperation.setInput(source);

        setCustomOptions(htmlToPDFOperation);

        return htmlToPDFOperation.execute(executionContext);
    }

    private void setCustomOptions(CreatePDFOperation htmlToPDFOperation) {
        PageLayout pageLayout = new PageLayout();
        pageLayout.setPageSize(8, 11.5);

        JSONObject dataToMerge = new JSONObject();

        CreatePDFOptions options = CreatePDFOptions.htmlOptionsBuilder()
                .includeHeaderFooter(true)
                .withPageLayout(pageLayout)
                .withDataToMerge(dataToMerge)
                .build();
        htmlToPDFOperation.setOptions(options);
    }
}

