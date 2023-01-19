package it.corso.example.thymeleafproject.repository;

import it.corso.example.thymeleafproject.model.Asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

}