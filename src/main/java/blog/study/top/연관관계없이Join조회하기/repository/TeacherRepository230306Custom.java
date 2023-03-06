package blog.study.top.연관관계없이Join조회하기.repository;

import blog.study.top.entity.Academy;
import blog.study.top.entity.Student;
import blog.study.top.연관관계없이Join조회하기.dto.AcademyTeacher;
import java.util.List;

public interface TeacherRepository230306Custom {
	List<AcademyTeacher> findAllAcademyTeacher();

	List<Student> findStudentByAcademy();

	List<Academy> findAcademyByStudent();
}
