package blog.study.top.Querydsl적용하기.repository;

import static blog.study.top.entity.QAcademy.*;

import blog.study.top.entity.Academy;
import blog.study.top.entity.QAcademy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AcademyRepositoryImpl0709 implements AcademyRepositoryCustom0709 {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Academy> findByName(String name) {
		return jpaQueryFactory.select(academy)
				.from(academy)
				.where(academy.name.eq(name))
				.fetch();
	}
}
