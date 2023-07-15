package it.corso.example.thymeleafproject.Controller;

import it.corso.example.thymeleafproject.business.interfaces.CategoryBO;
import it.corso.example.thymeleafproject.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {
    @Autowired
    CategoryBO categoryBO;

    @GetMapping("/create/category")
    public String create(Model model){
        model.addAttribute("categories",categoryBO.getAllCategory());
        model.addAttribute("category",new Category());
        return "createCategory.html";
    }

    @PostMapping("/create/category")
    public String create(Model model,@ModelAttribute Category category){
        category.setName(category.getName().toUpperCase());
        categoryBO.saveCategory(category);
        return "redirect:/index";
    }

    @GetMapping("/delete/category")
    public String create(Model model, @RequestParam(name = "id") long id){
        categoryBO.deleteCategory(id);
        return "redirect:/index";
    }
}
