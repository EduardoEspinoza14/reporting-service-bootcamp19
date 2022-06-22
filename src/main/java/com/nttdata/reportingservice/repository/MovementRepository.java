package com.nttdata.reportingservice.repository;

import com.nttdata.reportingservice.model.data.MovementData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Class MovementRepository.
 */
@Repository
public interface MovementRepository extends ReactiveCrudRepository<MovementData, Integer> {

  Mono<MovementData> findByIdDto(String idDto);

}
