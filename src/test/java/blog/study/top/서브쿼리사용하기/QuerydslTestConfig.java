package blog.study.top.서브쿼리사용하기;

import blog.study.top.config.QuerydslConfiguration;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@Import(QuerydslConfiguration.class)
@TestConfiguration
public class QuerydslTestConfig {

	@Autowired
	private JPAQueryFactory queryFactory;
}
