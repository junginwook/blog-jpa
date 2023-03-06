package blog.study.top.엔플러스일문제.service;

import blog.study.top.entity.Academy;
import blog.study.top.entity.Subject;
import blog.study.top.엔플러스일문제.repository.AcademyRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class AcademyServiceTest {

	@Autowired
	private AcademyRepository academyRepository;
	@Autowired
	private AcademyService academyService;


	@BeforeEach
	public void setup() {
		List<Academy> academies = new ArrayList<>();

		for(int i=0;i<10;i++){
			Academy academy = Academy.builder()
					.name("강남스쿨"+i)
					.build();

			academy.addSubject(Subject.builder().name("자바웹개발" + i).build());
			academy.addSubject(Subject.builder().name("파이썬자동화" + i).build());
			academies.add(academy);
		}

		academyRepository.saveAll(academies);
	}

	@Test
	public void 엔플러스일조회() throws Exception {
		//given
		List<String> subjectNames = academyService.findAllSubjectNames();
	}

	@Test
	public void fetch조인으로가져오기() throws Exception {
		//given
		List<Academy> academies = academyRepository.findAllByFetchJoin();

		System.out.println("size: " + academies.size());

		for (Academy academy: academies) {
			System.out.println("academy: " + academy.getName() + "/sujects: " + academy.getSubjects().get(0).getName() + "/" + academy.getSubjects().get(1).getName());
		}
	}

	@Test
	public void entityGraph로가져오기() throws Exception {
		//given
		List<Academy> academies = academyRepository.findAllByEntityGraph();

		System.out.println("size: " + academies.size());

		for (Academy academy: academies) {
			System.out.println("academy: " + academy.getName() + "/sujects: " + academy.getSubjects().get(0).getName() + "/" + academy.getSubjects().get(1).getName());
		}
	}
}

