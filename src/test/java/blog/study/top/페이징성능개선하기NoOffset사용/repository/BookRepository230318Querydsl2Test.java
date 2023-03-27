package blog.study.top.페이징성능개선하기NoOffset사용.repository;

import static org.junit.jupiter.api.Assertions.*;

import blog.study.top.config.QuerydslConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({QuerydslConfiguration.class, BookRepository230318Querydsl.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class BookRepository230318Querydsl2Test {

	@Autowired
	private BookRepository230318Querydsl bookRepository230318Querydsl;

	/**
	 * no offset 방식으로 조회시 페이지 번호가 커지더라도 성능 하락에 영향이 없다.
	 * 단, 기존의 페이징 방식으로 조회해야 하는 경우에는 limit offset 방식으로 쿼리를 작성해야 한다.
	 * 그리고 편의성 입장에서 한 번에 많은 페이지를 건너띌 수 없다.
	 */
	@DisplayName("페이징시 noOffset 사용 - bookId가 null이 아닐 경우")
	@Test
	void testPaginationNoOffsetBuilder() {
		bookRepository230318Querydsl.paginationNoOffset(1L, "book", 10);
	}

	@DisplayName("페이징시 noOffset 사용 - bookId가 null일 경우")
	@Test
	void testPaginationNoOffsetBuilderWithNull() {
		bookRepository230318Querydsl.paginationNoOffset(null, "book", 10);
	}

	@DisplayName("기존 limit, offset 기반의 페이징 방식")
	@Test
	void testLegacyPagination() {
		bookRepository230318Querydsl.paginationLegacy("book", 1, 10);
	}
}