package ru.otus.cookbook.frontend.interceptor;

import ru.otus.cookbook.data.dto.CategoryModel;
import ru.otus.cookbook.frontend.util.UrlResolver;
import ru.otus.cookbook.service.CategoryService;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class BaseTemplateHandlerInterceptor extends HandlerInterceptorAdapter {

    private final CategoryService categoryService;
    private final UrlResolver urlResolver;

    @Autowired
    public BaseTemplateHandlerInterceptor(CategoryService categoryService, UrlResolver urlResolver) {
        this.categoryService = categoryService;
        this.urlResolver = urlResolver;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) {

        if (modelAndView != null) {

            ModelMap modelMap = modelAndView.getModelMap();

            if (!modelMap.containsAttribute("categories")) {
                List<CategoryModel> categories = IteratorUtils.
                        toList(categoryService.
                                getAllCategories().
                                iterator());
                modelMap.addAttribute("categories", categories);
            }
            modelMap.addAttribute("homepageUrl", urlResolver.resolve(UrlResolver.Path.HOMEPAGE));
            modelMap.addAttribute("addRecipeUrl", urlResolver.resolve(UrlResolver.Path.ADD_RECIPE));
            modelMap.addAttribute("addCategoryUrl", urlResolver.resolve(UrlResolver.Path.ADD_CATEGORY));
        }

    }
}
