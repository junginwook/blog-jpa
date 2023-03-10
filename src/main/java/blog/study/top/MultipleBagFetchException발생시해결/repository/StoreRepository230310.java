package blog.study.top.MultipleBagFetchException발생시해결.repository;

import blog.study.top.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreRepository230310 extends JpaRepository<Store, Long>, StoreRepository230310Custom{

	@Query("SELECT s "
			+ "FROM Store s "
			+ "join fetch s.products "
			+ "join fetch s.employees")
	List<Store> findAllByFetchJoin();
}
