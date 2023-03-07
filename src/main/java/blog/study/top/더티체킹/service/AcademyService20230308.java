package blog.study.top.더티체킹.service;

import blog.study.top.entity.Academy;
import blog.study.top.더티체킹.repository.AcademyRepository20230308;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AcademyService20230308 {

	private final AcademyRepository20230308 academyRepository20230308;

	@Transactional
	public void updateAcademyByDirtyChecking(Long academyId) {
		Optional<Academy> academyOptional = academyRepository20230308.findById(academyId);
		Academy academy = academyOptional.orElseThrow(
				() -> new IllegalArgumentException()
		);

		academy.setMatchKey("new matchKey");
	}
}
