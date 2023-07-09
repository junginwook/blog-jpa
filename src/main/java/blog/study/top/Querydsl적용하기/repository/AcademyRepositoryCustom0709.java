package blog.study.top.Querydsl적용하기.repository;

import blog.study.top.entity.Academy;
import java.util.List;

public interface AcademyRepositoryCustom0709 {
	List<Academy> findByName(String name);
}
