package com.example.cateringProject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//Tämä on siksi, että ohjelma osaa navgoida omalle login sivulle, ettei springbootin tai bootstrapin login sivu
//tulisi päälle
@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
