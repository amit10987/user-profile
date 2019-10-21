package userprofile.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import userprofile.model.Hotel;

@Repository
public interface HotelRepository extends ElasticsearchRepository<Hotel, String> {
}
