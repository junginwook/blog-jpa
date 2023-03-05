package blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private List<Child> children = new ArrayList<>();

	public Parent(String name) {
		this.name = name;
	}

	public void addChild(Child child) {
		children.add(child);
		child.setParent(this);
	}
}
