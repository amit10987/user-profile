package com.example.userprofile;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface HotelRepository extends ElasticsearchRepository<Hotel, String> {
}
