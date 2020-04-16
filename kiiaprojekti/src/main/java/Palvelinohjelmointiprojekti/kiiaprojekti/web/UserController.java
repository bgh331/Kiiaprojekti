package Palvelinohjelmointiprojekti.kiiaprojekti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Palvelinohjelmointiprojekti.kiiaprojekti.domain.NewUser;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.User;
import Palvelinohjelmointiprojekti.kiiaprojekti.domain.UserRepository;

import javax.validation.Valid;

@Controller
public class UserController {
	@Autowired
    private UserRepository urepository; 
	
	//Luodaan kartoittaja kirjautumiselle ja uuden käyttäjän luomiselle
    @RequestMapping(value = "signup")
    public String addStudent(Model model){
    	model.addAttribute("newuser", new NewUser());
        return "signup";
    }	
    
    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
    
    //Tallenetaan uusi käyttäjä user luokkaan 
    
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") NewUser newuser, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // validation errors
    		if (newuser.getPassword().equals(newuser.getPasswordCheck())) { // check password match		
	    		String pwd = newuser.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd); //enkoodataan salasana
		    	
		    	//Annetaan uudelle käyttäjälle haluttu rooli ja liitetään salasana
		    	User u = new User();
		    	u.setPasswordHash(hashPwd);
		    	u.setUsername(newuser.getUsername());
		    	u.setRole("USER");
		    	if (urepository.findByUsername(newuser.getUsername()) == null) { // tarkistetaan onko käyttäjä jo olemassa?
		    		urepository.save(u);
		    	} 
		    	//Pari virhe viestiä kaiken varalle
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists please choose another.");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Password incorrect, please try again.");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    
    
}