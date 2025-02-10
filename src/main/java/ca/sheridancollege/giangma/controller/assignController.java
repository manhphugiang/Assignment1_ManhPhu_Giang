package ca.sheridancollege.giangma.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.giangma.bean.Dog;
import ca.sheridancollege.giangma.bean.Judge;
import ca.sheridancollege.giangma.bean.Owner;
import ca.sheridancollege.giangma.repository.DogRepository;
import ca.sheridancollege.giangma.repository.JudgeRepository;
import ca.sheridancollege.giangma.repository.OwnerRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class assignController {

	private DogRepository dogRepo;
	private JudgeRepository judgeRepo;
	private OwnerRepository ownerRepo;	
	
	@GetMapping("/assignJudgeToDog")
	public String getTheAssignJudgeToDogPageForDaddy(Model model) {
		
		model.addAttribute("judgeList", judgeRepo.findAll());
		model.addAttribute("dogList", dogRepo.findAll());		
		return "assignJudgeToDog.html";
	}
	
	@PostMapping("/assignJudgeToDog")
	public String processAssignJudgeToDog(@RequestParam int judgeId, @RequestParam List<Integer> dogIds) {
	    Judge judge = judgeRepo.findById(judgeId)
	    		.orElseThrow(() -> new RuntimeException("Judge not found"));
	    
	    List<Dog> dogs = dogRepo.findAllById(dogIds);
	    
	    
	    judge.setDogs(dogs); 
	    judgeRepo.save(judge);
	    return "redirect:/assignJudgeToDog";
	}
	
	
	@GetMapping("/assignOwnerToDog")
	public String getAssignOwnerToDog(Model model) {
		
		model.addAttribute("ownerList", ownerRepo.findAll());
		model.addAttribute("dogList", dogRepo.findAll());		
		return "assignOwnerToDog.html";
	}
	
	@PostMapping("/assignOwnerToDog")
	public String procesAassignOwnerToDog(@RequestParam int ownerId, @RequestParam List<Integer> dogIds) {
	    Owner owner = ownerRepo.findById(ownerId)
	    		.orElseThrow(() -> new RuntimeException("Owner not found"));
	    
	    List<Dog> dogs = dogRepo.findAllById(dogIds);
	    
/*	  //  System.out.println(dogs);
	    //delete the previous owner of the selected dog
	    for (Dog dog : dogs) {
	        dog.setOwner(null); 
	    }
	    //set the dog
	    // assign the new owner for the dog
	    for (Dog dog : dogs) {
	        dog.setOwner(owner);
	    }
	    
	    dogRepo.saveAll(dogs);
	    
	    
	    arrggsssss the assignment document not telling me that if I can override owner or not 
	    this takes a lot of time argsssssss
	*/    
	    //save the owner
	    for (Dog dog : dogs) {
	        dog.setOwner(owner);
	    }
	    
	    dogRepo.saveAll(dogs);
	    
	    owner.setDogs(dogs);
	    ownerRepo.save(owner);
	    return "redirect:/assignOwnerToDog";
	}
	

	
}
