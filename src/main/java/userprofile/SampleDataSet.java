package userprofile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import userprofile.model.Amenity;
import userprofile.repository.AmenityRepository;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SampleDataSet {

    Logger logger = LoggerFactory.getLogger(SampleDataSet.class);

    @Autowired
    AmenityRepository amenityRepository;

    @PostConstruct
    public void init() {
        //populateHotelData();
        //populateAmenityData();
    }

    private void populateHotelData() {

    }

    private void populateAmenityData() {

        try {
            List<Amenity> amenities = getAmenities();
            amenityRepository.saveAll(amenities);
        } catch (Exception e) {
            logger.error("Error bulk index", e);
        }

    }

    private List<Amenity> getAmenities() {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("/selections.csv").getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.replace("\"", "").split(",");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records.stream().filter(rec -> rec.size() == 3).map(rec -> {
            Amenity amenity = new Amenity();
            amenity.setTimestamp(Long.parseLong(rec.get(0).trim()));
            amenity.setUserId(rec.get(1).trim());
            amenity.setAmenityId(Long.parseLong(rec.get(2).trim()));
            return amenity;
        }).collect(Collectors.toList());
    }
}
