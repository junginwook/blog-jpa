package blog.study.top.jpa에서대량의데이터를삭제할때주의해야할점.repository;

import static org.assertj.core.api.Assertions.assertThat;

import blog.study.top.entity.Customer;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CustomRepositoryTest {

	@Autowired
	private CustomRepository customRepository;

	@BeforeEach
	public void set() {
		for(int i = 0; i<10; i++) {
			customRepository.save(new Customer(i + "님"));
		}
	}

	/**
	 * derived delete query를 사용하게 되면 내부적으로
	 * jpaQueryExecution 에서 처리하게 되는데,
	 * in query로 엔티티를 모두 조회한 이후에
	 * 하나씩 엔티티를 삭제한다. 삭제할 엔티티가 많아지면 성능 하락
	 */
	@DisplayName("derived query 로 삭제")
	@Test
	void testDeleteDerivedQuery() {
		customRepository.deleteByIdIn(Arrays.asList(1L, 2L, 3L));

		assertThat(customRepository.findAll().size()).isEqualTo(7);
	}

	/**
	 * @Query 어노테이션으로 jpql 만들시
	 * in 쿼리로 효율적으로 처리하게 된다.
	 */
	@DisplayName("jpql 로 삭제")
	@Test
	void testJpqlDelete() {
		customRepository.deleteAllByIdInQuery(Arrays.asList(1L, 2L, 3L));

		assertThat(customRepository.findAll().size()).isEqualTo(7);
	}
}