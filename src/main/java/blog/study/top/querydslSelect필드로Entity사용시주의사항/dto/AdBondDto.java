package blog.study.top.querydslSelect필드로Entity사용시주의사항.dto;

import blog.study.top.entity.AdBond;
import blog.study.top.entity.Customer;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdBondDto {
	Long amount;
	LocalDate txDate;
	String orderType;
	Long customerId;

	public AdBond toEntity() {
		return AdBond.builder()
				.amount(amount)
				.txDate(txDate)
				.orderType(orderType)
				.customer(new Customer())
				.build();
	}
}
