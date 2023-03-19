package blog.study.top.페이징성능개선하기NoOffset사용.repository;

import static blog.study.top.entity.QBook.*;

import blog.study.top.entity.Book;
import io.micrometer.common.util.StringUtils;

import blog.study.top.페이징성능개선하기NoOffset사용.dto.BookPaginationDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository230318Querydsl extends QuerydslRepositorySupport {

	private JPAQueryFactory queryFactory;

	public BookRepository230318Querydsl(JPAQueryFactory queryFactory) {
		super(Book.class);
		this.queryFactory = queryFactory;
	}

	public List<BookPaginationDto> paginationLegacy(String name, int pageNo, int pageSize) {
		return queryFactory
				.select(Projections.fields(BookPaginationDto.class,
						book.id.as("bookId"),
						book.name,
						book.bookNo)
				)
				.from(book)
				.where(
						book.name.like(name + "%")
				)
				.orderBy(book.id.desc())
				.limit(pageSize)
				.offset(pageNo * pageSize)
				.fetch();
	}

	public List<BookPaginationDto> paginationNoOffset(Long bookId, String name, int pageSize) {
		return queryFactory
				.select(Projections.fields(BookPaginationDto.class,
						book.id.as("bookId"),
						book.name,
						book.bookNo)
				)
				.from(book)
				.where(
						ltBookId(bookId),
						book.name.like(name + "%")
				)
				.orderBy(book.id.desc())
				.limit(pageSize)
				.fetch();
	}

	private BooleanExpression ltBookId(Long bookId) {
		if (bookId == null) {return null;}
		return book.id.lt(bookId);
	}
}
