package it.corso.example.thymeleafproject.business.interfaces;

import it.corso.example.thymeleafproject.model.Category;

import java.util.List;

public interface CategoryBO {
    Category getCategory(Long id);
    List<Category> getAllCategory();
}
