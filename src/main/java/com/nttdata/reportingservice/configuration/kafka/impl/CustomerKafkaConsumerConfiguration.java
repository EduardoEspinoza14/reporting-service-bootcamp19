package com.nttdata.reportingservice.configuration.kafka.impl;

import com.nttdata.reportingservice.configuration.deserializer.JsonDeserializerCustomer;
import com.nttdata.reportingservice.configuration.kafka.KafkaConsumerConfiguration;
import com.nttdata.reportingservice.model.dto.Customer;
import com.nttdata.reportingservice.repository.CustomerRepository;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.ReceiverOptions;

/**
 * Class CustomerKafkaConsumerConfiguration.
 */
@Configuration
public class CustomerKafkaConsumerConfiguration
        extends KafkaConsumerConfiguration<String, Customer> {

  @Autowired
  private CustomerRepository repository;

  @Override
  @Bean(name = "receiverCustomerOptions")
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
            JsonDeserializerCustomer.class);
    return ReceiverOptions.create(props);
  }

  @Override
  @Bean(name = "insertCustomerData")
  public void insertData() {
    createReceiver(CUSTOMER_TOPIC_INSERT)
            .flatMap(r -> repository.save(Customer.transformIntoData(r.value())))
            .subscribe();
  }

  @Override
  @Bean(name = "updateCustomerData")
  public void updateData() {
    createReceiver(CUSTOMER_TOPIC_UPDATE)
            .flatMap(r -> repository.findByIdDto(r.key())
                    .flatMap(p -> repository
                            .save(Customer.transformIntoData(r.value(), p.getId()))))
            .subscribe();
  }

  @Override
  @Bean(name = "deleteCustomerData")
  public void deleteData() {
    createReceiver(CUSTOMER_TOPIC_DELETE)
            .flatMap(r -> {
              return repository.findByIdDto(r.key())
                      .flatMap(p -> repository.deleteById(p.getId()));
            })
            .subscribe();
  }

}
