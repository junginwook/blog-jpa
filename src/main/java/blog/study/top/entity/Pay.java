package blog.study.top.entity;

import blog.study.top.entity.embedded.PayDetails;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Pay {
	private String code;
	private Long amount;
	private List<PayDetail> details = new ArrayList<>();

	public Pay(String code, Long amount, PayDetail detail) {
		this.code = code;
		this.amount = amount;
		this.details.add(detail);
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PayDetail {
		private String salesType;
		private Long amount;
	}
}
