package blog.study.top.페이징성능개선하기_검색버튼사용시페이지건수고정;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class FixedPageRequest extends PageRequest {

	public FixedPageRequest(Pageable pageable, long totalCount) {
		super(getPageNo(pageable, totalCount), pageable.getPageSize(), pageable.getSort());
	}

	private static int getPageNo(Pageable pageable, long totalCount) {
		int pageNo = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();
		long requestCount = pageNo * pageSize;

		if (totalCount > requestCount) {
			return pageNo;
		}

		return (int) Math.floor((double)(totalCount-1)/pageNo);
	}
}
