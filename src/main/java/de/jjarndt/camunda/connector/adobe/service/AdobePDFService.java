package de.jjarndt.camunda.connector.adobe.service;

import de.jjarndt.camunda.connector.adobe.model.OperationType;
import de.jjarndt.camunda.connector.adobe.service.operations.CreateOCR;
import de.jjarndt.camunda.connector.adobe.service.operations.CreatePDFFromStaticHTML;
import de.jjarndt.camunda.connector.adobe.service.operations.ExportPDFToDOCX;
import de.jjarndt.camunda.connector.adobe.service.operations.Operation;

public class AdobePDFService {
    public static Operation createOperation(OperationType operationType, PDFClient client) {
        return switch (operationType) {
            case OCR -> new CreateOCR(client);
            case EXPORT_FROM_DOCX -> new ExportPDFToDOCX(client);
            case CREATE_PDF_FROM_STATIC_HTML -> new CreatePDFFromStaticHTML(client);
            case EXPORT_PDF_TO_JPEG -> null;
        };
    }
}
