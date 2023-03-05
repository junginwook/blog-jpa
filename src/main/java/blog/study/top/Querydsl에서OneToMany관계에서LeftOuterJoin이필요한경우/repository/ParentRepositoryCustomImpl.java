package blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.repository;

import static blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.QParent.*;

import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.Parent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParentRepositoryCustomImpl implements ParentRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Parent> findByName(String name) {
		return queryFactory
				.selectFrom(parent)
				.where(parent.name.eq(name))
				.fetch();
	}
}
