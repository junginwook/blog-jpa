package blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.repository;

import blog.study.top.Querydsl에서OneToMany관계에서LeftOuterJoin이필요한경우.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long>, ParentRepositoryCustom {
}
