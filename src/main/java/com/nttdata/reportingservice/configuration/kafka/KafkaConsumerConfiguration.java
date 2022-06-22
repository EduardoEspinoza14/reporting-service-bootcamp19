package com.nttdata.reportingservice.configuration.kafka;

import java.util.Collections;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

/**
 * Class KafkaConsumerConfiguration.
 */
public abstract class KafkaConsumerConfiguration<K, V> {

  @Value(value = "${kafka.bootstrapAddress:}")
  public String bootstrapAddress;

  @Value(value = "${kafka.group-id:}")
  public String groupId;

  public static final String PRODUCT_TOPIC_INSERT = "product.insert";
  public static final String PRODUCT_TOPIC_UPDATE = "product.update";
  public static final String PRODUCT_TOPIC_DELETE = "product.delete";
  public static final String CUSTOMER_TOPIC_INSERT = "customer.insert";
  public static final String CUSTOMER_TOPIC_UPDATE = "customer.update";
  public static final String CUSTOMER_TOPIC_DELETE = "customer.delete";
  public static final String MOVEMENT_TOPIC_INSERT = "movement.insert";
  public static final String MOVEMENT_TOPIC_UPDATE = "movement.update";
  public static final String MOVEMENT_TOPIC_DELETE = "movement.delete";

  public abstract ReceiverOptions<K, V> receiverOptions();

  public ReceiverOptions<K, V> subscribeOptions(String topic) {
    return receiverOptions().subscription(Collections.singleton(topic));
  }

  public abstract void insertData();

  public abstract void updateData();

  public abstract void deleteData();

  /**
   * Method createReceiver.
   */
  public Flux<ConsumerRecord<K, V>> createReceiver(String topic) {
    return KafkaReceiver.create(subscribeOptions(topic))
            .receiveAutoAck()
            .concatMap(r -> r);
  }

}
