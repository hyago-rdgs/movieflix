package br.com.hyagorodrigues.movieflix.controller.response;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email
) {
}
