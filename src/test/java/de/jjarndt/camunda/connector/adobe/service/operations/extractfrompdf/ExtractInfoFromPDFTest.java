package de.jjarndt.camunda.connector.adobe.service.operations.extractfrompdf;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExtractPDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.extractpdf.ExtractPDFOptions;
import de.jjarndt.camunda.connector.adobe.model.OperationInput;
import de.jjarndt.camunda.connector.adobe.service.PDFClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ExtractInfoFromPDFTest {

    @Mock
    private PDFClient mockPDFClient;

    @Mock
    private ExecutionContext mockExecutionContext;

    @Mock
    private FileRef mockFileRef;

    @Mock
    private ExtractPDFOperation mockExtractPdfOperation;

    private ExtractInfoFromPDF extractInfoFromPdf;

    private MockedStatic<ExtractPDFOperation> mockedExtractPdfOperation;

    @BeforeEach
    void setUp() throws ServiceApiException, IOException {
        MockitoAnnotations.openMocks(this);
        extractInfoFromPdf = new ExtractInfoFromPDF(mockPDFClient);

        mockedExtractPdfOperation = Mockito.mockStatic(ExtractPDFOperation.class);
        mockedExtractPdfOperation.when(ExtractPDFOperation::createNew).thenReturn(mockExtractPdfOperation);

        when(mockPDFClient.createExecutionContext()).thenReturn(mockExecutionContext);
        when(mockExtractPdfOperation.execute(any(ExecutionContext.class))).thenReturn(mockFileRef);
    }

    @AfterEach
    void tearDown() {
        mockedExtractPdfOperation.close();
    }

    @Test
    void testPerformOperation() throws Exception {
        OperationInput input = mock(OperationInput.class);
        Map<String, String> options = new HashMap<>();
        options.put("addCharInfo", "false");
        options.put("elementsToExtract", "TEXT");

        when(input.options()).thenReturn(options);
        when(input.source()).thenReturn(mockFileRef);
        when(input.executionContext()).thenReturn(mockExecutionContext);

        FileRef result = extractInfoFromPdf.performOperation(input);

        verify(input, times(1)).options();
        verify(input, times(1)).source();
        verify(mockExtractPdfOperation, times(1)).setInputFile(mockFileRef);
        verify(mockExtractPdfOperation, times(1)).setOptions(any(ExtractPDFOptions.class));
        verify(mockExtractPdfOperation, times(1)).execute(mockExecutionContext);
        assert result == mockFileRef;
    }
}
