package com.nttdata.reportingservice.model.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Class ProductData.
 */
@Getter
@Setter
@NoArgsConstructor
@Table(name = "product")
public class ProductData {

  @Id
  private Integer id;
  private String idDto;
  private LocalDate startDate;
  private String number;
  private String type;
  private Double creditLimit;
  private LocalDate expirationDate;
  private String securityCode;
  private Double commissionAmount;
  private Integer singleDayMovement;
  private Double creditAmount;
  private Integer paymentDay;
  private Integer maxMovementLimit;
  private String customerIdDto;
  private Integer customerId;

}
