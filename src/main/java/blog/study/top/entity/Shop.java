package blog.study.top.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String address;
	private String shopNo;
	private String shopName;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> items = new ArrayList<>();

	public Shop(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public void addItem(Item item) {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}
		this.items.add(item);
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
