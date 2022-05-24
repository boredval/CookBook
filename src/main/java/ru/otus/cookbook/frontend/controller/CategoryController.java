package ru.otus.cookbook.frontend.controller;

import ru.otus.cookbook.data.dto.CategoryModel;
import ru.otus.cookbook.data.dto.RecipeModel;
import ru.otus.cookbook.service.CategoryService;
import ru.otus.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;
    private RecipeService recipeService;

    @Autowired
    public CategoryController(CategoryService categoryService, RecipeService recipeService) {
        this.categoryService = categoryService;
        this.recipeService = recipeService;
    }

    @GetMapping({"/",""})
    public String redirectHome() {
        return ControllerConstants.REDIRECT_PREFIX + "/";
    }

    @GetMapping("/{id}")
    public String showCategory(@PathVariable long id, Model model) {
        Optional<CategoryModel> categoryOptional = categoryService.getCategoryByID(id);

        if (categoryOptional.isPresent()) {
            CategoryModel category = categoryOptional.get();

            List<RecipeModel> recipes = recipeService.getRecipesByCategory(category);

            model.addAttribute(ControllerConstants.MODEL_ATTR_CATEGORY, category);
            model.addAttribute(ControllerConstants.MODEL_ATTR_RECIPE_LIST, recipes);

            return ControllerConstants.TEMPLATE_CATEGORY;
        }

        return ControllerConstants.TEMPLATE_CATEGORY_NOT_FOUND;

    }

    @GetMapping("/add")
    public String showAddCategory(Model model) {
        model.addAttribute(ControllerConstants.MODEL_ATTR_CATEGORY, new CategoryModel());
        return ControllerConstants.TEMPLATE_ADD_CATEGORY;
    }

    @PostMapping("/add")
    public String addCategory(@Valid CategoryModel category, BindingResult result) {

        if (result.hasErrors()) {
            return ControllerConstants.TEMPLATE_ADD_CATEGORY;
        }

        CategoryModel savedCategory = categoryService.saveCategory(category);
        return ControllerConstants.REDIRECT_PREFIX + savedCategory.getUrl();
    }
}
