package blog.study.top.MultipleBagFetchException발생시해결.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blog.study.top.MultipleBagFetchException발생시해결.repository.StoreRepository230310;
import blog.study.top.entity.Employee;
import blog.study.top.entity.Product;
import blog.study.top.entity.Store;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "spring.jpa.properties.hibernate.default_batch_fetch_size=1000")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class StoreService230310Test {

	@Autowired
	private StoreRepository230310 storeRepository230310;

	@Autowired
	private StoreService230310 storeService230310;

	@Test
	public void No_Repositor의배치사이즈() {
		Store store1 = new Store("서점", "서울시 강남구");
		store1.addProduct(new Product("책1_1", 10000L));
		store1.addProduct(new Product("책1_2", 20000L));
		store1.addEmployee(new Employee("직원1", LocalDate.now()));
		store1.addEmployee(new Employee("직원2", LocalDate.now()));
		storeRepository230310.save(store1);

		Store store2 = new Store("서점", "서울시 강남구");
		store2.addProduct(new Product("책2_1", 10000L));
		store2.addProduct(new Product("책2_2", 20000L));
		store2.addEmployee(new Employee("직원2_1", LocalDate.now()));
		store2.addEmployee(new Employee("직원2_2", LocalDate.now()));
		storeRepository230310.save(store2);

		long size = storeService230310.find();

		assertThat(size).isEqualTo(60000L);
	}

	@Test
	public void fetch조인으로OneToMany연관관계두개이상가져오기() {
		Store store1 = new Store("서점", "서울시 강남구");
		store1.addProduct(new Product("책1_1", 10000L));
		store1.addProduct(new Product("책1_2", 20000L));
		store1.addEmployee(new Employee("직원1", LocalDate.now()));
		store1.addEmployee(new Employee("직원2", LocalDate.now()));
		storeRepository230310.save(store1);

		Store store2 = new Store("서점", "서울시 강남구");
		store2.addProduct(new Product("책2_1", 10000L));
		store2.addProduct(new Product("책2_2", 20000L));
		store2.addEmployee(new Employee("직원2_1", LocalDate.now()));
		store2.addEmployee(new Employee("직원2_2", LocalDate.now()));
		storeRepository230310.save(store2);

		long size = storeService230310.findByFetchJoin();

		assertThat(size).isEqualTo(60000L);
	}
}