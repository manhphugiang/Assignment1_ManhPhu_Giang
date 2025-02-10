package ca.sheridancollege.giangma.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.bean.Judge;
@Repository
public interface JudgeRepository extends JpaRepositoryImplementation<Judge, Integer> {

}
