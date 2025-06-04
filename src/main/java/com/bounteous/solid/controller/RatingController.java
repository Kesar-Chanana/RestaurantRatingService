package com.bounteous.solid.controller;

import com.bounteous.solid.entity.AppUser;
import com.bounteous.solid.entity.Rating;
import com.bounteous.solid.entity.Restaurant;
import com.bounteous.solid.services.RatingService;
import com.bounteous.solid.services.RestaurantService;
import com.bounteous.solid.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bounteous.solid.util.Constants.*;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    /**
     * @param restaurantId
     * @param username
     * @param score
     * @param dishName
     * @param description
     * @return
     * This method is responsible to ad or update restaurant
     */
    // Add or update a rating (overwrites if same user rates same restaurant)
    @PostMapping("/add")
    public Rating addRating(
            @RequestParam String restaurantId,
            @RequestParam String username,
            @RequestParam int score,
            @RequestParam(required = false) String dishName,
            @RequestParam(required = false) String description) {

        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        AppUser user = userService.getUserByUsername(username);

        if (restaurant == null || user == null) {
            throw new IllegalArgumentException("Invalid restaurant or user");
        }
//Need to move from controller to service layer -TODO
        Rating rating = new Rating();
        rating.setRestaurant(restaurant);
        rating.setUser(user);
        rating.setScore(score);
        rating.setDishName(dishName);
        rating.setDescription(description);

        
        return ratingService.addOrUpdateRating(rating);
    }

    // List ratings for a restaurant with optional filter and order parameters
    @GetMapping("/list")
    public List<Rating> listRatings(
            @RequestParam String restaurantId,
            @RequestParam(required = false) Integer lowerRange,
            @RequestParam(required = false) Integer upperRange,
            @RequestParam(required = false, defaultValue = "desc") String order) {

        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        if (restaurant == null) {
            throw new IllegalArgumentException("Invalid restaurant");
        }

        List<Rating> ratings = ratingService.listRatings(restaurant);
        // Apply filter if both range values are provided
        if (lowerRange != null && upperRange != null) {
            ratings.removeIf(r -> r.getScore() < lowerRange || r.getScore() > upperRange);
        }
        // Sort based on order parameter
        ratings.sort((r1, r2) -> {
            if (ASCENDING.equalsIgnoreCase(order)){
                return Integer.compare(r1.getScore(), r2.getScore());
            } else{
                return Integer.compare(r2.getScore(), r1.getScore());
            }
        });
        return ratings;
    }
}
