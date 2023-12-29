package de.jjarndt.camunda.connector.adobe.service.operations.ocr;

import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.OCROperation;
import com.adobe.pdfservices.operation.pdfops.options.ocr.OCROptions;
import de.jjarndt.camunda.connector.adobe.model.OperationInput;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * This class is a test class for the CreateOCR class. It tests the performOperation() method
 * and various scenarios with different options and exceptions.
 */
class CreateOCRTest {

    @Mock
    private PDFClient mockClient;
    @Mock
    private OperationInput mockInput;
    @Mock
    private FileRef mockSource;
    @Mock
    private FileRef mockResult;
    @Mock
    private OCROperation mockOCROperation;

    private MockedStatic<OCROperation> mockedOCROperation;
    private CreateOCR createOCR;

    @BeforeEach
    void setUp() throws ServiceApiException, IOException {
        MockitoAnnotations.openMocks(this);
        createOCR = new CreateOCR(mockClient);

        when(mockInput.source()).thenReturn(mockSource);
        when(mockInput.options()).thenReturn(getDefaultOptions());

        mockedOCROperation = Mockito.mockStatic(OCROperation.class);
        mockedOCROperation.when(OCROperation::createNew).thenReturn(mockOCROperation);

        when(mockOCROperation.execute(any())).thenReturn(mockResult);
    }

    @AfterEach
    void tearDown() {
        mockedOCROperation.close();
    }

    @Test
    void performOperationTest() throws Exception {
        FileRef result = createOCR.performOperation(mockInput);

        verify(mockInput).source();
        verify(mockInput).options();
        verify(mockOCROperation).setInput(mockSource);
        verify(mockOCROperation).setOptions(any(OCROptions.class));
        verify(mockOCROperation).execute(any());

        Assertions.assertEquals(mockResult, result, "Result is not equal");
    }

    @Test
    void performOperationWithDifferentOptions() throws Exception {
        // Setzen Sie verschiedene Optionen
        Map<String, String> options = Map.of(
                "locale", "DE_DE",
                "type", "SEARCHABLE_IMAGE"
        );
        when(mockInput.options()).thenReturn(options);

        FileRef result = createOCR.performOperation(mockInput);

        // Verifizieren Sie das Verhalten mit den neuen Optionen
        verify(mockOCROperation).setOptions(any(OCROptions.class));
        Assertions.assertEquals(mockResult, result);
    }

    @Test
    void performOperationWithMissingOptions() throws Exception {
        // Setzen Sie leere Optionen
        when(mockInput.options()).thenReturn(Map.of());

        FileRef result = createOCR.performOperation(mockInput);

        // Verifizieren Sie das Verhalten mit Standardoptionen
        verify(mockOCROperation).setOptions(any(OCROptions.class));
        Assertions.assertEquals(mockResult, result);
    }

//    @Test
//    void performOperationHandlesExceptions() {
//        // Simulieren Sie eine Ausnahme bei der OCR-Operation
//        when(mockOCROperation.execute(any())).thenThrow(new ServiceApiException("Fehler"));
//
//        // Überprüfen Sie, ob die Ausnahme wie erwartet weitergegeben wird
//        assertThrows(ServiceApiException.class, () -> createOCR.performOperation(mockInput));
//    }

    private Map<String, String> getDefaultOptions() {
        Map<String, String> options = new HashMap<>();
        options.put("locale", "EN_US");
        options.put("type", "SEARCHABLE_IMAGE_EXACT");
        return options;
    }
}
