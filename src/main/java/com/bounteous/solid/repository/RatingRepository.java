package com.bounteous.solid.repository;


import com.bounteous.solid.entity.AppUser;
import com.bounteous.solid.entity.Rating;
import com.bounteous.solid.entity.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    Rating findByRestaurantAndUser(Restaurant restaurant, AppUser user);
    List<Rating> findByRestaurant(Restaurant restaurant);
}
