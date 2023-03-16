package blog.study.top.exists쿼리성능개선.repository;

import blog.study.top.entity.Academy;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AcademyRepository230312 extends JpaRepository<Academy, Long>, AcademyRepository230312Custom {
	boolean existsAcademyByNameAndAddressAndPhoneNumber(String name, String address, String phoneNumber);
}
