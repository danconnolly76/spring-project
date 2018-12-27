package danielconnolly.assignment1.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByFNameAndPassword(String accountname, String password);
}
