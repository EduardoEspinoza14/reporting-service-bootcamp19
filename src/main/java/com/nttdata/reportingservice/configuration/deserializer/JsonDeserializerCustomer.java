package com.nttdata.reportingservice.configuration.deserializer;

import com.nttdata.reportingservice.model.dto.Customer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 * Class JsonDeserializerCustomer.
 */
public class JsonDeserializerCustomer extends JsonDeserializer<Customer> {

  /**
   * Constructor.
   */
  public JsonDeserializerCustomer() {
    super();
    this.setRemoveTypeHeaders(false);
    this.addTrustedPackages("*");
    this.setUseTypeMapperForKey(true);
  }

}