package blog.study.top.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Item {

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private Long price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_id")
	private Shop shop;

	public Item(String name, Long price) {
		this.name = name;
		this.price = price;
	}

	public void updateShop(Shop shop) {
		this.shop = shop;
	}
}
