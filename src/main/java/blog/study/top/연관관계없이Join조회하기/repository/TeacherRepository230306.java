package blog.study.top.연관관계없이Join조회하기.repository;

import blog.study.top.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository230306 extends JpaRepository<Teacher, Long>, TeacherRepository230306Custom {

}
