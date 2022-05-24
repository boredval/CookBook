package ru.otus.cookbook.frontend.controller;

import ru.otus.cookbook.data.dto.RecipeModel;
import ru.otus.cookbook.service.RecipeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController {

    private final RecipeService recipeService;

    @Autowired
    public HomePageController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String showHomePage(Model model, @RequestParam(required = false) String query) {

        List<RecipeModel> recipes;

        if (StringUtils.isBlank(query)){
            recipes = recipeService.getAllRecipes();
        } else {
            recipes = recipeService.searchRecipesByTitle(query);
        }
        model.addAttribute(ControllerConstants.MODEL_ATTR_RECIPE_LIST, recipes);

        return ControllerConstants.TEMPLATE_HOMEPAGE;
    }

}
