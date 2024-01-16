package com.postgresql.postgresql_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.postgresql.postgresql_demo.model.Person;

@RepositoryRestResource
public interface PersonRepo extends JpaRepository<Person, Long> {
    
}
