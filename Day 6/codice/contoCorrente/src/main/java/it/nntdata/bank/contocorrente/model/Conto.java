package it.nntdata.bank.contocorrente.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.ConstructorParameters;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "conto")
public class Conto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long iban;



}