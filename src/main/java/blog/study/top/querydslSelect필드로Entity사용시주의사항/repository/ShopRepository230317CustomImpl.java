package blog.study.top.querydslSelect필드로Entity사용시주의사항.repository;

import static blog.study.top.entity.QAdItem.*;

import blog.study.top.entity.AdBond;
import blog.study.top.entity.QAdBond;
import blog.study.top.entity.QAdItem;
import blog.study.top.querydslSelect필드로Entity사용시주의사항.dto.AdBondDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShopRepository230317CustomImpl implements ShopRepository230317Custom {

	private final JPAQueryFactory queryFactory;

	/**
	 * 문제점
	 * 1. 조인
	 * customer의 id만 조회할 것인데도
	 * dto로 조회하면 자동으로 조인이 되면서 조회하게 되네 그러면서 모든 필드값도 조회하고
	 *
	 * 2. 1+n
	 * oneToOne 조회시 대상 테이블로 조회할 경우
	 *
	 * select
	 *         sum(a1_0.amount),
	 *         a1_0.tx_date,
	 *         a1_0.order_type,
	 *         c1_0.id,
	 *         c1_0.biz_no,
	 *         c1_0.ceo_name,
	 *         c1_0.customer_name,
	 *         c1_0.customer_no,
	 *         c1_0.name
	 *     from
	 *         ad_item a1_0
	 *     join
	 *         customer c1_0
	 *             on c1_0.id=a1_0.customer_id
	 *     where
	 *         a1_0.order_type in(?,?)
	 *         and a1_0.tx_date between ? and ?
	 *     group by
	 *         a1_0.order_type,
	 *         a1_0.tx_date,
	 *         a1_0.customer_id
	 */
	@Override
	public List<AdBond> createAdBond(LocalDate startDate, LocalDate endDate, List<String> orderTypes) {
		return queryFactory
				.select(Projections.fields(AdBond.class,
						adItem.amount.sum().as("amount"),
						adItem.txDate,
						adItem.orderType,
						adItem.customer)
				)
				.from(adItem)
				.where(adItem.orderType.in(orderTypes))
				.groupBy(adItem.orderType, adItem.txDate, adItem.customer)
				.fetch();
	}


	@Override
	public List<AdBondDto> createAdBondAdvanced(List<String> orderTypes) {
		return queryFactory
				.select(Projections.fields(AdBondDto.class,
						adItem.amount.sum().as("amount"),
						adItem.txDate,
						adItem.orderType,
						adItem.customer.id.as("customerId"))
				)
				.from(adItem)
				.where(adItem.orderType.in(orderTypes))
				.groupBy(adItem.orderType, adItem.txDate, adItem.customer)
				.fetch();
	}
}
