package blog.study.top.jpa에서대량의데이터를삭제할때주의해야할점.repository;

import blog.study.top.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomRepository extends JpaRepository<Customer, Long> {

	@Modifying
	@Transactional
	long deleteByIdIn(List<Long> ids);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("delete from Customer c where c.id in :ids")
	void deleteAllByIdInQuery(@Param("ids") List<Long> ids);
}
