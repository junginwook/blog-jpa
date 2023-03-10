package blog.study.top.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by jojoldu@gmail.com on 2017. 7. 20.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Entity
@Getter
@NoArgsConstructor
public class Subject {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "academy_id", foreignKey = @ForeignKey(name = "FK_SUBJECT_ACADEMY"))
	private Academy academy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "FK_SUBJECT_TEACHER"))
	private Teacher teacher;

	@Builder
	public Subject(String name, Academy academy, Teacher teacher) {
		this.name = name;
		this.academy = academy;
		this.teacher = teacher;
	}

	public void updateAcademy(Academy academy){
		this.academy = academy;
	}
}
