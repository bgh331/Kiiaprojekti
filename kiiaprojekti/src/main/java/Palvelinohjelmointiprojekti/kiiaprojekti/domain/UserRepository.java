package Palvelinohjelmointiprojekti.kiiaprojekti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	
	//Luodaan käyttäjä repository joka etsii käyttäjä nimen
	User findByUsername(String username);
	
}