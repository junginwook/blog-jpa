package blog.study.top.jpa_entity_select에서_update_쿼리_발생할_경우.service;

import blog.study.top.entity.Order;
import blog.study.top.jpa_entity_select에서_update_쿼리_발생할_경우.repository.OrderRepository230325;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {
	private final OrderRepository230325 orderRepository;

	/**
	 * Jpa에서 더티 체킹시 스냅샷에 저장된 엔티티와 비교를 할때
	 * entity 자체의 equals 메서드를 사용하지 않는다!!
	 * 각 entity의 필드 값들의 equals 메서드를 이용해 변경 여부를 체크한다.
	 */
	@Transactional
	public void showPayDetailAmount() {
		List<Order> allByOrderNo = orderRepository.findAllByOrderNo("1");
		allByOrderNo.get(0).setCCCDate(LocalDate.now().plusDays(2));
	}
}
