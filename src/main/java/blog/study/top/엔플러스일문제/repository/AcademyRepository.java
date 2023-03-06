package blog.study.top.엔플러스일문제.repository;


import blog.study.top.entity.Academy;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AcademyRepository extends JpaRepository<Academy, Long> {
	@Query("select a from Academy a join fetch a.subjects s")
	List<Academy> findAllByFetchJoin();

	@EntityGraph(attributePaths = "subjects")
	@Query("select a from Academy a")
	List<Academy> findAllByEntityGraph();
}
