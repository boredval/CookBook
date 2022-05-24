package ru.otus.cookbook.data.converters;

import ru.otus.cookbook.data.dto.RecipeModel;
import ru.otus.cookbook.data.entity.CategoryEntity;
import ru.otus.cookbook.data.entity.RecipeEntity;
import ru.otus.cookbook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeToEntityConverter implements Converter<RecipeModel, RecipeEntity> {

    private CategoryRepository categoryRepository;

    @Autowired
    public RecipeToEntityConverter(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public RecipeEntity convert(RecipeModel source) {
        var target = new RecipeEntity();
        target.setId(source.getId());
        target.setCategory(this.getCategory(source.getCategoryId()));
        target.setInstructions(source.getInstructions());
        target.setTitle(source.getTitle());
        return target;
    }

    private CategoryEntity getCategory(long id){
        return categoryRepository.findById(id).orElse(null);
    }
}
