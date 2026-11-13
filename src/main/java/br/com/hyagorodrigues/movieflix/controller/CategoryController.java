package br.com.hyagorodrigues.movieflix.controller;

import br.com.hyagorodrigues.movieflix.controller.request.CategoryRequest;
import br.com.hyagorodrigues.movieflix.controller.response.CategoryResponse;
import br.com.hyagorodrigues.movieflix.entity.Category;
import br.com.hyagorodrigues.movieflix.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CategoryResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable Long id) {
        Optional<Category> category = service.findById(id);
        if(category.isPresent()) {
            return category.get();
        }

        return null;
    }

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest request) {
        return service.save(category);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
