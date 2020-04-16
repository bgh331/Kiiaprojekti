package Palvelinohjelmointiprojekti.kiiaprojekti.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Palvelinohjelmointiprojekti.kiiaprojekti.domain.AgegroupRepository;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.Scout;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.ScoutRepository;



@Controller
public class ScoutController {
	@Autowired
	private ScoutRepository repository;
	@Autowired
	private AgegroupRepository Arepository;
	
	//kirjautumis sivu
    @RequestMapping(value="login")
    public String login() {	
        return "login";
    }
    //Partiolais listan listaus sekä lisääminen
	@RequestMapping(value = { "/", "scoutlist" })
	public String Scoutlist(Model model) {
		model.addAttribute("scouts", repository.findAll());
		model.addAttribute("scout", new Scout());
		model.addAttribute("agegroups", Arepository.findAll());
		return "scoutlist";
	}



	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Scout scout) {
		repository.save(scout);
		return "redirect:scoutlist";
	}
	 @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String DeleteScout(@PathVariable("id") Long Scoutid, Model model) {
		repository.deleteById(Scoutid);
		return "redirect:../scoutlist";
	}
	 @PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editScout(@PathVariable("id") Long scoutId, Model model) {
		// Haetaan tietokannasta SQL-lauseella kirja, jolla on tämä id
		// ja lisätään modeliin
		model.addAttribute("scout", repository.findById(scoutId));
		model.addAttribute("agegroups", Arepository.findAll());
		return "editscout";
	}

}
