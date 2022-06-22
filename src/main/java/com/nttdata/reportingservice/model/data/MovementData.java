package com.nttdata.reportingservice.model.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Class MovementData.
 */
@Getter
@Setter
@NoArgsConstructor
@Table(name = "movement")
public class MovementData {

  @Id
  private Integer id;
  private String idDto;
  private String concept;
  private LocalDate date;
  private String type;
  private Double amount;
  private String productIdDto;
  private Integer productId;
  private String customerIdDto;
  private Integer customerId;
}
