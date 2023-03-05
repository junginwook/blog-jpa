package blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.repository;

import static blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.QParent.*;

import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.Parent;
import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.QParent;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

/**
 * 이렇게 하면 항상 2개의 의존성을 받아야 한다는 단점이 있다.
 */
@Repository
public class ParentRepositorySupport extends QuerydslRepositorySupport {

	private final JPAQueryFactory queryFactory;

	public ParentRepositorySupport(JPAQueryFactory queryFactory) {
		super(Parent.class);
		this.queryFactory = queryFactory;
	}

	public List<Parent> findByName(String name) {
		return queryFactory
				.selectFrom(parent)
				.where(parent.name.eq(name))
				.fetch();
	}
}
