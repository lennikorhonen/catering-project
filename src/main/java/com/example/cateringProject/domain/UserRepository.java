package com.example.cateringProject.domain;

import org.springframework.data.repository.CrudRepository;

//CRUD repositorio käyttäjiä varten
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username); //Kysely metodi käyttöliittymälle
}
