package com.nttdata.reportingservice.configuration.kafka.impl;

import com.nttdata.reportingservice.configuration.deserializer.JsonDeserializerMovement;
import com.nttdata.reportingservice.configuration.kafka.KafkaConsumerConfiguration;
import com.nttdata.reportingservice.model.dto.Movement;
import com.nttdata.reportingservice.repository.MovementRepository;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.ReceiverOptions;

/**
 * Class MovementKafkaConsumerConfiguration.
 */
@Configuration
public class MovementKafkaConsumerConfiguration
        extends KafkaConsumerConfiguration<String, Movement> {

  @Autowired
  private MovementRepository repository;

  @Override
  @Bean(name = "receiverMovementOptions")
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
            JsonDeserializerMovement.class);
    return ReceiverOptions.create(props);
  }

  @Override
  @Bean(name = "insertMovementData")
  public void insertData() {
    createReceiver(MOVEMENT_TOPIC_INSERT)
            .flatMap(r -> repository.save(Movement.transformIntoData(r.value())))
            .subscribe();
  }

  @Override
  @Bean(name = "updateMovementData")
  public void updateData() {
    createReceiver(MOVEMENT_TOPIC_UPDATE)
            .flatMap(r -> repository.findByIdDto(r.key())
                    .flatMap(p -> repository
                            .save(Movement.transformIntoData(r.value(), p.getId()))))
            .subscribe();
  }

  @Override
  @Bean(name = "deleteMovementData")
  public void deleteData() {
    createReceiver(MOVEMENT_TOPIC_DELETE)
            .flatMap(r -> {
              return repository.findByIdDto(r.key())
                      .flatMap(p -> repository.deleteById(p.getId()));
            })
            .subscribe();
  }
}
