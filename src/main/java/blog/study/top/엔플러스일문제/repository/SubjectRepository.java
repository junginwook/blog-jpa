package blog.study.top.엔플러스일문제.repository;


import blog.study.top.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
