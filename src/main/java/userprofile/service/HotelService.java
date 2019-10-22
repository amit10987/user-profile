package userprofile.service;

import userprofile.model.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> getTopHotelsByUserId(int userId);
}
