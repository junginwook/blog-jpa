package blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.Parent;
import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.testconfig.QuerydslTestConfig;
import java.util.List;
import org.aspectj.lang.annotation.After;
import org.hibernate.annotations.Filter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Import(QuerydslTestConfig.class)
@DataJpaTest
class ParentRepositoryTest {

	@Autowired
	private ParentRepository parentRepository;

	@Test
	public void testExtendsQuerydslCustom() {
		String name = "inwook";
		parentRepository.save(new Parent(name));

		List<Parent> parents = parentRepository.findByName(name);

		assertThat(parents.size()).isEqualTo(1);
	}
}