package com.example.userprofile;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AminityRepository extends ElasticsearchRepository<Amenity, String> {
}
