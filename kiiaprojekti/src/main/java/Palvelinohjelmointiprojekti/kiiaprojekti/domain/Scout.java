package Palvelinohjelmointiprojekti.kiiaprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//luodaan partiolainen luokka/entity jonne talletetaan partiolaisten tiedot

@Entity
public class Scout {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String age;
	private String parent;
	private String email;
	private String phone;
	
	@ManyToOne
	@JoinColumn(name = "agegroupId")
	private Agegroup agegroup;
	
	
	public Scout(String name, String age, String parent, String email, String phone, Agegroup agegroup ) {
		super();
		this.name = name;
		this.age = age;
		this.parent = parent;
		this.email = email;
		this.phone = phone;
		this.agegroup = agegroup;
		
		
	}


	public Agegroup getAgegroup() {
		return agegroup;
	}


	public void setAgegroup(Agegroup agegroup) {
		this.agegroup = agegroup;
	}


	public Scout() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getParent() {
		return parent;
	}


	public void setParent(String parent) {
		this.parent = parent;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		if (this.agegroup != null)
			return "Scout [id=" + id + ", name=" + name + ", age=" + age + ", parent=" + parent + ", email=" + email
					+ ", phone=" + phone + ", category=" + this.getAgegroup() + "]";
		else
			return "Scout [id=" + id + ", name=" + name + ", age=" + age + ", parent=" + parent + ", email=" + email 
					+ ", phone=" + phone + " ]";
		
	}
	
	
	

}
