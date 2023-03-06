package blog.study.top.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Teacher {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private Long academyId;

	public Teacher(String name) {
		this.name = name;
	}
}
