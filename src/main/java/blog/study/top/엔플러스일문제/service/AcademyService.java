package blog.study.top.엔플러스일문제.service;

import blog.study.top.entity.Academy;
import blog.study.top.엔플러스일문제.repository.AcademyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AcademyService {

	private final AcademyRepository academyRepository;

	@Transactional(readOnly = true)
	public List<String> findAllSubjectNames() {
		return extractSubjectNames(academyRepository.findAll());
	}

	@Transactional(readOnly = true)
	public List<String> findAllSubjectNamesByFetchJoin() {
		return extractSubjectNames(academyRepository.findAllByFetchJoin());
	}

	@Transactional(readOnly = true)
	public List<String> findAllSubjectNamesByEntityGraph() {
		return extractSubjectNames(academyRepository.findAllByEntityGraph());
	}

	private List<String> extractSubjectNames(List<Academy> academies) {
		log.info(">>>>모든 과목을 추출한다<<<<<");
		log.info("Academy Size: {}", academies.size());

		return academies.stream().map(academy -> academy.getSubjects().get(0).getName())
				.toList();
	}
}
