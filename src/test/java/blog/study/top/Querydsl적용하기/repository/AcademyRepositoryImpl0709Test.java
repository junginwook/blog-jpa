package blog.study.top.Querydsl적용하기.repository;


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
class AcademyRepositoryImpl0709Test {

	@Autowired
	private AcademyRepository0709 academyRepository0709;

	@Test
	void name() {
		academyRepository0709.findByName("academy");
	}
}