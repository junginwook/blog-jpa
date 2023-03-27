package blog.study.top.jpa_entity_select에서_update_쿼리_발생할_경우.repository;

import blog.study.top.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository230325 extends JpaRepository<Order, Long> {
	List<Order> findAllByOrderNo(String orderNo);
}
