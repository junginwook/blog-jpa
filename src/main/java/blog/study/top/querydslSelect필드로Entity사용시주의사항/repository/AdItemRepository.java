package blog.study.top.querydslSelect필드로Entity사용시주의사항.repository;

import blog.study.top.entity.AdItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdItemRepository extends JpaRepository<AdItem, Long> {

}
