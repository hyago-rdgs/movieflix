package br.com.hyagorodrigues.movieflix.repository;

import br.com.hyagorodrigues.movieflix.entity.Category;
import br.com.hyagorodrigues.movieflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("""
        SELECT DISTINCT m
        FROM Movie m
        JOIN m.categories c
        WHERE c IN :categories
    """)
    List<Movie> findMovieByCategories(List<Category> categories);

    List<Movie> findTop5ByOrderByRatingDesc();
}
