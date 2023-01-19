package it.corso.example.thymeleafproject.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    private String name;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    private List<Asset> cryptoes = new ArrayList<>();

    public List<Asset> getCryptoes() {
        return cryptoes;
    }

    public void setCryptoes(List<Asset> cryptoes) {
        this.cryptoes = cryptoes;
    }

    public void addCrypto(Asset asset){
        this.cryptoes.add(asset);
    }
    public void deleteCrypto(Asset asset){
        this.cryptoes.remove(asset);
    }
}