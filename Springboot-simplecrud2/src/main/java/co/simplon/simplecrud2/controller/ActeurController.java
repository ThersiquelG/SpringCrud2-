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
import co.simplon.simplecrud2.repository.ActeurRepository;







@RestController
@RequestMapping("/api")
public class ActeurController {
	
	@Autowired
	ActeurRepository repository;
	
	
	@CrossOrigin
	@GetMapping("/acteur")
	List<Acteur> getAllActeur(){
		return repository.findAll();
		
	}
	
	@CrossOrigin
	@GetMapping("/acteur/{id}")
	ResponseEntity<Acteur> getPeopleById(@PathVariable(value="idActeur") long idActeur) {
	    Acteur acteur = repository.findOne(idActeur);
	    if(acteur == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(acteur);
	}
	
	@CrossOrigin
	@PostMapping("/acteur")
	Acteur addActeur(@Valid @RequestBody Acteur acteur){
		return repository.save(acteur);
	}
	
	@CrossOrigin
	@PutMapping("/acteur/{id}")
	ResponseEntity<Acteur> updatePeople(@PathVariable(value="idActeur") long idActeur, @Valid @RequestBody Acteur acteur){
		Acteur acteurToUpdate = repository.findOne(idActeur);
		if(acteurToUpdate == null)
			return ResponseEntity.notFound().build();
		
		// Update the mandatory attributes
		acteurToUpdate.setFirstname(acteur.getFirstname());
		acteurToUpdate.setName(acteur.getName());
		
		// Update all other not null attributes
		if(acteur.getAddress() != null)
			acteurToUpdate.setAddress(acteur.getAddress());
		
		if(acteur.getPhone() != null)
			acteurToUpdate.setPhone(acteur.getPhone());
		
		if(acteur.getEmail() != null)
			acteurToUpdate.setEmail(acteur.getEmail());
		
		Acteur updatedPeople = repository.save(acteurToUpdate);
		return ResponseEntity.ok(updatedPeople);
	}
	
	
	
	@CrossOrigin
	@DeleteMapping("/acteur/{id}")
	ResponseEntity<Acteur> deletePeople(@PathVariable(value="idActeur") long idActeur){
		Acteur acteur = repository.findOne(idActeur);
		if(acteur == null)
			return ResponseEntity.notFound().build();
		
		repository.delete(acteur);
		return ResponseEntity.ok().build();
	}
	
	
	

}
