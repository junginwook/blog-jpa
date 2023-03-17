package blog.study.top.oneToOne관계에서주테이블서브테이블.repository;

import blog.study.top.entity.Customer;
import blog.study.top.entity.Shop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShopRepository23031 extends JpaRepository<Shop, Long> {
	@Query("SELECT s FROM Shop s")
	List<Shop> findAllShop();
}
