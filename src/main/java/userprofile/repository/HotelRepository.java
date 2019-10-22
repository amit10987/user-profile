package userprofile.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import userprofile.model.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, String> {
    List<Hotel> findByUserId(int userId);
}
