package br.com.hyagorodrigues.movieflix.mapper;

import br.com.hyagorodrigues.movieflix.controller.request.CategoryRequest;
import br.com.hyagorodrigues.movieflix.controller.response.CategoryResponse;
import br.com.hyagorodrigues.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
