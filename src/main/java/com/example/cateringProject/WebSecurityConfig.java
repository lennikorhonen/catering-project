package com.example.cateringProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.cateringProject.web.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/css/**").permitAll() //css käytössä vaikka ei ole kirjautunut
		.and()
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
	.formLogin()
		.defaultSuccessUrl("/productlist") //Onnistuneessa kirjautumisessa ohjataan suoraan productlist sivulle
		.permitAll()
		.and()
	.logout()
		.permitAll()
		.invalidateHttpSession(true); //Kirjautuessa ulos mitätöidään http istunto
	}
	
	//Injektoidaan luokka UserDetailsServiceImpl
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	//Muunnetaan salasana koodikielelle
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new
				BCryptPasswordEncoder());
	}
}