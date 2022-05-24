package ru.otus.cookbook.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryModel {

    private long id;

    @NotBlank
    private String title;

    private String description;

    private String url;

}
