package blog.study.top.QuerydslSelect에서상수사용하기.repository;

import static blog.study.top.entity.QBook.*;

import blog.study.top.QuerydslSelect에서상수사용하기.dto.BookPageDto;
import blog.study.top.entity.Book;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository230318Querydsl2 extends QuerydslRepositorySupport {

	private final JPAQueryFactory queryFactory;

	public BookRepository230318Querydsl2(JPAQueryFactory queryFactory) {
		super(Book.class);
		this.queryFactory = queryFactory;
	}

	public List<BookPageDto> getBookPage(long bookNo, int pageNo) {
		return queryFactory
				.select(Projections.fields(BookPageDto.class,
						book.name,
						Expressions.constantAs(bookNo, book.bookNo)
						)
				)
				.from(book)
				.where(book.bookNo.eq(bookNo))
				.offset(pageNo)
				.limit(10)
				.fetch();
	}

	public List<BookPageDto> getBookPageWithDtoField(long bookNo, int pageNo) {
		return queryFactory
				.select(Projections.fields(BookPageDto.class,
						book.name,
						Expressions.constantAs(bookNo, book.bookNo),
						Expressions.as(Expressions.constant(pageNo), "pageNo")
				))
				.from(book)
				.where(book.bookNo.eq(bookNo))
				.offset(pageNo)
				.limit(10)
				.fetch();
	}

	public List<BookPageDto> getBookPageWithDtoFieldAdvanced(long bookNo, int pageNo) {
		return queryFactory
				.select(Projections.fields(BookPageDto.class,
						book.name,
						Expressions.asNumber(bookNo).as(book.bookNo),
						Expressions.asNumber(pageNo).as("pageNo")
				))
				.from(book)
				.where(book.bookNo.eq(bookNo))
				.offset(pageNo)
				.limit(10)
				.fetch();
	}
}
