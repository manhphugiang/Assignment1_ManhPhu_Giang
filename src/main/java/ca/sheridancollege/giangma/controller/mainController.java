package ca.sheridancollege.giangma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.giangma.bean.Dog;
import ca.sheridancollege.giangma.bean.Judge;
import ca.sheridancollege.giangma.bean.Owner;
import ca.sheridancollege.giangma.repository.DogRepository;
import ca.sheridancollege.giangma.repository.JudgeRepository;
import ca.sheridancollege.giangma.repository.OwnerRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class mainController {

	private DogRepository dogRepo;
	private JudgeRepository judgeRepo;
	private OwnerRepository ownerRepo;
		
	@GetMapping("/")
	public String goToHomePageForDaddy() {
		return "root.html";
	}
	
	@GetMapping("/view")
	public String viewPage(Model model) {
		model.addAttribute("ownerList", ownerRepo.findAll());
		model.addAttribute("dogList", dogRepo.findAll());
		model.addAttribute("judgeList", judgeRepo.findAll());

		return "view.html";
	}
	
	@GetMapping("/addOwner")
	private String goToAddOwnerPage() {
		return "addOwner.html";
	}
	
	@PostMapping("addOwner")
	private String processAddOwner(@ModelAttribute Owner owner) {
		ownerRepo.save(owner);
		return "redirect:/addOwner";
	}
	
	@GetMapping("/addDog")
	private String goToAddDogPage() {
		return "addDog.html";
	}
	
	@PostMapping("addDog")
	private String processAddDog(@ModelAttribute Dog dog) {
		dogRepo.save(dog);
		return "redirect:/addDog";
	}
	
	
	
	@GetMapping("/addJudge")
	private String goToAddJudgePage() {
		return "addJudge.html";
	}
	
	@PostMapping("addJudge")
	private String processAddDog(@ModelAttribute Judge judge) {
		judgeRepo.save(judge);
		return "redirect:/addJudge";
	}
	
	
	
	
	
	
	
	
	
	
}
