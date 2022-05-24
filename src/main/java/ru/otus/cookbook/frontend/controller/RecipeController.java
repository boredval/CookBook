package ru.otus.cookbook.frontend.controller;

import ru.otus.cookbook.data.dto.RecipeModel;
import ru.otus.cookbook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"/",""})
    public String redirectHome() {
        return ControllerConstants.REDIRECT_PREFIX + "/";
    }

    @GetMapping("/{id}")
    public String showRecipe(@PathVariable long id, Model model) {
        Optional<RecipeModel> recipe = recipeService.getRecipeById(id);

        if (recipe.isPresent()) {
            model.addAttribute(ControllerConstants.MODEL_ATTR_RECIPE, recipe.get());
            return ControllerConstants.TEMPLATE_RECIPE;
        }

        return ControllerConstants.TEMPLATE_RECIPE_NOT_FOUND;

    }

    @GetMapping("/add")
    public String showAddRecipe(Model model) {
        model.addAttribute(ControllerConstants.MODEL_ATTR_RECIPE,new RecipeModel());
        return ControllerConstants.TEMPLATE_ADD_RECIPE;
    }

    @PostMapping("/add")
    public String addRecipe(@Valid RecipeModel recipe, BindingResult result) {

        if (result.hasErrors()) {
            return ControllerConstants.TEMPLATE_ADD_RECIPE;
        }

        RecipeModel savedRecipe = recipeService.saveRecipe(recipe);
        return ControllerConstants.REDIRECT_PREFIX + savedRecipe.getUrl();
    }
}
