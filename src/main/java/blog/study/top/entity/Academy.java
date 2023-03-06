package blog.study.top.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class Academy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "match_key")
	private String matchKey;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column
	private String phoneNumber;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "academy")
	private List<Student> students = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="academy_id")
	private List<Subject> subjects = new ArrayList<>();

	public Academy(String name, String address) {
		this.name = name;
		this.address = address;
	}

	@Builder
	public Academy(String name, String address, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public void addStudent(List<Student> students) {
		for (Student student: students) {
			addStudent(student);
		}
	}

	public void addStudent(Student student) {
		this.students.add(student);
		student.setAcademy(this);
	}

	public void addSubject(Subject subject) {
		this.subjects.add(subject);
	}

	public void setMatchKey(String matchKey) {
		this.matchKey = matchKey;
	}
}