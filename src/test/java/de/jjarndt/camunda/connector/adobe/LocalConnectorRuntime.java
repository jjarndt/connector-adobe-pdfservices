package de.jjarndt.camunda.connector.adobe;

import de.jjarndt.camunda.connector.adobe.service.AdobePDFService;
import de.jjarndt.camunda.connector.adobe.service.operations.CreateOCR;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LocalConnectorRuntime {

  public static void main(String[] args) {
    SpringApplication.run(LocalConnectorRuntime.class, args);
  }

  @Bean
  public AdobePDFService adobePDFService() {
    return new AdobePDFService();
  }

  @Bean
  public AdobePdfServicesFunction adobePdfServicesFunction(AdobePDFService adobePDFService) {
    return new AdobePdfServicesFunction(adobePDFService);
  }
}
