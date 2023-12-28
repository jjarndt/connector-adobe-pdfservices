package de.jjarndt.camunda.connector.adobe.service.operations;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.OCROperation;
import de.jjarndt.camunda.connector.adobe.model.ConnectorRequest;
import de.jjarndt.camunda.connector.adobe.model.ConnectorResponse;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class CreateOCR implements Operation {

    private final PDFClient client;

    public CreateOCR(PDFClient client) {
        this.client = client;
    }

    @Override
    public ConnectorResponse execute(ConnectorRequest request) {
        try {
            // Erstellen eines ExecutionContext mit den bereitgestellten Anmeldeinformationen
            ExecutionContext executionContext = ExecutionContext.create(client.getCredentials());

            // Erstellen und Konfigurieren der OCROperation
            OCROperation ocrOperation = OCROperation.createNew();
            FileRef source = FileRef.createFromLocalFile(request.requestDetails().sourcePath());
            ocrOperation.setInput(source);

            // Ausführen der Operation und Speichern des Ergebnisses
            FileRef result = ocrOperation.execute(executionContext);
            String outputFilePath = request.requestDetails().destinationPath();
            result.saveAs(outputFilePath);

            // Erfolg zurückgeben
            return new ConnectorResponse(true, "OCR erfolgreich ausgeführt", outputFilePath);
        } catch (Exception e) {
            // Fehlerbehandlung
            return new ConnectorResponse(false, "OCR-Fehler: " + e.getMessage(), null);
        }
    }

    public static String createOutputFilePath(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String timeStamp = dateTimeFormatter.format(now);
        return("output/OcrPDF/ocr" + timeStamp + ".pdf");
    }
}
