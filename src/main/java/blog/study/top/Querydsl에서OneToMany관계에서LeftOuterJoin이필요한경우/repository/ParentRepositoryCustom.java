package blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.repository;

import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.dto.Family;
import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.Parent;
import java.util.List;

public interface ParentRepositoryCustom {
	List<Parent> findByName(String name);
	List<Family> findFamily();
	List<Family> findFamilyEntity();
	List<Family> findFamilyAggregation();

}
