package blog.study.top;

import blog.study.top.entity.Member;
import blog.study.top.entity.Order;
import blog.study.top.entity.Pay;
import blog.study.top.entity.Pay.PayDetail;
import blog.study.top.jpa_entity_select에서_update_쿼리_발생할_경우.repository.OrderRepository230325;
import blog.study.top.jpa_entity_select에서_update_쿼리_발생할_경우.service.OrderService;
import java.time.Period;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BaseController {

	private final OrderService orderService;

	private final OrderRepository230325 orderRepository230325;

	@RequestMapping("/")
	public String con() {
		orderService.showPayDetailAmount();

		return "ss";
	}

	@RequestMapping("/save")
	public String save() {
		orderRepository230325.saveAll(
				List.of(
						new Order("1", new Member("1", "ssss"), Period.ZERO, new Pay("1", 1L, new PayDetail("ss", 2L))),
						new Order("1", new Member("1", "john"), Period.ZERO, new Pay("1", 1L, new PayDetail("ss", 2L)))
				)
		);

		return "saved";
	}
}
