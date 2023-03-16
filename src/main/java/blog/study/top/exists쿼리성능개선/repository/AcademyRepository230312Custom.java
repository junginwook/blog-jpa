package blog.study.top.exists쿼리성능개선.repository;

public interface AcademyRepository230312Custom {
	boolean exist(Long academyId);
	boolean existImproved(Long academyId, String academyName);
}
