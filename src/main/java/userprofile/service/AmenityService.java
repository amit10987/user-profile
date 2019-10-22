package userprofile.service;

import java.util.Set;

public interface AmenityService {

    Set<Long> getTopAmenityByUserId(String userId, int size);
}
