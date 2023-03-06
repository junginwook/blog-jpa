package blog.study.top.다이나믹쿼리사용하기.repository;

import static org.junit.jupiter.api.Assertions.*;

import blog.study.top.다이나믹쿼리사용하기.QuerydslTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(QuerydslTestConfig.class)
@DataJpaTest
class AcademyrrTest {

	@Autowired
	private Academyrr academyrr;

	@Test
	void testFindDynamicQuery() {
		academyrr.findDynamicQuery("name", "address", "phoneNumber");
		academyrr.findDynamicQuery("name", "address", null);
		academyrr.findDynamicQuery("name", null, null);
	}

	@Test
	void testFindDynamicQueryAdvance() {
		academyrr.findDynamicQueryAdvance("name", "address", "phoneNumber");
		academyrr.findDynamicQueryAdvance("name", "address", null);
		academyrr.findDynamicQueryAdvance("name", null, null);
	}
}