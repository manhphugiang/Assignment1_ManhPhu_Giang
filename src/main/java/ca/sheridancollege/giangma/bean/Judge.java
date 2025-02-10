package ca.sheridancollege.giangma.bean;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Judge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String judgeName;
	@ManyToMany
	@JoinTable(
			name= "judge_dog",
			joinColumns = @JoinColumn(name= "judge_id"),
			inverseJoinColumns= @JoinColumn(name="dog_id")
			)
	private List<Dog> dogs;
}
