package com.nttdata.reportingservice.repository;

import com.nttdata.reportingservice.model.data.ProductData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Class ProductRepository.
 */
@Repository
public interface ProductRepository extends ReactiveCrudRepository<ProductData, Integer> {

  Mono<ProductData> findByIdDto(String idDto);

}
