package blog.study.top.exists쿼리성능개선.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import blog.study.top.config.QuerydslConfiguration;
import blog.study.top.entity.Academy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@Import(QuerydslConfiguration.class)
@AutoConfigureTestDatabase(replace = Replace.NONE) //property에 설정된 테스트 컨테이너를 사용하도록
@DataJpaTest
class AcademyRepository230312Test {

	@Autowired
	private AcademyRepository230312 academyRepository230312;

	private String academyName;
	private String academyAddress;
	private String academyPhoneNumber;
	@BeforeEach
	public void setup() {
		academyName = "academy1";
		academyAddress = "address1";
		academyPhoneNumber = "1234";
		academyRepository230312.save(new Academy(academyName, academyAddress, academyPhoneNumber));
	}

	@Test
	void testExistByName() {
		final boolean isExist = academyRepository230312.existsAcademyByNameAndAddressAndPhoneNumber(academyName, academyAddress, academyPhoneNumber);
		assertThat(isExist).isTrue();
	}

	/**
	 * from절이 없으면 에러
	 */
	@Test
	void testJPQLExist() {
		final Throwable t = catchThrowable(() -> academyRepository230312.exist(1L));
		assertThat(t)
				.isInstanceOf(InvalidDataAccessApiUsageException.class);
	}

	/**
	 * fetchFirst로 limit 1을 걸어서 하나라도 레코드가 조회되면
	 * 작업을 중단되도록 해야 한다.
	 *
	 * count는 exists에 비해 성능상 이슈가 있다.
	 * 그러나 @Query와 Querydsl에서는 select exists를 사용할 수가 없다.
	 * 그래서 select exist를 limit 1로 대체해서 사용해야 한다.
	 * 단 JpaRepository의 메서드 쿼리에선 내부적으로 Limit 1을 사용하고 있어 성능상 이슈가 없다.
	 * (그러나 조인등 조건 절이 복잡해지면 사용할 수 없다.)
	 */
	@Test
	void testCustomExist() {
		final boolean isExist = academyRepository230312.existImproved(1L, academyName);
		assertThat(isExist).isTrue();
	}
}