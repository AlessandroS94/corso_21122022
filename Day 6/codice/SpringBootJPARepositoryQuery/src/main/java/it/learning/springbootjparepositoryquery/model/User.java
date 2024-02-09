package it.learning.springbootjparepositoryquery.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    private String name;

    private Date birthday;

}