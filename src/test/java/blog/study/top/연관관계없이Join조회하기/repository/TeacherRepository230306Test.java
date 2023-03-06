package blog.study.top.연관관계없이Join조회하기.repository;

import static org.junit.jupiter.api.Assertions.*;

import blog.study.top.연관관계없이Join조회하기.QuerydslTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(QuerydslTestConfig.class)
@DataJpaTest
class TeacherRepository230306Test {

	@Autowired
	private TeacherRepository230306 teacherRepository230306;

	@Test
	void testFindAllAcademyTeacher() {
		teacherRepository230306.findAllAcademyTeacher();
	}

	@Test
	void testFindAcademyByStudent() {
		teacherRepository230306.findStudentByAcademy();
	}

	@Test
	void testFindStudentByAcademy() {
		teacherRepository230306.findAcademyByStudent();
	}
}