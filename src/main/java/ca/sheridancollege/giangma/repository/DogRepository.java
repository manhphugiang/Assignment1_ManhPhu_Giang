package ca.sheridancollege.giangma.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.bean.Dog;

@Repository
public interface DogRepository extends JpaRepositoryImplementation<Dog, Integer>{

}
