package blog.study.top.entity;

import blog.study.top.entity.converter.PayConverter;
import blog.study.top.entity.converter.PeriodStringConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@ToString
@DynamicUpdate
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderNo;

	@Embedded
	private Member member;

	@Convert(converter = PeriodStringConverter.class)
	private Period period;

	@Convert(converter = PayConverter.class)
	private List<Pay> pays = new ArrayList<>();

	private LocalDate cccDate;

	public Order(String orderNo, Member member, Period period, Pay pay) {
		this.orderNo = orderNo;
		this.member = member;
		this.period = period;
		this.pays.add(pay);
	}

	public void changeOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setCCCDate(LocalDate localDate) {
		this.cccDate = localDate;
	}
}
