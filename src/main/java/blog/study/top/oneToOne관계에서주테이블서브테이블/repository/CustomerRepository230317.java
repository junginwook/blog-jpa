package blog.study.top.oneToOne관계에서주테이블서브테이블.repository;

import blog.study.top.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository230317 extends JpaRepository<Customer, Long> {
	@Query("SELECT c FROM Customer c")
	List<Customer> findAllCustomer();

	@Query("SELECT c FROM Customer c JOIN FETCH Shop s")
	List<Customer> findAllCustomerByFetchJoin();
}
