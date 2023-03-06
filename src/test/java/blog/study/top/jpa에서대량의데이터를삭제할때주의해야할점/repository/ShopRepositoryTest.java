package blog.study.top.jpa에서대량의데이터를삭제할때주의해야할점.repository;

import static org.assertj.core.api.Assertions.assertThat;

import blog.study.top.entity.Item;
import blog.study.top.entity.Shop;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@ActiveProfiles("test")
class ShopRepositoryTest {

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private ItemRepository itemRepository;

	private final List<Long> SHOP_ID_LIST = new ArrayList<>();

	@BeforeEach
	public void set() {
		for (long i = 1; i <= 3; i++) {
			SHOP_ID_LIST.add(i);
		}
	}

	@DisplayName("derived query 를 이용해서 삭제 - 부모 & 자식")
	@Test
	public void deleteByDerivedQuery() {
		createShopAndItem();
		System.out.println("---------------------------");

		shopRepository.deleteAllByIdIn(SHOP_ID_LIST);
		assertThat(shopRepository.findAll().size()).isEqualTo(7);
	}

	@DisplayName("@Query 로 Id 리스트를 이용해서 삭제 - 부모 & 자식")
	@Test
	public void deleteByQueryJpql() {
		createShopAndItem();
		System.out.println("---------------------------");

		shopRepository.deleteAllByIdInQuery(SHOP_ID_LIST);
		itemRepository.deleteAllByIdInQuery(SHOP_ID_LIST);
		assertThat(shopRepository.findAll().size()).isEqualTo(7);
	}

	private void createShopAndItem() {
		for (int i = 0; i < 10; i++) {
			Shop shop = new Shop("우아한서점" + i, "우아한 동네" + i);

			for (int j = 0; j < 3; j++) {
				shop.addItem(new Item("IT책" + j, j * 1000L));
			}

			shopRepository.save(shop);
		}
	}
}