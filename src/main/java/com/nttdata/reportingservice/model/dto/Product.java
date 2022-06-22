package com.nttdata.reportingservice.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.nttdata.reportingservice.model.data.ProductData;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;


/**
 * Class Product.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  /**
   * Method transformIntoDto.
   */
  public static Product transformIntoDto(ProductData productData) {
    Product product = new Product();
    BeanUtils.copyProperties(productData, product, "id", "_id");
    return product;
  }

  public static ProductData transformIntoData(Product product) {
    return transformIntoData(product, null);
  }

  /**
   * Method transformIntoData.
   */
  public static ProductData transformIntoData(Product product, Integer id) {
    ProductData productData = new ProductData();
    BeanUtils.copyProperties(product, productData, "id");
    productData.setIdDto(product.getId());
    productData.setCustomerIdDto(product.getCustomerId());
    productData.setId(id);
    return productData;
  }

  private String id;
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate startDate;
  private String number;
  private String type;
  private Double creditLimit;
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate expirationDate;
  private String securityCode;
  private Double commissionAmount;
  private Integer singleDayMovement;
  private Double creditAmount;
  private Integer paymentDay;
  private Integer maxMovementLimit;
  private String customerId;

}
