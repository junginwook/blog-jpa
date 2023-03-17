package blog.study.top.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String customerNo;
	private String customerName;
	private String bizNo;
	private String ceoName;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "customer")
	private Shop shop;

	public Customer(String name) {
		this.name = name;
	}
}
