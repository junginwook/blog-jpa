package blog.study.top.다이나믹쿼리사용하기;

import blog.study.top.config.QuerydslConfiguration;
import blog.study.top.다이나믹쿼리사용하기.repository.AcademyrrCustom;
import blog.study.top.다이나믹쿼리사용하기.repository.AcademyrrCustomImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(QuerydslConfiguration.class)
@TestConfiguration
public class QuerydslTestConfig {

	@Autowired
	private JPAQueryFactory queryFactory;

	@Bean
	public AcademyrrCustom academyrrCustom() {
		return new AcademyrrCustomImpl(queryFactory);
	}
}
