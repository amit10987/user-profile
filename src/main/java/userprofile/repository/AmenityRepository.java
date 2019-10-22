package userprofile.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import userprofile.model.Amenity;

import java.util.List;

@Repository
public interface AmenityRepository extends CrudRepository<Amenity, String> {

    List<Amenity> findByUserId(String userId);
}
