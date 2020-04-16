package Palvelinohjelmointiprojekti.kiiaprojekti;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import Palvelinohjelmointiprojekti.kiiaprojekti.domain.Agegroup;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.AgegroupRepository;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.Scout;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.ScoutRepository;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.User;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.UserRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class ScoutRepositoryTest {

		@Autowired
		private ScoutRepository repository;
		@Autowired
		private AgegroupRepository Arepository;
		@Autowired
		private UserRepository usrepository;
		
	@Test
		public void findBynameShouldReturnScout() {
		List<Scout> scouts = repository.findByName("Lotta Lehtinen");
		assertThat(scouts).hasSize(1);
		assertThat(scouts.get(0).getName()).isEqualTo("Lotta Lehtinen");
		}
		
	@Test
		public void createNewScout() {
		Scout scout = new Scout("Niilo Kukonmäki", "7", "Antti Kukonmäki", "antti.kukonmäki@gamil.com","0505501345",
				Arepository.findByName("Sudenpentu (7-10v.)").get(0));
		repository.save(scout);
		assertThat(scout.getId()).isNotNull();
		}
	
    
    @Test
    public void findByAgegroupNameTest() {
    	List<Agegroup> group = Arepository.findByName("Sudenpentu (7-10v.)");
    	assertThat(group).hasSize(1);
        assertThat(group.get(0).getName()).isEqualTo("Sudenpentu (7-10v.)");
    }
    
    @Test
    public void CreateNewAgegroupTest() {
       	Agegroup group = new Agegroup("Fossiili");
    	Arepository.save(group);
    	assertThat(group.getAgegroupId()).isNotNull();
    }
    
    @Test
    public void deleteAgegroupTests() {
    	Agegroup group = new Agegroup("Konkari");
    	Arepository.save(group);
    	Arepository.deleteAll();
    	assertThat(Arepository.count()).isEqualTo(0); // Testing deletes with size.
    }   
    
    @Test
    public void findByUsernameUserTest() {
        User user = usrepository.findByUsername("user");
        assertThat(user.getUsername()).isNotNull();
        assertThat(user.getRole()).isEqualTo("USER");
    }
    
    @Test
    public void createNewUserTest() {
    	User user = new User("Kristiina", "$2a$09$Z0WM3EGzz5sJJj3fA5KOnuvUHhrMO4tCcnRHFbM7HT4aBbyZ4G.AO", "USER");
    	usrepository.save(user);
    	assertThat(user.getId()).isNotNull();
    }  
    
    @Test
    public void deleteAllUsersTest() {
    	User user = new User("Kaukonen", "$2a$09$glF.zGYv.66kknTC4oLTkepUOPrMMlxTeLVRcjiDU1Q5e6te3cc0K", "USER");
    	usrepository.save(user);
    	usrepository.deleteAll();
    	assertThat(usrepository.count()).isEqualTo(0); // Testing deletes with size. Everything works - need to have different usernames...
    	// .. because of constraints in the SQL tables and columns - good and efficient.
    }   

}