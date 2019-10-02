package com.kostua.calculator.data;

import com.kostua.calculator.domain.Carrier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *  This interface extends Spring Data JPA’s PagingAndSortingRepository
 *  specifying the domain type as {@link Carrier}and the id type as Long.
 */
public interface CarrierRepository extends PagingAndSortingRepository <Carrier, Long> {

}
