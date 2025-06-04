package com.bounteous.solid.controller;

import com.bounteous.solid.entity.Restaurant;
import com.bounteous.solid.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/add")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @GetMapping("/get/{id}")
    public Restaurant getRestaurant(@PathVariable String id) {
        return restaurantService.getRestaurant(id);
    }

    @GetMapping("/lists")
    public List<Restaurant> listRestaurants() {
        return restaurantService.listRestaurants();
    }

    /**
     * Lists restaurants sorted by descending average rating.
     * Optionally, can limit to the top N restaurants.
     */
    @GetMapping("/listDesc")
    public List<Restaurant> listRestaurantsDec(@RequestParam(required = false, defaultValue = "10") int n) {
        List<Restaurant> sorted = restaurantService.listRestaurants();
        // Declaratively limit the list to top n restaurants
        return sorted.size() > n ? sorted.subList(0, n) : sorted;
    }


    //Functional-style version using Java Streams
    @GetMapping("/listFunc")
    public List<Restaurant> listRestaurantsFunctional(@RequestParam(required = false, defaultValue = "10") int n) {
        return restaurantService.listRestaurants().stream()
                .limit(n)
                .collect(Collectors.toList());
    }
}
