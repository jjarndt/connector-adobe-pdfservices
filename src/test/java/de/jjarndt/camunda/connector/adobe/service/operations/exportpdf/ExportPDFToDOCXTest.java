package de.jjarndt.camunda.connector.adobe.service.operations.exportpdf;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.ExportPDFOperation;
import com.adobe.pdfservices.operation.pdfops.options.exportpdf.ExportPDFOptions;
import com.adobe.pdfservices.operation.pdfops.options.exportpdf.ExportPDFTargetFormat;
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

class ExportPDFToDOCXTest {

    @Mock
    private PDFClient mockPDFClient;

    @Mock
    private ExecutionContext mockExecutionContext;

    @Mock
    private FileRef mockFileRef;

    @Mock
    private ExportPDFOperation mockExportPdfOperation;

    private ExportPDFToDOCX exportPdfToDocx;

    private MockedStatic<ExportPDFOperation> mockedExportPdfOperation;

    @BeforeEach
    void setUp() throws ServiceApiException, IOException {
        MockitoAnnotations.openMocks(this);
        exportPdfToDocx = new ExportPDFToDOCX(mockPDFClient);

        mockedExportPdfOperation = Mockito.mockStatic(ExportPDFOperation.class);
        mockedExportPdfOperation.when(() -> ExportPDFOperation.createNew(ExportPDFTargetFormat.DOCX)).thenReturn(mockExportPdfOperation);

        when(mockPDFClient.createExecutionContext()).thenReturn(mockExecutionContext);
        when(mockExportPdfOperation.execute(any(ExecutionContext.class))).thenReturn(mockFileRef);
    }

    @AfterEach
    void tearDown() {
        mockedExportPdfOperation.close();
    }

    @Test
    void testPerformOperation() throws Exception {
        OperationInput input = mock(OperationInput.class);
        Map<String, String> options = new HashMap<>();
        options.put("locale", "EN_US");

        when(input.options()).thenReturn(options);
        when(input.source()).thenReturn(mockFileRef);
        when(input.executionContext()).thenReturn(mockExecutionContext);

        FileRef result = exportPdfToDocx.performOperation(input);

        verify(input, times(1)).options();
        verify(input, times(1)).source();
        verify(mockExportPdfOperation, times(1)).setInput(mockFileRef);
        verify(mockExportPdfOperation, times(1)).setOptions(any(ExportPDFOptions.class));
        verify(mockExportPdfOperation, times(1)).execute(mockExecutionContext);
        assert result == mockFileRef;
    }
}
