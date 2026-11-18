package br.com.hyagorodrigues.movieflix.service;

import br.com.hyagorodrigues.movieflix.entity.User;
import br.com.hyagorodrigues.movieflix.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User request) {
        return repository.save(request);
    }
}
