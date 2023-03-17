package blog.study.top.querydslSelect필드로Entity사용시주의사항.service;

import static org.junit.jupiter.api.Assertions.*;

import blog.study.top.entity.AdBond;
import blog.study.top.querydslSelect필드로Entity사용시주의사항.repository.ShopRepository230317;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdItemServiceTest {

	@Autowired
	private AdItemService adItemService;

	@Test
	void testCreateAdBond() {
		adItemService.createAdBond1(LocalDate.now(), LocalDate.now(), List.of("1", "2"));
	}
}