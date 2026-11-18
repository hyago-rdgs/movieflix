package br.com.hyagorodrigues.movieflix.repository;

import br.com.hyagorodrigues.movieflix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
