package userprofile.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import userprofile.model.Amenity;

import java.util.List;

@Repository
public interface AmenityRepository extends ElasticsearchRepository<Amenity, String> {

    List<Amenity> findByUserIdOrderBy(int userId);
}
