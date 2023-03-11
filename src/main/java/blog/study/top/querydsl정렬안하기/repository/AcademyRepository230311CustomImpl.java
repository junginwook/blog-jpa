package blog.study.top.querydsl정렬안하기.repository;

import static blog.study.top.entity.QAcademy.*;

import blog.study.top.entity.Academy;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@RequiredArgsConstructor
public class AcademyRepository230311CustomImpl implements AcademyRepository230311Custom {

	private final JPAQueryFactory queryFactory;

	public List<Integer> getGroupOneWithOrder() {
		return queryFactory.select(Expressions.ONE)
				.from(academy)
				.groupBy(academy.name)
				.fetch();
	}

	public List<Integer> getGroupOneWithOutOrder() {
		return queryFactory.select(Expressions.ONE)
				.from(academy)
				.groupBy(academy.name)
				.orderBy(OrderByNull.DEFAULT)
				.fetch();
	}
}
