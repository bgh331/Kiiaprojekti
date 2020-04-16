package Palvelinohjelmointiprojekti.kiiaprojekti.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AgegroupRepository extends CrudRepository<Agegroup, Long> {

	//listataan ja lisätään crudrepository ikäkausi listaan.
	
	List<Agegroup> findByName(String name);

}