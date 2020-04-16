package Palvelinohjelmointiprojekti.kiiaprojekti;

import org.slf4j.Logger;


import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import Palvelinohjelmointiprojekti.kiiaprojekti.domain.User;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.UserRepository;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.Agegroup;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.AgegroupRepository;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.Scout;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.ScoutRepository;


@SpringBootApplication
public class KiiaprojektiApplication {
	private static final Logger log = LoggerFactory.getLogger(KiiaprojektiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KiiaprojektiApplication.class, args);
	}

	@Bean
	public CommandLineRunner kiiaprojekti(ScoutRepository brepository, AgegroupRepository Arepository, UserRepository urepository) {
		return (args) -> {
			
			//Annetaan valmista dataa luokkiin 

			Arepository.save(new Agegroup("Sudenpentu (7-10v.)"));
			Arepository.save(new Agegroup("Seikkailija (10-12v.)"));
			Arepository.save(new Agegroup("Tarpoja (12-15v.)"));
			Arepository.save(new Agegroup("Samoaja (15-18v.)"));
			Arepository.save(new Agegroup("Vaeltaja (18v. ->)"));

			log.info("save a couple of scouts"); // logger kertoo ohjelman toiminnasta
			brepository.save(new Scout("Lotta Lehtinen", "12", "Niina Lehtinen", "niin.leht@gamil.com","0505501345",
					Arepository.findByName("Seikkailija (10-12v.)").get(0)));
			brepository.save(new Scout("Niilo Kukonmäki", "7", "Antti Kukonmäki", "antti.kukonmäki@gamil.com","0505501345",
					Arepository.findByName("Sudenpentu (7-10v.)").get(0)));
			
			
			//Luodaan uusia käyttäjiä 
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			User user3 = new User("Kiia", "$2a$04$9gKGDOVL/JR7dB3FnoRir..fPCreozI7YmNnR.PwBJIfJcl8NP3.e", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
			
			for (Scout scout : brepository.findAll()) {
				log.info(scout.toString());
			}

		};
	}

}