package ru.otus.cookbook.frontend.util;

import ru.otus.cookbook.data.dto.CategoryModel;
import ru.otus.cookbook.data.dto.RecipeModel;
import org.springframework.stereotype.Component;

@Component
public class UrlResolver {

    private static final String SEGMENT_CATEGORY = "categories";
    private static final String SEGMENT_RECIPE = "recipes";
    private static final String SEGMENT_ADD = "add";

    public enum Path {
        ADD_RECIPE("/" + SEGMENT_RECIPE + "/" + SEGMENT_ADD),
        ADD_CATEGORY("/" + SEGMENT_CATEGORY + "/" + SEGMENT_ADD),
        HOMEPAGE("/");

        private final String value;
        Path(String path){
            this.value = path;
        }

        String getValue(){
            return this.value;
        }
    }

    public String resolve(CategoryModel category) {
        return buildUrlFromSegments(SEGMENT_CATEGORY, Long.toString(category.getId()));
    }

    public String resolve(RecipeModel recipe) {
        return buildUrlFromSegments(SEGMENT_RECIPE, Long.toString(recipe.getId()));
    }

    public String resolve(Path path){
        return path.getValue();
    }


    private String buildUrlFromSegments(String... segments){
        if (segments.length == 0){
            return "/";
        }

        StringBuilder urlBuilder = new StringBuilder();
        for (String segment : segments) {
            urlBuilder.append("/");
            urlBuilder.append(segment);
        }
        return urlBuilder.toString();
    }
}
