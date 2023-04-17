package blog.study.top.entity.embedded;

import blog.study.top.entity.Academy;
import blog.study.top.entity.Pay.PayDetail;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class PayDetails {
	public static final PayDetails Empty = new PayDetails();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "pay")
	private List<Academy> payDetails = new ArrayList<>();
}
