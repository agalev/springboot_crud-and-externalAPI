package com.postgresql.postgresql_demo;

import java.util.List;
import java.util.Map;
import java.util.HashMap;


import org.springframework.web.bind.annotation.RestController;

import com.postgresql.postgresql_demo.model.Person;
import com.postgresql.postgresql_demo.repo.PersonRepo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;




@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/")
public class PersonController {
    
    @Autowired
    PersonRepo repo;

    @GetMapping("/person")
    public List<Person> getAllPersons() {
        return repo.findAll();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Person with id:" + id + " does not exist."));
        return ResponseEntity.ok(person);
    }
    
    @PostMapping("/addPerson")
    public Person addPerson(@RequestBody Person person) {
       return repo.save(person);
    }

    @PatchMapping("person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person personDetails) {
        Person person = repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Person with id:" + id + " does not exist."));

        person.setName(personDetails.getName());

        Person updatedPerson = repo.save(person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePerson(@PathVariable Long id) {
        Person person = repo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Person with id:" + id + " does not exist."));

        repo.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
