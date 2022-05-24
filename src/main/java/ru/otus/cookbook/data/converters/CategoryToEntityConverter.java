package ru.otus.cookbook.data.converters;

import ru.otus.cookbook.data.dto.CategoryModel;
import ru.otus.cookbook.data.entity.CategoryEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToEntityConverter implements Converter<CategoryModel, CategoryEntity> {

    @Override
    public CategoryEntity convert(CategoryModel source) {
        CategoryEntity target = new CategoryEntity();
        target.setDescription(source.getDescription());
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        return target;
    }
}
