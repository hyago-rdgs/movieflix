package br.com.hyagorodrigues.movieflix.controller;

import br.com.hyagorodrigues.movieflix.controller.request.CategoryRequest;
import br.com.hyagorodrigues.movieflix.controller.response.CategoryResponse;
import br.com.hyagorodrigues.movieflix.entity.Category;
import br.com.hyagorodrigues.movieflix.mapper.CategoryMapper;
import br.com.hyagorodrigues.movieflix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> categories = service.findAll().stream().map(CategoryMapper::toCategoryResponse).toList();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return service.findById(id).map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest request) {
        Category category = CategoryMapper.toCategory(request);
        Category save = service.save(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponse(save));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
