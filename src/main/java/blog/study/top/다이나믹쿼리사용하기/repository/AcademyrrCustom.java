package blog.study.top.다이나믹쿼리사용하기.repository;

import blog.study.top.entity.Academy;
import java.util.List;

public interface AcademyrrCustom {
	List<Academy> findDynamicQuery(String name, String address, String phoneNumber);
	List<Academy> findDynamicQueryAdvance(String name, String address, String phoneNumber);
}
