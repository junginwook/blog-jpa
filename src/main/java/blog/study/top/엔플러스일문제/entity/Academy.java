package blog.study.top.엔플러스일문제.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Academy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "academy")
	private List<Subject> subjects = new ArrayList<>();

	@Builder
	public Academy(String name, List<Subject> subjects) {
		this.name = name;
		if(subjects != null) {
			this.subjects = subjects;
		}
	}

	public void addSubject(Subject subject) {
		this.subjects.add(subject);
		subject.updateAcademy(this);
	}
}
