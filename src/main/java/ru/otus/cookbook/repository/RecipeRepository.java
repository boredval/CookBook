package ru.otus.cookbook.repository;

import ru.otus.cookbook.data.entity.CategoryEntity;
import ru.otus.cookbook.data.entity.RecipeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {

    List<RecipeEntity> findRecipeEntitiesByCategory(CategoryEntity category);
}