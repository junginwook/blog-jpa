package blog.study.top.querydsl정렬안하기.repository;

import blog.study.top.config.QuerydslConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(QuerydslConfiguration.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class AcademyRepository230311Test {

	@Autowired
	private AcademyRepository230311 academyRepository;

	@Test
	void groupByWithOrder() {
		academyRepository.getGroupOneWithOrder();
	}

	/**
	 * mysql 5 버전에서는 group by 시 자동으로 정렬을 해주었다.
	 * 따라서 데이터가 커질 경우 성능 저하가 발생할 가능성이 있었다.
	 * 따라서 order by null 을 통해 정렬을 하지 않도록 하여 성능 저하를 최적화하였다.
	 * 다만, mysql 8 버전에서는 이런 자동 정렬을 해주지 않는다.
	 */
	@Test
	void groupByWithoutOrder() {
		academyRepository.getGroupOneWithOutOrder();
	}
}