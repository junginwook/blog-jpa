package blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.dto.Family;
import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.Child;
import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.Parent;
import blog.study.top.config.QuerydslConfiguration;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(QuerydslConfiguration.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class ParentRepositoryTest {

	@Autowired
	private ParentRepository parentRepository;

	private Parent parent;
	private Child child1;
	private Child child2;

	@BeforeEach
	public void set() {
		parent = new Parent("parent");
		child1 = new Child("child1");
		child2 = new Child("child2");
		parent.addChild(child1);
		parent.addChild(child2);
	}

	@Test
	public void testExtendsQuerydslCustom() {
		String name = "inwook";
		parentRepository.save(new Parent(name));

		List<Parent> parents = parentRepository.findByName(name);

		assertThat(parents.size()).isEqualTo(1);
	}

	/**
	 * parent.children 과 leftJoin(parent.children, child) 이 각각 적용되어
	 * child 에 대한 Inner join 과 Outer Join 이 모두 발생한다.
	 */
	@DisplayName("OneToMany 연관관계에서 Left Outer Join을 할 경우 ")
	@Test
	public void testFindFamily() {
		parentRepository.save(parent);

		List<Family> family = parentRepository.findFamily();

		assertThat(family.size()).isEqualTo(1);
	}

	@DisplayName("Entity 직접 조회 + Left Outer Join")
	@Test
	public void testFindFamilyEntity() {
		parentRepository.save(parent);

		List<Family> family = parentRepository.findFamilyEntity();

		assertThat(family.size()).isEqualTo(1);
	}

	@DisplayName("Aggregation 결과 집합을 이용")
	@Test
	public void testFindFamilyAggregation() {
		parentRepository.save(parent);

		List<Family> family = parentRepository.findFamilyAggregation();

		assertThat(family).isEqualTo(1);
	}
}