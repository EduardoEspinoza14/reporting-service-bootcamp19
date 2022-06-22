package com.nttdata.reportingservice.repository;

import com.nttdata.reportingservice.model.data.CustomerData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Class CustomerRepository.
 */
@Repository
public interface CustomerRepository extends ReactiveCrudRepository<CustomerData, Integer> {

  Mono<CustomerData> findByIdDto(String idDto);

}
