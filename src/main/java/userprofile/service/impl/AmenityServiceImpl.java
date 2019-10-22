package userprofile.service.impl;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.catalog.Database;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import userprofile.model.Amenity;
import userprofile.repository.AmenityRepository;
import userprofile.service.AmenityService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AmenityServiceImpl implements AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    @Autowired
    SQLContext sqlContext;

    @Override
    public Set<Long> getTopAmenityByUserId(String userId, int size) {
        List<Amenity> amenities = amenityRepository.findByUserId(userId);
        Map<Long, Long> amenity = amenities.stream().collect(Collectors.toMap(Amenity::getAmenityId, p -> 1L, (u, u2) -> u + 1));
        Map<Long, Long> amenitySortByValDesc = amenity.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        return amenitySortByValDesc.keySet().stream().sequential().limit(size).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Set<Long> runSpark() {
        Dataset<Row> row = sqlContext.read().csv(new ClassPathResource("/selections.csv").getPath());
        return null;
    }

}
