package blog.study.top.다이나믹쿼리사용하기.repository;

import static blog.study.top.entity.QAcademy.*;

import blog.study.top.entity.Academy;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AcademyrrCustomImpl implements AcademyrrCustom {

	private final JPAQueryFactory queryFactory;

	/**
	 * booleanBuilder 를 사용하여 동적쿼리 생성
	 * where의 조건을 한 번에 볼 수 없어서 아쉽다.
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @return
	 */
	@Override
	public List<Academy> findDynamicQuery(String name, String address, String phoneNumber) {

		BooleanBuilder builder = new BooleanBuilder();

		if(!StringUtils.isEmpty(name)) {
			builder.and(academy.name.eq(name));
		}

		if(!StringUtils.isEmpty(address)) {
			builder.and(academy.address.eq(address));
		}

		if(!StringUtils.isEmpty(phoneNumber)) {
			builder.and(academy.phoneNumber.eq(phoneNumber));
		}

		return queryFactory
				.selectFrom(academy)
				.where(builder)
				.fetch();
	}

	/**
	 * BooleanExpression을 사용하면 where에 여러 조건을
	 * 한번에 확인할 수 있다.
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @return
	 */
	@Override
	public List<Academy> findDynamicQueryAdvance(String name, String address, String phoneNumber) {
		return queryFactory
				.selectFrom(academy)
				.where(
						eqName(name),
						eqAddress(address),
						eqPhoneNumber(phoneNumber)
				)
				.fetch();
	}

	private BooleanExpression eqName(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		return academy.name.eq(name);
	}

	private BooleanExpression eqAddress(String address) {
		if (StringUtils.isEmpty(address)) {
			return null;
		}
		return academy.address.eq(address);
	}

	private BooleanExpression eqPhoneNumber(String phoneNumber) {
		if (StringUtils.isEmpty(phoneNumber)) {
			return null;
		}
		return academy.phoneNumber.eq(phoneNumber);
	}
}
