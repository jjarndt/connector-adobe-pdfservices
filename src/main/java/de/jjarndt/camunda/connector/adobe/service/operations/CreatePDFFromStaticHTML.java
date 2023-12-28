package de.jjarndt.camunda.connector.adobe.service.operations;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.CreatePDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.createpdf.CreatePDFOptions;
import com.adobe.pdfservices.operation.pdfops.options.createpdf.PageLayout;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;

public final class CreatePDFFromStaticHTML extends AbstractPDFOperation {
    public CreatePDFFromStaticHTML(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(ExecutionContext executionContext, FileRef source) throws Exception {
        CreatePDFOperation htmlToPDFOperation = CreatePDFOperation.createNew();
        htmlToPDFOperation.setInput(source);
        setCustomOptions(htmlToPDFOperation); // Implement this method as needed
        return htmlToPDFOperation.execute(executionContext);
    }

    private static void setCustomOptions(CreatePDFOperation htmlToPDFOperation) {
        // Define the page layout, in this case an 8 x 11.5 inch page (effectively portrait orientation).
        PageLayout pageLayout = new PageLayout();
        pageLayout.setPageSize(8, 11.5);

        // Set the desired HTML-to-PDF conversion options.
        CreatePDFOptions htmlToPdfOptions = CreatePDFOptions.htmlOptionsBuilder()
                .includeHeaderFooter(true)
                .withPageLayout(pageLayout)
                .build();
        htmlToPDFOperation.setOptions(htmlToPdfOptions);
    }
}

