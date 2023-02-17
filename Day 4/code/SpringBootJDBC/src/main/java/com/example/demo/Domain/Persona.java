package com.example.demo.Domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    private Long id;
    private String nome;
    private String cognome;
}