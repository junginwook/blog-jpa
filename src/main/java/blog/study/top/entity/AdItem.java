package blog.study.top.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(indexes = {
		@Index(name = "idx_ad_item_1", columnList="orderType, txDate")
})
public class AdItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderType;
	private LocalDate txDate;
	private String serviceTxNo;
	private long amount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_ad_item_customer"))
	private Customer customer;

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AdItem(String orderType) {
		this.orderType = orderType;
	}
}
