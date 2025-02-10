package ca.sheridancollege.giangma.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.giangma.bean.Owner;
@Repository
public interface OwnerRepository extends JpaRepositoryImplementation<Owner, Integer> {

}
