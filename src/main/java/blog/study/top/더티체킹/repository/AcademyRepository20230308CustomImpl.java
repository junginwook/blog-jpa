package blog.study.top.더티체킹.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class AcademyRepository20230308CustomImpl implements AcademyRepository20230308Custom {

	@Autowired
	private final JPAQueryFactory queryFactory;
}
