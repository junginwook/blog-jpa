package blog.study.top.querydslSelect필드로Entity사용시주의사항.service;

import static org.junit.jupiter.api.Assertions.*;

import blog.study.top.entity.AdBond;
import blog.study.top.entity.AdItem;
import blog.study.top.entity.Customer;
import blog.study.top.entity.Shop;
import blog.study.top.oneToOne관계에서주테이블서브테이블.repository.CustomerRepository230317;
import blog.study.top.querydslSelect필드로Entity사용시주의사항.repository.AdBondRepository;
import blog.study.top.querydslSelect필드로Entity사용시주의사항.repository.AdItemRepository;
import blog.study.top.querydslSelect필드로Entity사용시주의사항.repository.ShopRepository230317;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdItemServiceTest {

	@Autowired
	private AdItemService adItemService;
	@Autowired
	private AdItemRepository adItemRepository;
	@Autowired
	private ShopRepository230317 shopRepository;
	@Autowired
	private CustomerRepository230317 customerRepository230317;

	@BeforeEach
	public void setup() {
		Shop shop = new Shop("shop1", "addr1");
		shop.setCustomer(new Customer("customer1"));
		Shop save = shopRepository.save(shop);
		Customer customer = save.getCustomer();

		AdItem adItem = new AdItem("1");
		adItem.setCustomer(customer);
		adItemRepository.save(adItem);
	}

	@Test
	void testCreateAdBond() {
		adItemService.createAdBond1(LocalDate.now(), LocalDate.now(), List.of("1", "2"));
	}

	@Test
	void testOneToOne() {
		adItemService.createAdBond2(List.of("1", "2"));
	}
}