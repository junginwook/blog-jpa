package blog.study.top.exists쿼리성능개선.repository;

import static blog.study.top.entity.QAcademy.*;

import blog.study.top.entity.QAcademy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AcademyRepository230312CustomImpl implements AcademyRepository230312Custom {

	private final JPAQueryFactory queryFactory;

	/**
	 * from절 없이는 쿼리를 생성할 수 없다. 다른 방법으로 우회해야 한다.
	 *
	 * @param academyId
	 * @return
	 */
	@Override
	public boolean exist(Long academyId) {
		return queryFactory.select(
				queryFactory.selectOne()
						.from(academy)
						.where(academy.id.eq(academyId))
						.fetchAll().exists()
		).fetchOne();
	}

	@Override
	public boolean existImproved(Long academyId, String academyName) {
		Integer fetchOne = queryFactory.selectOne()
				.from(academy)
				.where(
						academy.id.eq(academyId),
						academy.name.eq(academyName)
				)
				.fetchFirst(); //limit 1
				//entity가 하나라도 조회되면 즉시 작업을 멈춘다.
				//count에 비해 성능이 더 좋다.
		return fetchOne != null;
	}
}
