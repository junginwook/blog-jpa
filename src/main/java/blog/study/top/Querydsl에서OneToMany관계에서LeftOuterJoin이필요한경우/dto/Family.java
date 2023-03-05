package blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.dto;

import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.Child;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Family {

	private String parentName;
	private List<Child> children = new ArrayList<>();
}
