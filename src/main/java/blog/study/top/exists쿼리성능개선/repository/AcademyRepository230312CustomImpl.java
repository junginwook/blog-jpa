package blog.study.top.exists쿼리성능개선.repository;

import static blog.study.top.entity.QAcademy.*;

import blog.study.top.entity.QAcademy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AcademyRepository230312CustomImpl implements AcademyRepository230312Custom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Boolean exist(Long academyId) {
		return queryFactory.select(
				queryFactory.selectOne()
						.from(academy)
						.where(academy.id.eq(academyId))
						.fetchAll().exists()
		).fetchOne();
	}
}
