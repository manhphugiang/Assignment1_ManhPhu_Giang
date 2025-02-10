package ca.sheridancollege.giangma.bootstrap;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.giangma.bean.Dog;
import ca.sheridancollege.giangma.bean.Judge;
import ca.sheridancollege.giangma.bean.Owner;
import ca.sheridancollege.giangma.repository.DogRepository;
import ca.sheridancollege.giangma.repository.JudgeRepository;
import ca.sheridancollege.giangma.repository.OwnerRepository;
import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final DogRepository dogRepo;
    private final OwnerRepository ownerRepo;
    private final JudgeRepository judgeRepo;

	@Override
	public void run(String... args) throws Exception {
	        Owner o1 = Owner.builder().firstName("Loki").lastName("Odinson").build();
	        Owner o2 = Owner.builder().firstName("Thor").lastName("Odinson").build();
	        Owner o3 = Owner.builder().firstName("Tony").lastName("Stark").build();
	        Owner o4 = Owner.builder().firstName("Bruce").lastName("Banner").build();
	        Owner o5 = Owner.builder().firstName("Wade").lastName("Wilson").build();
	        Owner o6 = Owner.builder().firstName("Black").lastName("Widow").build();
	        
	        ownerRepo.save(o1);
	        ownerRepo.save(o2);
	        ownerRepo.save(o3);
	        ownerRepo.save(o4);
	        ownerRepo.save(o5);
	        ownerRepo.save(o6);

	        Dog d1 = Dog.builder().dogName("Loki").owner(o1).build();
	        Dog d2 = Dog.builder().dogName("Thor").owner(o1).build();
	        Dog d3 = Dog.builder().dogName("Ironman").owner(o2).build();
	        Dog d4 = Dog.builder().dogName("Hulk").owner(o2).build();
	        Dog d5 = Dog.builder().dogName("Deadpool").owner(o3).build();
	        Dog d6 = Dog.builder().dogName("Alivepool").owner(o3).build();
	        Dog d7 = Dog.builder().dogName("Spidey").owner(o4).build();
	        Dog d8 = Dog.builder().dogName("Strange").owner(o4).build();
	        Dog d9 = Dog.builder().dogName("Rocket").owner(o5).build();
	        Dog d10 = Dog.builder().dogName("Groot").owner(o5).build();
	        Dog d11 = Dog.builder().dogName("Vision").owner(o6).build();
	        Dog d12 = Dog.builder().dogName("Wanda").owner(o6).build();
	        
	        
	        
	        
	        dogRepo.save(d1);
	        dogRepo.save(d2);
	        dogRepo.save(d3);
	        dogRepo.save(d4);
	        dogRepo.save(d5);
	        dogRepo.save(d6);
	        dogRepo.save(d7);
	        dogRepo.save(d8);
	        dogRepo.save(d9);
	        dogRepo.save(d10);
	        dogRepo.save(d11);
	        dogRepo.save(d12);
	        

	        
	        Judge j1 = Judge.builder().judgeName("Nick Fury").dogs(Arrays.asList(d1, d2, d3, d4)).build();
	        Judge j2 = Judge.builder().judgeName("Stan Lee").dogs(Arrays.asList(d5, d6, d7, d8)).build();
	        Judge j3 = Judge.builder().judgeName("Aunt May").dogs(Arrays.asList(d9, d10, d11, d12)).build();
	       
	        judgeRepo.save(j1);
	        judgeRepo.save(j2);
	        judgeRepo.save(j3);
	        
	    }
	
}
