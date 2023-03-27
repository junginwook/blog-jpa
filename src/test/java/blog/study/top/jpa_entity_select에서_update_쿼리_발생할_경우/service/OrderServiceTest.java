package blog.study.top.jpa_entity_select에서_update_쿼리_발생할_경우.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blog.study.top.QuerydslSelect에서상수사용하기.repository.BookRepository230318Querydsl2;
import blog.study.top.config.QuerydslConfiguration;
import blog.study.top.entity.Member;
import blog.study.top.entity.Order;
import blog.study.top.entity.Pay;
import blog.study.top.entity.Pay.PayDetail;
import blog.study.top.jpa_entity_select에서_update_쿼리_발생할_경우.repository.OrderRepository230325;
import blog.study.top.더티체킹.repository.AcademyRepository20230308;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class OrderServiceTest {

	@Autowired
	private OrderRepository230325 orderRepository230325;

	@Autowired
	private OrderService orderService;

	@BeforeEach
	void setup() {
		orderRepository230325.saveAll(
			List.of(
					new Order("1", new Member("1", "ssss"), Period.ZERO, new Pay("1", 1L, new PayDetail("ss", 2L))),
					new Order("1", new Member("1", "john"), Period.ZERO, new Pay("1", 1L, new PayDetail("ss", 2L)))
			)
		);

	}

	@Test
	@Transactional
	void test() {
//		Optional<Order> orderOptional = orderRepository230325.findById(1L);
//		System.out.println("orderNo: " + orderOptional.get().getOrderNo());
//		Order order = orderOptional.orElseThrow(
//				() -> new IllegalArgumentException()
//		);
//		order.changeOrderNo("2");
		orderService.showPayDetailAmount();
//		List<Order> allByOrderNo = orderRepository230325.findAllByOrderNo("1");
//		allByOrderNo.get(0).changeOrderNo("234");
//		assertThat(allByOrderNo.size()).isEqualTo(2);
	}
}