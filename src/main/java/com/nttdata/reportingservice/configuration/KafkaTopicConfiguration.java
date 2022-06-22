package com.nttdata.reportingservice.configuration;

import com.nttdata.reportingservice.configuration.kafka.KafkaConsumerConfiguration;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * Class KafkaTopicConfiguration.
 */
@Configuration
public class KafkaTopicConfiguration {

  @Value(value = "${kafka.bootstrapAddress}")
  private String bootstrapAddress;

  /**
   * Method KafkaAdmin.
   */
  @Bean
  public KafkaAdmin kafkaAdmin() {
    Map<String, Object> configs = new HashMap<>();
    configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    return new KafkaAdmin(configs);
  }

  @Bean
  public NewTopic topicInsertProduct() {
    return new NewTopic(KafkaConsumerConfiguration.PRODUCT_TOPIC_INSERT, 1, (short) 1);
  }

  @Bean
  public NewTopic topicUpdateProduct() {
    return new NewTopic(KafkaConsumerConfiguration.PRODUCT_TOPIC_UPDATE, 1, (short) 1);
  }

  @Bean
  public NewTopic topicDeleteProduct() {
    return new NewTopic(KafkaConsumerConfiguration.PRODUCT_TOPIC_DELETE, 1, (short) 1);
  }

  @Bean
  public NewTopic topicInsertCustomer() {
    return new NewTopic(KafkaConsumerConfiguration.CUSTOMER_TOPIC_INSERT, 1, (short) 1);
  }

  @Bean
  public NewTopic topicUpdateCustomer() {
    return new NewTopic(KafkaConsumerConfiguration.CUSTOMER_TOPIC_UPDATE, 1, (short) 1);
  }

  @Bean
  public NewTopic topicDeleteCustomer() {
    return new NewTopic(KafkaConsumerConfiguration.CUSTOMER_TOPIC_DELETE, 1, (short) 1);
  }

  @Bean
  public NewTopic topicInsertMovement() {
    return new NewTopic(KafkaConsumerConfiguration.MOVEMENT_TOPIC_INSERT, 1, (short) 1);
  }

  @Bean
  public NewTopic topicUpdateMovement() {
    return new NewTopic(KafkaConsumerConfiguration.MOVEMENT_TOPIC_UPDATE, 1, (short) 1);
  }

  @Bean
  public NewTopic topicDeleteMovement() {
    return new NewTopic(KafkaConsumerConfiguration.MOVEMENT_TOPIC_DELETE, 1, (short) 1);
  }

}