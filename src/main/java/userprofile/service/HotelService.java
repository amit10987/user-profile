package userprofile.service;

import userprofile.model.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> getTopHotelsByUserId(String userId, int size);
}
