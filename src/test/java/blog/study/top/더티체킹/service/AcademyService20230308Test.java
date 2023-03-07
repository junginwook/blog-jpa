package blog.study.top.더티체킹.service;

import blog.study.top.entity.Academy;
import blog.study.top.엔플러스일문제.repository.AcademyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("더티체킹")
@SpringBootTest
class AcademyService20230308Test {
	@Autowired
	private AcademyService20230308 academyService20230308;
	@Autowired
	private AcademyRepository academyRepository;

	@BeforeEach
	public void set() {
		academyRepository.save(new Academy("academy1", "add"));
	}

	/**
	 * jpa의 기본 더티체킹은 모든 필드를 변경한다..
	 * 장점: 생성되는 쿼리가 같아 부트 실행시점에 미리 만들어서 재사용 가능
	 * 쿼리 재사용이 가능
	 *
	 * @DynamicUpdate를 사용하면 변경한 필드만 대응한다.
	 */
	@Test
	void testDirtyChecking() {
		academyService20230308.updateAcademyByDirtyChecking(1L);
	}
}