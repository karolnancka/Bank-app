package bank.controller;

import bank.model.Category;
import bank.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public String showAll(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "/categories/allCategories";
    }

    @GetMapping("/add")
    public String getAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/addCategory";
    }

    @PostMapping("/add")
    public String addCategory(@Valid final Category category, final BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            return "categories/addCategory";
        }

        categoryRepository.save(category);
        return "redirect:all";
    }
}
