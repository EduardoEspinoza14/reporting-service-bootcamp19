package com.nttdata.reportingservice.configuration.kafka.impl;

import com.nttdata.reportingservice.configuration.deserializer.JsonDeserializerProduct;
import com.nttdata.reportingservice.configuration.kafka.KafkaConsumerConfiguration;
import com.nttdata.reportingservice.model.dto.Product;
import com.nttdata.reportingservice.repository.ProductRepository;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.ReceiverOptions;

/**
 * Class ProductKafkaConsumerConfiguration.
 */
@Configuration
public class ProductKafkaConsumerConfiguration extends KafkaConsumerConfiguration<String, Product> {

  @Autowired
  private ProductRepository repository;

  @Override
  @Bean(name = "receiverProductOptions")
  public ReceiverOptions receiverOptions() {
    Map<String, Object> props = new HashMap<>();
    props.put(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
            bootstrapAddress);
    props.put(
            ConsumerConfig.GROUP_ID_CONFIG,
            groupId);
    props.put(
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
            StringDeserializer.class);
    props.put(
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
            JsonDeserializerProduct.class);
    return ReceiverOptions.create(props);
  }

  @Override
  @Bean(name = "insertProductData")
  public void insertData() {
    createReceiver(PRODUCT_TOPIC_INSERT)
            .flatMap(r -> repository.save(Product.transformIntoData(r.value())))
            .subscribe();
  }

  @Override
  @Bean(name = "updateProductData")
  public void updateData() {
    createReceiver(PRODUCT_TOPIC_UPDATE)
            .flatMap(r -> repository.findByIdDto(r.key())
                    .flatMap(p -> repository
                            .save(Product.transformIntoData(r.value(), p.getId()))))
            .subscribe();
  }

  @Override
  @Bean(name = "deleteProductData")
  public void deleteData() {
    createReceiver(PRODUCT_TOPIC_DELETE)
            .flatMap(r -> {
              return repository.findByIdDto(r.key())
                      .flatMap(p -> repository.deleteById(p.getId()));
            })
            .subscribe();
  }

}
