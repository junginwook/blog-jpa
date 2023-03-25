package blog.study.top.querydsl에서_CrossJoin_발생할경우.repository;

import static org.junit.jupiter.api.Assertions.*;

import blog.study.top.config.QuerydslConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({QuerydslConfiguration.class, ShopRepository230325Support.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class ShopRepository230325Test {

	@Autowired
	private ShopRepository230325Support shopRepository230325Support;

	@Autowired
	private ShopRepository230325 shopRepository230325;

	@Test
	void testCrossJoinAtQuerydsl() {
		shopRepository230325Support.crossJoin();
	}

	@Test
	void testCrossJoinAtDataJpa() {
		shopRepository230325.crossJoin();
	}

	@Test
	void testNotCrossJoinAtQuerydsl() {
		shopRepository230325Support.notCrossJoin();
	}

	@Test
	void testNotCrossJoinAtDataJpa() {
		shopRepository230325.notCrossJoin();
	}

	@Test
	void ccc() {
		shopRepository230325Support.ccc();
	}
}