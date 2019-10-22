package userprofile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import userprofile.model.Hotel;
import userprofile.service.AmenityService;
import userprofile.service.HotelService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private AmenityService amenityService;

    @GetMapping("/amenities")
    @ResponseBody
    Set<Long> getTopAmenitiesByUserId(@RequestParam String userId, @RequestParam int size) {
        return amenityService.getTopAmenityByUserId(userId, size);
    }

    @GetMapping("/hotels")
    @ResponseBody
    List<Hotel> getTopHotelsByUserId(@RequestParam String userId, @RequestParam String size) {
        return null;
    }
}
