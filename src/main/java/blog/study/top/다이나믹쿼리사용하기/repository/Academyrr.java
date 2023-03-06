package blog.study.top.다이나믹쿼리사용하기.repository;

import blog.study.top.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Academyrr extends JpaRepository<Academy, Long>, AcademyrrCustom {
}
