package de.jjarndt.camunda.connector.adobe.service.operations.extractfrompdf;

import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExtractPDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractElementType;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractPDFOptions;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import de.jjarndt.camunda.connector.adobe.service.operations.AbstractPDFOperation;
import de.jjarndt.camunda.connector.adobe.model.OperationInput;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ExtractInfoFromPDF extends AbstractPDFOperation {
    public ExtractInfoFromPDF(PDFClient client) {
        super(client);
    }

    @Override
    protected FileRef performOperation(OperationInput input) throws Exception {
        Map<String, String> options = input.options();
        ExtractPDFOptions extractPDFOptions = buildExtractPDFOptions(options);

        ExtractPDFOperation extractPDFOperation = ExtractPDFOperation.createNew();
        extractPDFOperation.setInputFile(input.source());
        extractPDFOperation.setOptions(extractPDFOptions);

        return extractPDFOperation.execute(input.executionContext());
    }

    private ExtractPDFOptions buildExtractPDFOptions(Map<String, String> options) {
        boolean addCharInfo = Boolean.parseBoolean(options.getOrDefault("add_char_info", "false"));
        String elements = options.getOrDefault("elements_to_extract", "TEXT");
        List<ExtractElementType> elementTypes = Arrays.stream(elements.split(","))
                .map(String::trim)
                .map(ExtractElementType::valueOf)
                .collect(Collectors.toList());

        return ExtractPDFOptions.extractPdfOptionsBuilder()
                .addElementsToExtract(elementTypes)
                .addCharInfo(addCharInfo)
                .build();
    }
}
