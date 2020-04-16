package Palvelinohjelmointiprojekti.kiiaprojekti.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ScoutRepository extends CrudRepository<Scout, Long> {

	//Listataan partiolaiset ja lisätään crudrepository
	
	List<Scout> findByName(String name);

}
