package blog.study.top.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity(name = "book")
@Getter
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Long bookNo;

	private String bookType;

	@Builder
	public Book(String name, Long bookNo) {
		this.name = name;
		this.bookNo = bookNo;
	}
}
