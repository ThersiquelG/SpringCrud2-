package co.simplon.simplecrud2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.simplecrud2.model.Film;;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
