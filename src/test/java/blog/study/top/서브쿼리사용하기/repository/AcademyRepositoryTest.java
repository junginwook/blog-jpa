package blog.study.top.서브쿼리사용하기.repository;

import static org.assertj.core.api.Assertions.assertThat;

import blog.study.top.config.QuerydslConfiguration;
import blog.study.top.entity.Academy;
import blog.study.top.entity.Student;
import blog.study.top.서브쿼리사용하기.QuerydslTestConfig;
import blog.study.top.서브쿼리사용하기.dto.StudentCount;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Import(QuerydslConfiguration.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class AcademyRepositoryTest {
	@Autowired
	private AcademySubQueryRepositoryImpl academyRepositoryCustom;

	@Autowired
	private AcademySubQueryRepository academyRepository;
	private Academy academy1;
	private Academy academy2;
	private String academyName1 = "name1";
	private String academyName2 = "name2";

	@BeforeEach
	public void set() {
		//given
		academy1 = Academy.builder()
				.address("address1")
				.name(academyName1)
				.build();

		academy1.addStudent(Arrays.asList(
				new Student("student1", 1),
				new Student("student2", 2)
		));

		academy2 = Academy.builder()
				.address("address2")
				.name(academyName2)
				.build();
		academy2.addStudent(Arrays.asList(
				new Student("student1", 1),
				new Student("student2", 2),
				new Student("student3", 3)
		));
	}

	@Test
	public void testSelectSubQuery() {

		academyRepository.saveAll(Arrays.asList(academy1, academy2));

		//when
		List<StudentCount> studentCount = academyRepositoryCustom.findAllStudentCount();

		assertThat(studentCount.get(0).getAcademyName()).isEqualTo(academyName1);
		assertThat(studentCount.get(1).getAcademyName()).isEqualTo(academyName2);
	}

	@Test
	public void testWhereSubQuery() {

		academyRepository.saveAll(Arrays.asList(academy1, academy2));

		//when
		List<Academy> result = academyRepositoryCustom.findAllByStudentId(3);

		assertThat(result.size()).isEqualTo(1);
	}
}