package blog.study.top.oneToOne관계에서주테이블서브테이블.service;

import blog.study.top.config.QuerydslConfiguration;
import blog.study.top.entity.Customer;
import blog.study.top.entity.Shop;
import blog.study.top.oneToOne관계에서주테이블서브테이블.repository.CustomerRepository230317;
import blog.study.top.oneToOne관계에서주테이블서브테이블.repository.ShopRepository23031;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(QuerydslConfiguration.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class CustomerShopServiceTest {

	@Autowired
	private ShopRepository23031 shopRepository23031;
	@Autowired
	private CustomerRepository230317 customerRepository230317;

	@BeforeEach
	public void setup() {
		Shop shop1 = new Shop("shop1", "address1");
		shop1.setCustomer(new Customer("customer1"));

		Shop shop2 = new Shop("shop2", "address2");
		shop2.setCustomer(new Customer("customer2"));

		shopRepository23031.saveAll(List.of(shop1, shop2));
	}

	@DisplayName("OneToOne 사용시 주테이블에서 조회")
	@Test
	void testFindAllShops() {
		shopRepository23031.findAllShop();
	}

	/**
	 * 테이블을 조회할 때, @OneToOne 연관관계로 연결되어 있는 경우에는 대상테이블에서 조회를 하면 1+n 문제가
	 * 발생한다.
	 * 해당 문제는 프록시 기능의 한계로 인해 발생한다.
	 * 대상 테이블 입장에서는 외래키가 없기 때문에 연결되어 있는 주테이블이 Null인지 아닌지 조회해 보기 전까지는 알 수 없다.
	 * 프록시를 만들기 위해 주테이블의 null 여부를 파악하기 위해 1+n 이 발생하게 된다.
	 */
	@DisplayName("OneToOne 사용시 대상테이블에서 조회 - 1+N 문제가 발생한다")
	@Test
	void testFindAllCustomers() {
		List<Customer> allCustomer = customerRepository230317.findAllCustomer();
	}

	@DisplayName("OneToOne 사용시 대상테이블에서 조회 - Fetch join 으로 문제 해결")
	@Test
	void testFindAllCustomersByFetchJoin() {
		List<Customer> allCustomerByFetchJoin = customerRepository230317.findAllCustomerByFetchJoin();
	}
}