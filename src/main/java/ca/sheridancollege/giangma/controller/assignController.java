package ca.sheridancollege.giangma.controller;

import java.util.ArrayList;
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
	
	
	
	

	
	
	
	
	
	//select judge button 
	@PostMapping("/selectJudge")
	public String selectJudge(@RequestParam int judgeId, Model model) {
	    Judge selectedJudge = judgeRepo.findById(judgeId).orElse(null);

	    
	    
	 // get all dogs 
	    List<Dog> allDogs = dogRepo.findAll();

	    List<Dog> unassignedDogs = new ArrayList<>();

	    // loop through each dog to check if it is assigned to the selected judge
	    for (Dog dog : allDogs) {
	        
	        List<Judge> assignedJudges = dog.getJudges();

	        // if the dog has no judges, it is unassigned and should be added to the list
	        if (assignedJudges == null || assignedJudges.isEmpty()) {
	            unassignedDogs.add(dog);
	            continue;
	        }

	        boolean isAssigned = false;

	        // check if each judge assigned to the dog
	        for (Judge judge : assignedJudges) {
	            if (judge.getId().equals(judgeId)) {
	                isAssigned = true; 
	                break;
	            }
	        }

	        // if the dog is not assigned to the selected judge, add it to the list
	        if (!isAssigned) {
	            unassignedDogs.add(dog);
	        }
	    }

	    
	    

	    model.addAttribute("judgeList", judgeRepo.findAll());
	    model.addAttribute("dogList", unassignedDogs);
	    model.addAttribute("selectedJudge", selectedJudge);

	    return "assignDogToSelectedJudge.html";
	}

	
	
	
	
	@PostMapping("/assignJudgeToDog")
	public String processAssignJudgeToDog(@RequestParam int judgeId, @RequestParam List<Integer> dogIds) {
	    Judge judge = judgeRepo.findById(judgeId)
	    		.orElseThrow(() -> new RuntimeException("Judge not found"));
	    
	    List<Dog> newDogs = dogRepo.findAllById(dogIds);
	    List<Dog> currentDogs = new ArrayList<>(judge.getDogs()); // Copy existing assigned dogs

	    for (Dog dog : newDogs) {
	        if (!currentDogs.contains(dog)) { // Prevent duplicate assignments
	            currentDogs.add(dog);
	        }
	    }

	    judge.setDogs(currentDogs);
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
	public String procesAassignOwnerToDog(@RequestParam int ownerId, @RequestParam List<Integer> dogIds, Model model) {
	    
		
		//error handler
	    if (dogIds == null || dogIds.isEmpty()) {
	        model.addAttribute("error", "You must select at least one dog.");
	        return "assignOwnerToDog"; // Return the same form page with an error message
	    }
		
		
		
		Owner owner = ownerRepo.findById(ownerId)
	    		.orElseThrow(() -> new RuntimeException("Owner not found"));
	    
	    List<Dog> dogs = dogRepo.findAllById(dogIds);
	    

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
