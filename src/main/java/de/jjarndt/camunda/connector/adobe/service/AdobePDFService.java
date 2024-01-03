package de.jjarndt.camunda.connector.adobe.service;

import de.jjarndt.camunda.connector.adobe.model.OperationType;
import de.jjarndt.camunda.connector.adobe.service.operations.createpdf.CreatePDFFromDOCX;
import de.jjarndt.camunda.connector.adobe.service.operations.createpdf.CreatePDFFromDynamicHTML;
import de.jjarndt.camunda.connector.adobe.service.operations.createpdf.CreatePDFFromPPTX;
import de.jjarndt.camunda.connector.adobe.service.operations.extractfrompdf.ExtractInfoFromPDF;
import de.jjarndt.camunda.connector.adobe.service.operations.ocr.CreateOCR;
import de.jjarndt.camunda.connector.adobe.service.operations.createpdf.CreatePDFFromStaticHTML;
import de.jjarndt.camunda.connector.adobe.service.operations.exportpdf.ExportPDFToDOCX;
import de.jjarndt.camunda.connector.adobe.service.operations.Operation;

public class AdobePDFService {
    public static Operation createOperation(OperationType operationType, PDFClient client) {
        return switch (operationType) {
            case OCR -> new CreateOCR(client);
            case EXPORT_FROM_DOCX -> new ExportPDFToDOCX(client);
            case CREATE_PDF_FROM_STATIC_HTML -> new CreatePDFFromStaticHTML(client);
            case CREATE_PDF_FROM_DOCX -> new CreatePDFFromDOCX(client);
            case CREATE_PDF_FROM_DYNAMIC_HTML -> new CreatePDFFromDynamicHTML(client);
            case CREATE_PDF_FROM_PPTX -> new CreatePDFFromPPTX(client);
            case EXTRACT_INFO_FROM_PDF -> new ExtractInfoFromPDF(client);
        };
    }
}
