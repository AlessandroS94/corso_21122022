package it.corso.example.thymeleafproject.repository;

import it.corso.example.thymeleafproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}