package com.nttdata.reportingservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.nttdata.reportingservice.model.data.CustomerData;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;

/**
 * Class Customer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  /**
   * Method transformIntoDto.
   */
  public static Customer transformIntoDto(CustomerData customerData) {
    Customer customer = new Customer();
    BeanUtils.copyProperties(customerData, customer, "id", "_id");
    return customer;
  }

  public static CustomerData transformIntoData(Customer customer) {
    return transformIntoData(customer, null);
  }

  /**
   * Method transformIntoData.
   */
  public static CustomerData transformIntoData(Customer customer, Integer id) {
    CustomerData customerData = new CustomerData();
    BeanUtils.copyProperties(customer, customerData, "id");
    customerData.setIdDto(customer.getId());
    customerData.setId(id);
    return customerData;
  }

  @Id
  private String id;
  private String name;
  private String lastName;
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate dateBorn;
  private String address;
  private String phone;
  private String type;
  private String ruc;
  private String dni;

}
