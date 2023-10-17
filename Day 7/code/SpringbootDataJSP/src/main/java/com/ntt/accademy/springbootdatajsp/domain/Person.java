package com.ntt.accademy.springbootdatajsp.domain;

import jakarta.persistence.*;
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "gruppo_id")
    private Gruppo gruppo;

    private Tipo tipo;

}