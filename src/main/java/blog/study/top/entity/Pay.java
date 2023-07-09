package blog.study.top.entity;

import blog.study.top.entity.embedded.PayDetails;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Pay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String code;
	private Long amount;

	public Pay(String code, Long amount, PayDetail detail) {
		this.code = code;
		this.amount = amount;

	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PayDetail {
		private String salesType;
		private Long amount;
	}
}
