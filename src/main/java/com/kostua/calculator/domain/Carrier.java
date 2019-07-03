package com.kostua.calculator.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


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
