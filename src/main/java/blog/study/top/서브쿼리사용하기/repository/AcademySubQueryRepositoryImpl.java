package blog.study.top.서브쿼리사용하기.repository;

import static blog.study.top.entity.QAcademy.academy;
import static blog.study.top.entity.QStudent.student;
import static com.querydsl.core.types.ExpressionUtils.count;

import blog.study.top.entity.Academy;
import blog.study.top.서브쿼리사용하기.dto.StudentCount;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AcademySubQueryRepositoryImpl {

	private final JPAQueryFactory queryFactory;

	public List<StudentCount> findAllStudentCount() {
		return queryFactory
				.select(Projections.fields(StudentCount.class,
						academy.name.as("academyName"),
						ExpressionUtils.as(
								JPAExpressions.select(count(student))
										.from(student)
										.where(student.academy.eq(academy)),
								"studentCount")
				))
				.from(academy)
				.fetch();
	}

	public List<Academy> findAllByStudentId(long studentId) {
		return queryFactory
				.selectFrom(academy)
				.where(academy.id.in(
						JPAExpressions
								.select(student.academy.id)
								.from(student)
								.where(student.id.eq(studentId))
				))
				.fetch();
	}
}
