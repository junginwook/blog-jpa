package blog.study.top.exists쿼리성능개선.repository;

import static org.assertj.core.api.Assertions.assertThat;

import blog.study.top.config.QuerydslConfiguration;
import blog.study.top.entity.Academy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(QuerydslConfiguration.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class AcademyRepository230312Test {

	@Autowired
	private AcademyRepository230312 academyRepository230312;

	@Test
	void testExistByName() {
		final String academyName = "academy1";
		final String academyAddress = "address1";
		academyRepository230312.save(new Academy(academyName, academyAddress));

		final boolean isExist = academyRepository230312.existsAcademyByName(academyName);

		assertThat(isExist).isTrue();
	}
}