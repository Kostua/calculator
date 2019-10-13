package com.kostua.calculator.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;


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

@Entity
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double air;
    private Double sea;

    private Carrier() {
    }

    public Carrier(@NotNull String name, Double air, Double sea) {
        this.name = name;
        this.air = air;
        this.sea = sea;
    }



    protected boolean canEqual(final Object other) {
        return other instanceof Carrier;
    }

    public Long getId() {
        return this.id;
    }

    public @NotNull @NotNull String getName() {
        return this.name;
    }

    public Double getAir() {
        return this.air;
    }

    public Double getSea() {
        return this.sea;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(@NotNull @NotNull String name) {
        this.name = name;
    }

    public void setAir(Double air) {
        this.air = air;
    }

    public void setSea(Double sea) {
        this.sea = sea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrier carrier = (Carrier) o;
        return Objects.equals(id, carrier.id) &&
                Objects.equals(name, carrier.name) &&
                Objects.equals(air, carrier.air) &&
                Objects.equals(sea, carrier.sea);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, air, sea);
    }
    @Override
    public String toString() {
        return "Carriers{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", Air='" + air + '\'' +
                ", Sea='" + sea + '\'' +
                '}';

    }
}
