package blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.repository;

import static blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.QChild.*;
import static blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.QParent.*;
import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.dto.Family;
import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.Child;
import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.Parent;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class ParentRepositoryCustomImpl implements ParentRepositoryCustom {

	@Autowired
	private final JPAQueryFactory queryFactory;

	@Override
	public List<Parent> findByName(String name) {
		return queryFactory
				.selectFrom(parent)
				.where(parent.name.eq(name))
				.fetch();
	}

	@Override
	public List<Family> findFamily() {
		return queryFactory
				.select(Projections.fields(Family.class,
						parent.name,
						parent.children
				))
				.from(parent)
				.leftJoin(parent.children, child)
				.fetch();
	}

	@Override
	public List<Family> findFamilyEntity() {
		List<Parent> parents = queryFactory
				.selectFrom(parent)
				.leftJoin(parent.children, child)
				.fetch();

		System.out.println("size: " + parents.size());

		return parents.stream()
				.map(p -> new Family(p.getName(), p.getChildren()))
				.toList();
	}

	@Override
	public List<Family> findFamilyAggregation() {
		Map<String, List<Child>> transform = queryFactory
				.from(parent)
				.leftJoin(parent.children, child)
				.transform(groupBy(parent.name).as(list(child)));

		return transform.entrySet().stream()
				.map(entry -> new Family(entry.getKey(), entry.getValue()))
				.toList();
	}
}
