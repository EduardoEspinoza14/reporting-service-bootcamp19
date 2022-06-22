package com.nttdata.reportingservice.configuration.deserializer;

import com.nttdata.reportingservice.model.dto.Product;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 * Class JsonDeserializerProduct.
 */
public class JsonDeserializerProduct extends JsonDeserializer<Product> {

  /**
   * Constructor.
   */
  public JsonDeserializerProduct() {
    super();
    this.setRemoveTypeHeaders(false);
    this.addTrustedPackages("*");
    this.setUseTypeMapperForKey(true);
  }

}