package blog.study.top.연관관계없이Join조회하기;

import blog.study.top.config.QuerydslConfiguration;
import blog.study.top.연관관계없이Join조회하기.repository.TeacherRepository230306Custom;
import blog.study.top.연관관계없이Join조회하기.repository.TeacherRepository230306CustomImpl;
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
	public TeacherRepository230306Custom teacherRepository230306Custom() {
		return new TeacherRepository230306CustomImpl(queryFactory);
	}
}
