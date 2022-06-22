package com.nttdata.reportingservice.configuration.deserializer;

import com.nttdata.reportingservice.model.dto.Movement;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 * Class JsonDeserializerMovement.
 */
public class JsonDeserializerMovement extends JsonDeserializer<Movement> {

  /**
   * Constructor.
   */
  public JsonDeserializerMovement() {
    super();
    this.setRemoveTypeHeaders(false);
    this.addTrustedPackages("*");
    this.setUseTypeMapperForKey(true);
  }

}