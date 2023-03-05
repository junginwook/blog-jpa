package blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.testconfig;

import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.repository.ParentRepositoryCustom;
import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.repository.ParentRepositoryCustomImpl;
import blog.study.top.config.QuerydslConfiguration;
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
	public ParentRepositoryCustom parentRepositoryCustom() {
		return new ParentRepositoryCustomImpl(queryFactory);
	}
}
