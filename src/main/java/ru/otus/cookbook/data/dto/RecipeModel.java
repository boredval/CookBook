package ru.otus.cookbook.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RecipeModel {

    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private String instructions;

    @NotNull
    private long categoryId;

    private String url;

    private String shortDescription;

}
