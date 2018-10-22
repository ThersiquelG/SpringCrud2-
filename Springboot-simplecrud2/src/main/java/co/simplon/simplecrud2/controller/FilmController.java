package co.simplon.simplecrud2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.simplecrud2.model.Acteur;
import co.simplon.simplecrud2.model.Film;
import co.simplon.simplecrud2.repository.ActeurRepository;
import co.simplon.simplecrud2.repository.FilmRepository;

@RestController
@RequestMapping("/api")
public class FilmController {
	@Autowired
	FilmRepository repository;
	
	
	@CrossOrigin
	@GetMapping("/film")
	List<Film> getAllActeur(){
		return repository.findAll();
		
	}
	
	@CrossOrigin
	@GetMapping("/film/{id}")
	ResponseEntity<Film> getPeopleById(@PathVariable(value="idFilm") long idFilm) {
	    Film film = repository.findOne(idFilm);
	    if(film == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(film);
	}
	
	@CrossOrigin
	@PostMapping("/film")
	Film addActeur(@Valid @RequestBody Film film){
		return repository.save(film);
	}
	
	@CrossOrigin
	@PutMapping("/film/{id}")
	ResponseEntity<Film> filmToUpdate(@PathVariable(value="idFilm") long idFilm, @Valid @RequestBody Film film){
		Film filmToUpdate = repository.findOne(idFilm);
		if(filmToUpdate == null)
			return ResponseEntity.notFound().build();
		
		// Update the mandatory attributes
		filmToUpdate.setName(film.getName());
		
		
	
		// Update all other not null attributes
		if(film.getYear() != null)
			filmToUpdate.setYear(film.getYear());
		
		if(film.getRate() != 0)
			filmToUpdate.setRate(film.getRate());
		
		
		
		
		Film updatetedFilm = repository.save(filmToUpdate);
		return ResponseEntity.ok(updatetedFilm);
	}
	
	
	
	@CrossOrigin
	@DeleteMapping("/film/{id}")
	ResponseEntity<Film> deletePeople(@PathVariable(value="idFilm") long idFilm){
		Film film = repository.findOne(idFilm);
		if(film == null)
			return ResponseEntity.notFound().build();
		
		repository.delete(film);
		return ResponseEntity.ok().build();
	}
	

}
