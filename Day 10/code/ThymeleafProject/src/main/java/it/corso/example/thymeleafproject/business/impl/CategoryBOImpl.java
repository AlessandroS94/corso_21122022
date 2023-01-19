package it.corso.example.thymeleafproject.business.impl;

import it.corso.example.thymeleafproject.business.interfaces.CategoryBO;
import it.corso.example.thymeleafproject.model.Category;
import it.corso.example.thymeleafproject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBOImpl implements CategoryBO {
    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }
    @Override
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
}
