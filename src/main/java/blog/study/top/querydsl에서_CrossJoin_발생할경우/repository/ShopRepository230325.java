package blog.study.top.querydsl에서_CrossJoin_발생할경우.repository;

import blog.study.top.entity.Customer;
import blog.study.top.entity.Shop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShopRepository230325 extends JpaRepository<Shop, Long> {

	@Query("SELECT c "
			+ "FROM Customer c "
			+ "WHERE c.customerNo > c.shop.shopNo")
	List<Customer> crossJoin();

	@Query("SELECT c "
			+ "FROM Customer c "
			+ "INNER JOIN c.shop s "
			+ "WHERE c.customerNo > s.shopNo")
	List<Customer> notCrossJoin();
}
