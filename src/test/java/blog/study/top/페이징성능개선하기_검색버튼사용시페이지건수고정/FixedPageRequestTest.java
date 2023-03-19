package blog.study.top.페이징성능개선하기_검색버튼사용시페이지건수고정;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.data.domain.PageRequest;

class FixedPageRequestTest {

	@ParameterizedTest
	@CsvSource({
			"10, 100, 9", // (1)
			"10, 101, 10", // (2)
			"10, 91, 9", // (3)
			"10, 90, 8", // (4)
			"10, 79, 7", // (5)
			"10, 111, 10"
	})
	void testFixedPageRequest(int pageNo, long totalCount, int expectedPageNo) {
		//given
		PageRequest pageRequest = PageRequest.of(pageNo,10);

		//when
		FixedPageRequest result = new FixedPageRequest(pageRequest, totalCount);

		//then
		assertThat(result.getPageNumber()).isEqualTo(expectedPageNo);
	}

	//1 ~ 10 - 0
	//11 ~ 20 - 1
	//...
	//71 ~ 80  - 7

	//91 ~ 100 - 9
}