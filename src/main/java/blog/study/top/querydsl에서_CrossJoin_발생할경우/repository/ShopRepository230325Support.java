package blog.study.top.querydsl에서_CrossJoin_발생할경우.repository;

import static blog.study.top.entity.QAcademy.academy;
import static blog.study.top.entity.QCustomer.*;
import static blog.study.top.entity.QShop.*;

import blog.study.top.entity.Customer;
import blog.study.top.entity.QAcademy;
import blog.study.top.entity.QCustomer;
import blog.study.top.entity.QShop;
import blog.study.top.entity.Shop;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class ShopRepository230325Support extends QuerydslRepositorySupport {

	private final JPAQueryFactory queryFactory;

	public ShopRepository230325Support(JPAQueryFactory queryFactory) {
		super(Shop.class);
		this.queryFactory = queryFactory;
	}

	public List<Customer> crossJoin() {
		return queryFactory
				.select(customer)
				.from(customer, academy)
				.where(customer.name.eq(academy.name))
				.fetch();
	}

	public List<Customer> notCrossJoin() {
		return queryFactory
				.selectFrom(customer)
				.innerJoin(customer.shop)
				.where(customer.customerNo.gt(shop.shopNo))
				.fetchJoin()
				.fetch();
	}

	public List<Customer> ccc() {
		return queryFactory
				.select(customer)
				.from(customer)
				.innerJoin(customer.shop, shop)
				.fetch();
	}
}
