package blog.study.top.페이징성능개선하기_커버링인덱스사용.repository;

import static blog.study.top.entity.QBook.*;

import blog.study.top.entity.Book;
import blog.study.top.entity.QBook;
import blog.study.top.페이징성능개선하기_커버링인덱스사용.dto.BookPaginationDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class BookRepository230419Support extends QuerydslRepositorySupport {

	private JPAQueryFactory queryFactory;

	public BookRepository230419Support(JPAQueryFactory queryFactory) {
		super(Book.class);
		this.queryFactory = queryFactory;
	}

	/**
	 * limit, offset 방식은 페이지의 크기가 작을 경우에는 문제가 없지만,
	 * 페이지의 크기가 커지면 계속해서 random access 가 증가하기 때문에
	 * 비효율이 발생하게 된다.
	 *
	 * 따라서 커버링 인덱스를 이용해 limit, offset 에 대한 처리를 하여
	 * 필요한 페이지의 Id 값들만 조회를 해온다.
	 *
	 * 이후 페이의 id 를 이용해 필요한 레코드에 대해서만 디스크에 접근을 하게 되면
	 * random access 를 획기적으로 줄일 수 있게 된다.
	 *
	 * but, 단점 존재,,,
	 * 쿼리의 모든 항목이 인덱스에 포함되어야하기 때문에 인덱스가 신규 생성될 수도 있다.
	 * 인덱스도 결국 데이터이기 때문에 너무 많은 항목(where, order by, group by ..)이 들어가면 인덱스의 크기가 비대해진다.
	 * 데이터의 양이 많아지고, 페이지 번호가 뒤로 갈수록 NoOffset 방식에 비해 느리다
	 */
	public List<BookPaginationDto> paginationCoveringIndex(String name, int pageNo, int pageSize) {
		// 1) 커버링 인덱스로 대상 조회
		List<Long> ids = queryFactory
				.select(book.id)
				.from(book)
				.where(book.name.like(name + "%"))
				.orderBy(book.id.desc())
				.limit(pageSize)
				.offset(pageNo * pageSize)
				.fetch();

		//id 값이 없으면 빈 리스트 반환
		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<>();
		}

		return queryFactory
				.select(Projections.fields(BookPaginationDto.class,
						book.id.as("bookId"),
						book.name,
						book.bookNo,
						book.bookType
						)
				)
				.from(book)
				.where(book.id.in(ids))
				.orderBy(book.id.desc())
				.fetch();
	}
}
