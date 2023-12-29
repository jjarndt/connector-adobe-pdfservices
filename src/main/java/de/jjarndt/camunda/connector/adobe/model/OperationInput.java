package de.jjarndt.camunda.connector.adobe.model;

import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.io.FileRef;

import java.util.Map;

public record OperationInput(ExecutionContext executionContext, FileRef source, Map<String, String> options) {
}
