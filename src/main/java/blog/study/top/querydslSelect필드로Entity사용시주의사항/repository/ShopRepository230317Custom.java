package blog.study.top.querydslSelect필드로Entity사용시주의사항.repository;

import blog.study.top.entity.AdBond;
import blog.study.top.querydslSelect필드로Entity사용시주의사항.dto.AdBondDto;
import java.time.LocalDate;
import java.util.List;

public interface ShopRepository230317Custom {
	List<AdBond> createAdBond(LocalDate startDate, LocalDate endDate, List<String> orderTypes);
	List<AdBondDto> createAdBondAdvanced(List<String> orderTypes);
}
