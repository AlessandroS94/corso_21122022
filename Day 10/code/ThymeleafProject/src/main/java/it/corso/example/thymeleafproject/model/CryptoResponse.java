package it.corso.example.thymeleafproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CryptoResponse {
    private String timestamp;
    private String low;
    private String high;
    private String last;
    private String volume;
    private String volume30d;
    private String bid;
    private String ask;
    private String priceChange;
    private String priceChangePercentage;
    private String pair;
}
