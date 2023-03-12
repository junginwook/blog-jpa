package blog.study.top.exists쿼리성능개선.repository;

import blog.study.top.entity.Academy;
import blog.study.top.querydsl정렬안하기.repository.AcademyRepository230311;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("m1")
public interface AcademyRepository230312 extends JpaRepository<Academy, Long>, AcademyRepository230312Custom {
	boolean existsAcademyByName(String name);
}
