package br.com.hyagorodrigues.movieflix.service;

import br.com.hyagorodrigues.movieflix.entity.Category;
import br.com.hyagorodrigues.movieflix.entity.Movie;
import br.com.hyagorodrigues.movieflix.entity.Streaming;
import br.com.hyagorodrigues.movieflix.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieService(MovieRepository repository, CategoryService categoryService, StreamingService streamingService) {
        this.movieRepository = repository;
        this.categoryService = categoryService;
        this.streamingService = streamingService;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public List<Movie> findByCategory(Long categoryId) {
        return movieRepository.findMovieByCategories(
                List.of(Category.builder()
                        .id(categoryId)
                        .build()
                ));
    }

    public Movie save(Movie request) {
        request.setCategories(this.findCategories(request.getCategories()));
        request.setStreamings(this.findStreamings(request.getStreamings()));

        return movieRepository.save(request);
    }

    public Optional<Movie> update(Long id, Movie request) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            List<Category> categories = this.findCategories(request.getCategories());
            List<Streaming> streamings = this.findStreamings(request.getStreamings());

            movie.setTitle(request.getTitle());
            movie.setDescription(request.getDescription());
            movie.setReleaseDate(request.getReleaseDate());
            movie.setRating(request.getRating());
            movie.getCategories().clear();
            movie.getCategories().addAll(categories);
            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movieRepository.save(movie);
            return Optional.of(movie);
        }

        return Optional.empty();
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category ->
                categoryService.findById(category.getId()).ifPresent(categoriesFound::add)
        );

        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> StreamingsFound = new ArrayList<>();
        streamings.forEach(streaming ->
                streamingService.findById(streaming.getId()).ifPresent(StreamingsFound::add)
        );

        return StreamingsFound;
    }
}
