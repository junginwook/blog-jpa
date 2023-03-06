package blog.study.top.연관관계없이Join조회하기.repository;

import static blog.study.top.entity.QAcademy.*;
import static blog.study.top.entity.QStudent.student;
import static blog.study.top.entity.QTeacher.teacher;

import blog.study.top.entity.Academy;
import blog.study.top.entity.QAcademy;
import blog.study.top.entity.Student;
import blog.study.top.연관관계없이Join조회하기.dto.AcademyTeacher;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class TeacherRepository230306CustomImpl implements TeacherRepository230306Custom {

	@Autowired
	private final JPAQueryFactory queryFactory;

	/**
	 * querydsl에서 연관관계 없이 join을 이용할 수 있다.
	 * @return
	 */
	@Override
	public List<AcademyTeacher> findAllAcademyTeacher() {
		return queryFactory
				.select(Projections.fields(AcademyTeacher.class,
						academy.name.as("academyName"),
						teacher.name.as("teacherName")
				))
				.from(academy)
				.join(teacher).on(academy.id.eq(teacher.academyId))
				.fetch();
	}

	/**
	 * 연관관계를 이용하여 fetch join
	 * ManyToOne
	 * @return
	 */
	@Override
	public List<Student> findStudentByAcademy() {
		return queryFactory
				.selectFrom(student)
				.join(student.academy, academy)
				.fetchJoin()
				.fetch();
	}

	/**
	 * 연관관계를 이용하여 fetch join
	 * OneToMany
	 * @return
	 */
	@Override
	public List<Academy> findAcademyByStudent() {
		return queryFactory
			.selectFrom(academy)
			.join(academy.students, student)
			.fetchJoin()
			.fetch();
	}
}
