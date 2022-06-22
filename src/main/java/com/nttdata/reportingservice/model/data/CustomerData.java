package com.nttdata.reportingservice.model.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Class CustomerData.
 */
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
public class CustomerData {

  @Id
  private Integer id;
  private String idDto;
  private String name;
  private String lastName;
  private LocalDate dateBorn;
  private String address;
  private String phone;
  private String type;
  private String ruc;
  private String dni;

}
