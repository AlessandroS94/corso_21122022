package com.ntt.accademy.springbootdatajsp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "person")
public class Person extends BaseEntity {
    private String name;
    private String surname;

}