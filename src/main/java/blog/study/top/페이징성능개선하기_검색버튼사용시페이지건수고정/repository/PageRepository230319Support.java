package blog.study.top.페이징성능개선하기_검색버튼사용시페이지건수고정.repository;

import static blog.study.top.entity.QBook.*;

import blog.study.top.entity.Book;
import blog.study.top.entity.QBook;
import blog.study.top.페이징성능개선하기_검색버튼사용시페이지건수고정.FixedPageRequest;
import blog.study.top.페이징성능개선하기_커버링인덱스사용.dto.BookPaginationDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class PageRepository230319Support extends QuerydslRepositorySupport {

	private final JPAQueryFactory queryFactory;

	public PageRepository230319Support(JPAQueryFactory queryFactory) {
		super(Book.class);
		this.queryFactory = queryFactory;
	}

	public Page<BookPaginationDto> paginationCountSearchBtn(boolean useSearchBtn, Pageable pageable, String name) {
		JPAQuery<BookPaginationDto> query = queryFactory
				.select(Projections.fields(BookPaginationDto.class,
						book.id.as("bookId"),
						book.name,
						book.bookNo,
						book.bookType
				))
				.from(book)
				.where(
						book.name.like(name + "%")
				)
				.orderBy(book.id.desc());

		JPQLQuery<BookPaginationDto> pagination = getQuerydsl().applyPagination(pageable, query);

		//검색 버튼을 눌렀을 경우 처리
		if (useSearchBtn) {
			int fixedPageCount = 10 * pageable.getPageSize();
			return new PageImpl<>(pagination.fetch(), pageable, fixedPageCount);
		}

		//페이징 버튼을 눌렀을 경우 처리
		long totalCount = pagination.fetchCount();
		Pageable pageRequest = new FixedPageRequest(pageable, totalCount);
		return new PageImpl<>(getQuerydsl().applyPagination(pageRequest, query).fetch(), pageRequest, totalCount);
	}

	Pageable exchangePageRequest(Pageable pageable, long totalCount) {
		int pageNo = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();
		long requestCount = (pageNo -1) * pageSize;

		if (totalCount > requestCount) {
			return pageable;
		}

		int requestPageNo = (int) Math.ceil((double) totalCount/pageNo);
		return PageRequest.of(requestPageNo, pageSize);
	}
}
