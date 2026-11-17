package br.com.hyagorodrigues.movieflix.mapper;

import br.com.hyagorodrigues.movieflix.controller.request.MovieRequest;
import br.com.hyagorodrigues.movieflix.controller.response.CategoryResponse;
import br.com.hyagorodrigues.movieflix.controller.response.MovieResponse;
import br.com.hyagorodrigues.movieflix.controller.response.StreamingResponse;
import br.com.hyagorodrigues.movieflix.entity.Category;
import br.com.hyagorodrigues.movieflix.entity.Movie;
import br.com.hyagorodrigues.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.function.Function;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequest movieRequest) {

        List<Category> categories = movieRequest.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = movieRequest.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .releaseDate(movieRequest.releaseDate())
                .rating(movieRequest.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie) {

        List<CategoryResponse> categories = movie.getCategories().stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings().stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }
}
