package userprofile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import userprofile.model.Amenity;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SampleDataSet {

    Logger logger = LoggerFactory.getLogger(SampleDataSet.class);

    @Autowired
    ElasticsearchTemplate template;

    @PostConstruct
    public void init() {
        populateHotelData();
        populateAmenityData();
    }

    private void populateHotelData() {

    }

    private void populateAmenityData() {
        try {
            if (!template.indexExists("selections")) {
                template.createIndex("selections");
            }
            ObjectMapper mapper = new ObjectMapper();

            List<Amenity> amenities = getAmenities();
            List<IndexQuery> queries = amenities.parallelStream().map(amenity -> {
                IndexQuery indexQuery = new IndexQuery();
                indexQuery.setId(amenity.getId());
                indexQuery.setIndexName("selections");
                indexQuery.setType("amenity");
                try {
                    indexQuery.setSource(mapper.writeValueAsString(amenity));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return indexQuery;
            }).collect(Collectors.toList());

            if (queries.size() > 0) {
                template.bulkIndex(queries);
            }
            template.refresh("selections");

        } catch (Exception e) {
            logger.error("Error bulk index", e);
        }

    }

    private List<Amenity> getAmenities() {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(getClass().getResource("/selections.csv").getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.replace("\"","").split(",");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records.stream().skip(1).filter(rec -> rec.size() == 3).map(rec -> {
            Amenity amenity = new Amenity();
            amenity.setId(UUID.randomUUID().toString());
            amenity.setTimestamp(Integer.parseInt(rec.get(0).trim()));
            amenity.setUserId(Integer.parseInt(rec.get(1).trim()));
            amenity.setAmenityId(Integer.parseInt(rec.get(2).trim()));
            return amenity;
        }).collect(Collectors.toList());
    }
}
