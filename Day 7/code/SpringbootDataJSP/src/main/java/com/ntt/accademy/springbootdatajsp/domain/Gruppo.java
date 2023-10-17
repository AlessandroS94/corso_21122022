package com.ntt.accademy.springbootdatajsp.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gruppo")
public class Gruppo extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "gruppo", cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    private List<Person> persons = new ArrayList<>();

    public void addPerson(Person person){
        person.setGruppo(this);
        this.persons.add(person);
    }
}