package de.jjarndt.camunda.connector.adobe.service;

import de.jjarndt.camunda.connector.adobe.model.OperationType;
import de.jjarndt.camunda.connector.adobe.service.operations.Operation;
import de.jjarndt.camunda.connector.adobe.service.operations.createpdf.CreatePDFFromDOCX;
import de.jjarndt.camunda.connector.adobe.service.operations.createpdf.CreatePDFFromDynamicHTML;
import de.jjarndt.camunda.connector.adobe.service.operations.createpdf.CreatePDFFromPPTX;
import de.jjarndt.camunda.connector.adobe.service.operations.createpdf.CreatePDFFromStaticHTML;
import de.jjarndt.camunda.connector.adobe.service.operations.exportpdf.ExportPDFToDOCX;
import de.jjarndt.camunda.connector.adobe.service.operations.extractfrompdf.ExtractInfoFromPDF;
import de.jjarndt.camunda.connector.adobe.service.operations.ocr.CreateOCR;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AdobePDFServiceTest {

    @Mock
    private PDFClient mockClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOCROperation() {
        Operation operation = AdobePDFService.createOperation(OperationType.OCR, mockClient);
        assertTrue(operation instanceof CreateOCR);
    }

    @Test
    void testCreateExportPDFToDOCXOperation() {
        Operation operation = AdobePDFService.createOperation(OperationType.EXPORT_FROM_DOCX, mockClient);
        assertTrue(operation instanceof ExportPDFToDOCX);
    }

    @Test
    void testCreatePDFFromStaticHTMLOperation() {
        Operation operation = AdobePDFService.createOperation(OperationType.CREATE_PDF_FROM_STATIC_HTML, mockClient);
        assertTrue(operation instanceof CreatePDFFromStaticHTML);
    }

    @Test
    void testCreatePDFFromDOCXOperation() {
        Operation operation = AdobePDFService.createOperation(OperationType.CREATE_PDF_FROM_DOCX, mockClient);
        assertTrue(operation instanceof CreatePDFFromDOCX);
    }

    @Test
    void testCreatePDFFromDynamicHTMLOperation() {
        Operation operation = AdobePDFService.createOperation(OperationType.CREATE_PDF_FROM_DYNAMIC_HTML, mockClient);
        assertTrue(operation instanceof CreatePDFFromDynamicHTML);
    }

    @Test
    void testCreatePDFFromPPTXOperation() {
        Operation operation = AdobePDFService.createOperation(OperationType.CREATE_PDF_FROM_PPTX, mockClient);
        assertTrue(operation instanceof CreatePDFFromPPTX);
    }

    @Test
    void testExtractTextInfoFromPDFOperation() {
        Operation operation = AdobePDFService.createOperation(OperationType.EXTRACT_INFO_FROM_PDF, mockClient);
        assertTrue(operation instanceof ExtractInfoFromPDF);
    }


}
