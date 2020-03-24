package com.example.cateringProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.cateringProject.domain.Category;
import com.example.cateringProject.domain.CategoryRepository;
import com.example.cateringProject.domain.Product;
import com.example.cateringProject.domain.ProductRepository;

@SpringBootApplication
public class CateringProjectApplication {
	//Alustetaan loggeri
	private static final Logger log = LoggerFactory.getLogger(CateringProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CateringProjectApplication.class, args);
	}
	
	//Luodaan muutama esimerkki tuote ja tuotteiden kategoriat
	@Bean
	public CommandLineRunner exampleProduct(ProductRepository repository, CategoryRepository crepository) {
		return(args) ->{
			log.info("save a couple of categories");
			crepository.save(new Category("Catering"));
			crepository.save(new Category("Kaffepaku"));
			//Hyvät ideat on yrityksen työntekijälle joka voi matkalla ollessaan lisätä tuotteita ja kokeilla
			//niitä kun ei ole enään tien päällä
			crepository.save(new Category("Hyvät ideat"));
			
			System.out.println("Kategoriat lisätty");
			
			log.info("save a couple of products");
			repository.save(new Product("Pulla", 2.00, crepository.findByName("Kaffepaku").get(0)));
			repository.save(new Product("Kakku pala", 3.50, crepository.findByName("Catering").get(0)));
			repository.save(new Product("Pasteija", 2.00, crepository.findByName("Hyvät ideat").get(0)));
			
			System.out.println("Tuotteet lisätty");
			
			//Logataan konsoliin tuotteista pieni teksti pätkä
			log.info("Fetch all products");
			for (Product product : repository.findAll()) {
				log.info(product.toString());
			}
			
		};
	}

}
