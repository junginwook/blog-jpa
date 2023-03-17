package blog.study.top.querydslSelect필드로Entity사용시주의사항.service;

import blog.study.top.entity.AdBond;
import blog.study.top.jpa에서대량의데이터를삭제할때주의해야할점.repository.ShopRepository;
import blog.study.top.querydslSelect필드로Entity사용시주의사항.dto.AdBondDto;
import blog.study.top.querydslSelect필드로Entity사용시주의사항.repository.AdBondRepository;
import blog.study.top.querydslSelect필드로Entity사용시주의사항.repository.ShopRepository230317;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdItemService {

	private final AdBondRepository adBondRepository;
	private final ShopRepository230317 shopRepository230317;

	@Transactional
	public void createAdBond1(LocalDate startDate, LocalDate endDate, List<String> orderTypes) {
		List<AdBond> adBonds = shopRepository230317.createAdBond(startDate, endDate, orderTypes);
		adBondRepository.saveAll(adBonds);
	}

	@Transactional
	public void createAdBond2(List<String> orderTypes) {
		List<AdBondDto> adBondDtoList = shopRepository230317.createAdBondAdvanced(orderTypes);
		List<AdBond> adBondList = adBondDtoList.stream()
				.map(AdBondDto::toEntity)
				.toList();
		adBondRepository.saveAll(adBondList);
	}
}
