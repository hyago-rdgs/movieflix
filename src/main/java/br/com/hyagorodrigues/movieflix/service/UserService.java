package br.com.hyagorodrigues.movieflix.service;

import br.com.hyagorodrigues.movieflix.entity.User;
import br.com.hyagorodrigues.movieflix.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User request) {
        String password = request.getPassword();
        request.setPassword(passwordEncoder.encode(password));

        return repository.save(request);
    }
}
