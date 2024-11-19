package it.myproject.springboot_jwt_angular.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employe")
public class Employee extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
}