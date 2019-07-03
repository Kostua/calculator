package com.kostua.calculator.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


/**
 *Class represent carrier company with two category of delivery price
 * for air and sea.
 *
 * @Data is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods,
 * based on the fields.
 *
 * @Entity is a JPA annotation to make this object ready for storage in a JPA-based data store.
 *
 */

@Data
@Entity
@NoArgsConstructor
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    private Double air;
    private Double sea;

    public Carrier(@NotNull String name, Double air, Double sea) {
        this.name = name;
        this.air = air;
        this.sea = sea;
    }

}
