package br.com.hyagorodrigues.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(
        @NotEmpty(message = "Category name is required") String name
) {
}
