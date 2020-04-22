package com.example.cateringProject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//CRUD repositorio kategoria luokkaa varten
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	List<Customer> findByCompany(String company); //Kysely metodi käyttöliittymälle
}
