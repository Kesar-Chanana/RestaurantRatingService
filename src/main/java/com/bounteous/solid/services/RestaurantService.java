package com.bounteous.solid.services;

import com.bounteous.solid.entity.Restaurant;
import com.bounteous.solid.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;


    /**
     * Adds a new restaurant.
     */
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    /**
     * Retrieves a restaurant by ID.
     */
    public Restaurant getRestaurant(String id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    /**
     * Lists restaurants sorted by descending average rating.
     * Uses functional style (streams) to sort.
     */
    public List<Restaurant> listRestaurants() {
        return restaurantRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Restaurant::getAverageRating).reversed())
                .limit(10) // Limit to top 10 if required
                .collect(Collectors.toList());
    }
}
