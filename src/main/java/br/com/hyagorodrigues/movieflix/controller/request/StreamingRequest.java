package br.com.hyagorodrigues.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(
        @NotEmpty(message = "Streaming service name is required") String name
) {
}
