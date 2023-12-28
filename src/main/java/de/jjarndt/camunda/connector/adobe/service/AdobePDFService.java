package de.jjarndt.camunda.connector.adobe.service;

import de.jjarndt.camunda.connector.adobe.model.OperationType;
import de.jjarndt.camunda.connector.adobe.service.operations.CreateOCR;
import de.jjarndt.camunda.connector.adobe.service.operations.Operation;

public class AdobePDFService {
    public static Operation createOperation(OperationType operationType, PDFClient client) {
        return switch (operationType) {
            case OCR -> new CreateOCR(client);
            case EXPORT_FROM_DOCX -> null;
            case EXPORT_FROM_STATIC_HTML -> null;
            case EXPORT_PDF_TO_JPEG -> null;
        };
    }
}
