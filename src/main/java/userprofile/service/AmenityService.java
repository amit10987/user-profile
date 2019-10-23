package userprofile.service;

import userprofile.model.Amenity;

import java.util.List;

public interface AmenityService {

    List<Amenity> getTopAmenityByUserId(String userId, int size);
}
