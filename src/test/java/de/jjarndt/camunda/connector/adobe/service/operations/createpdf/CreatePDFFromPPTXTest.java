package de.jjarndt.camunda.connector.adobe.service.operations.createpdf;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.CreatePDFOperation;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreatePDFFromPPTXTest {

    @Mock
    private PDFClient mockPDFClient;

    @Mock
    private ExecutionContext mockExecutionContext;

    @Mock
    private FileRef mockFileRef;

    @Mock
    private CreatePDFOperation mockCreatePdfOperation;

    private CreatePDFFromPPTX createPdfFromPPTX;

    private MockedStatic<CreatePDFOperation> mockedCreatePdfOperation;

    @BeforeEach
    void setUp() throws ServiceApiException, IOException {
        MockitoAnnotations.openMocks(this);
        createPdfFromPPTX = new CreatePDFFromPPTX(mockPDFClient);

        mockedCreatePdfOperation = Mockito.mockStatic(CreatePDFOperation.class);
        mockedCreatePdfOperation.when(CreatePDFOperation::createNew).thenReturn(mockCreatePdfOperation);

        when(mockPDFClient.createExecutionContext()).thenReturn(mockExecutionContext);
        when(mockCreatePdfOperation.execute(any(ExecutionContext.class))).thenReturn(mockFileRef);
    }

    @AfterEach
    void tearDown() {
        mockedCreatePdfOperation.close();
    }

    @Test
    void testPerformOperation() throws Exception {
        OperationInput input = mock(OperationInput.class);

        when(input.source()).thenReturn(mockFileRef);
        when(input.executionContext()).thenReturn(mockExecutionContext);

        FileRef result = createPdfFromPPTX.performOperation(input);

        verify(input, times(1)).source();
        verify(mockCreatePdfOperation, times(1)).setInput(mockFileRef);
        verify(mockCreatePdfOperation, times(1)).execute(mockExecutionContext);
        assert result == mockFileRef;
    }
}
