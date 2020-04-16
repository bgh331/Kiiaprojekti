package Palvelinohjelmointiprojekti.kiiaprojekti.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


//luodaan luokka/entity agegroup johon talletetaan kaikki ikäkaudet 
@Entity
public class Agegroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long agegroupId;
	private String name;
	
	//linkitetään agegroup scout listan kanssa
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "agegroup")
	private List<Scout> scouts;

	public Agegroup( String name) {
		super();
		
		this.name = name;
		
	}
	public Agegroup() {

	}

	public Long getAgegroupId() {
		return agegroupId;
	}

	public void setAgegroupId(long agegroupId) {
		this.agegroupId = agegroupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Scout> getScouts() {
		return scouts;
	}

	public void setScouts(List<Scout> scouts) {
		this.scouts = scouts;
	}

	@Override
	public String toString() {
		return "Agegroup [agegroupId=" + agegroupId + ", name=" + name + " ]";
	}

	
	
	
}
