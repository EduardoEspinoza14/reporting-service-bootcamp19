package com.nttdata.reportingservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.nttdata.reportingservice.model.data.MovementData;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Class Movement.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
  /**
   * Method transformIntoDto.
   */
  public static Movement transformIntoDto(MovementData movementData) {
    Movement movement = new Movement();
    BeanUtils.copyProperties(movementData, movement, "id", "_id");
    return movement;
  }

  public static MovementData transformIntoData(Movement movement) {
    return transformIntoData(movement, null);
  }

  /**
   * Class transformIntoData.
   */
  public static MovementData transformIntoData(Movement movement, Integer id) {
    MovementData movementData = new MovementData();
    BeanUtils.copyProperties(movement, movementData, "id");
    movementData.setIdDto(movement.getId());
    movementData.setProductIdDto(movement.getProductId());
    movementData.setCustomerIdDto(movement.getCustomerId());
    movementData.setId(id);
    return movementData;
  }

  private String id;
  private String concept;
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate date;
  private Double amount;
  private String type;
  private String productId;
  private String customerId;

}
