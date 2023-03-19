package blog.study.top.페이징성능개선하기_검색버튼사용시페이지건수고정.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import blog.study.top.페이징성능개선하기_커버링인덱스사용.dto.BookPaginationDto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RunWith(MockitoJUnitRunner.class)
class PageRepository230319SupportTest {

	@InjectMocks
	private PageRepository230319Support pageRepository230319Support;

	@DisplayName("검색버튼 사용시 10개 페이지 건수가 리턴된다.")
	@Test
	public void testPaginationCountSearchBtn() {
		String prefixName = "name";
		PageRequest pageRequest = PageRequest.of(1, 10);
		boolean useSearchBtn = true;
		Page<BookPaginationDto> page = pageRepository230319Support.paginationCountSearchBtn(useSearchBtn, pageRequest, prefixName);

		assertThat(page.getTotalElements()).isEqualTo(100);
	}
}