package ru.otus.cookbook.data.converters;

import ru.otus.cookbook.data.dto.CategoryModel;
import ru.otus.cookbook.data.entity.CategoryEntity;
import ru.otus.cookbook.frontend.util.UrlResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToModelConverter implements Converter<CategoryEntity, CategoryModel> {

    private UrlResolver urlResolver;

    @Autowired
    public CategoryToModelConverter (UrlResolver urlResolver){
        this.urlResolver = urlResolver;
    }

    @Override
    public CategoryModel convert(CategoryEntity source) {
        CategoryModel target = new CategoryModel();
        target.setDescription(source.getDescription());
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setUrl(urlResolver.resolve(target));
        return target;
    }
}
