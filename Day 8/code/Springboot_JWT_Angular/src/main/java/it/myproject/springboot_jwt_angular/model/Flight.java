package it.myproject.springboot_jwt_angular.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
public class Flight {
    private int id;
    private int flightNumber;
    private String origin;
    private String destination;


}
