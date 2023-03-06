package blog.study.top.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "academy_no")
	private Integer academyNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "academy_id", foreignKey = @ForeignKey(name = "fk_student_academy"))
	private Academy academy;

	@Builder
	public Student(String name, int academyNo) {
		this.name = name;
		this.academyNo = academyNo;
	}

	public Student(String name, Integer academyNo, Academy academy) {
		this.name = name;
		this.academyNo = academyNo;
		this.academy = academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public void updateName(String name) {
		this.name = name;
	}
}
