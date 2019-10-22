package userprofile.service.impl;

import org.springframework.stereotype.Service;
import userprofile.model.Hotel;
import userprofile.service.HotelService;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Override
    public List<Hotel> getTopHotelsByUserId(int userId) {
        return null;
    }
}
