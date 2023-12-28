package de.jjarndt.camunda.connector.adobe.model;

import io.camunda.connector.generator.java.annotation.TemplateProperty;

public record RequestDetails(
        @TemplateProperty(group = "source", label = "Source Type", description = "Type of the source of the PDF")
        SourceType sourceType,

        @TemplateProperty(group = "source", label = "Source Path", description = "Path of the source PDF")
        String sourcePath,

        @TemplateProperty(group = "destination", label = "Destination Type", description = "Type of the destination for the PDF")
        DestinationType destinationType,

        @TemplateProperty(group = "destination", label = "Destination Path", description = "Path for the output PDF")
        String destinationPath,

        @TemplateProperty(group = "operation", label = "Operation Type", description = "Type of operation to perform on the PDF")
        OperationType operationType
) {
}
