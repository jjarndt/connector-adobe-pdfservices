package de.jjarndt.camunda.connector.adobe.service;

import de.jjarndt.camunda.connector.adobe.model.OperationType;
import de.jjarndt.camunda.connector.adobe.service.operations.CreateOCR;
import de.jjarndt.camunda.connector.adobe.service.operations.Operation;

public class OperationFactory {
    public static Operation createOperation(OperationType type, PDFClient client) {
        return switch (type) {
            case OCR -> new CreateOCR(client);
            case EXPORT_FROM_DOCX -> null;
            case EXPORT_FROM_STATIC_HTML -> null;
            case EXPORT_PDF_TO_JPEG -> null;
        };
    }
}
