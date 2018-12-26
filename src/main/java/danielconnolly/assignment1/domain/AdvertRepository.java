package danielconnolly.assignment1.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdvertRepository extends JpaRepository<Advert, Integer> {

  @Query("SELECT ad FROM Advert ad WHERE ad.description LIKE %?1%")
  List<Advert> searchAdverts(String description);
}
