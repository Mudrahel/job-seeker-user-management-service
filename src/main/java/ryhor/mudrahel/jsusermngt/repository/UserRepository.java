package ryhor.mudrahel.jsusermngt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ryhor.mudrahel.jsusermngt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
