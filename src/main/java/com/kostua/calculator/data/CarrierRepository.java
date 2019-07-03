package com.kostua.calculator.data;

import com.kostua.calculator.domain.Carrier;
import org.springframework.data.repository.CrudRepository;

/**
 *  This interface extends Spring Data JPA’s CrudRepository
 *  specifying the domain type as {@link Carrier}and the id type as Long.
 */
public interface CarrierRepository extends CrudRepository<Carrier, Long> {

}
