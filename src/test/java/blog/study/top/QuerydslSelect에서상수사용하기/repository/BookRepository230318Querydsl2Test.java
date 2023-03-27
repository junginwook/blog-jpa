package blog.study.top.QuerydslSelect에서상수사용하기.repository;

import static org.assertj.core.api.Assertions.assertThat;

import blog.study.top.QuerydslSelect에서상수사용하기.dto.BookPageDto;
import blog.study.top.config.QuerydslConfiguration;
import blog.study.top.entity.Book;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import({BookRepository230318Querydsl2.class, QuerydslConfiguration.class})
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class BookRepository230318Querydsl2Test {

	@Autowired
	private BookRepository230318Querydsl2 bookRepository230318Querydsl2;

	@Autowired
	private BookRepository230318 bookRepository230318;

	private long bookNo = 1;
	private int pageNo = 0;

	@BeforeEach
	void setUp() {
		//given
		for (int i = 1; i <= 10; i++) {
			bookRepository230318.save(Book.builder()
					.name("a" + i)
					.bookNo(bookNo)
					.build());
		}
	}

	/**
	 * bookNo는 상수로 들어가서 불필요한 칼럼인 bookNo가 빠진채로 조회된다.
	 */
	@DisplayName("entity에 있는 상수 추가")
	@Test
	void getBookPage() {
		List<BookPageDto> bookPage = bookRepository230318Querydsl2.getBookPage(bookNo, pageNo);

		assertThat(bookPage).hasSize(10);
		assertThat(bookPage.get(0).getBookNo()).isEqualTo(bookNo);
	}

	@DisplayName("entity에 없는 상수 추가")
	@Test
	void testGetBookPageWithDtoField() {
		List<BookPageDto> bookPageWithDtoField = bookRepository230318Querydsl2.getBookPageWithDtoField(bookNo, pageNo);

		assertThat(bookPageWithDtoField).hasSize(10);
		assertThat(bookPageWithDtoField.get(0).getBookNo()).isEqualTo(bookNo);
		assertThat(bookPageWithDtoField.get(0).getPageNo()).isEqualTo(pageNo);
	}
}