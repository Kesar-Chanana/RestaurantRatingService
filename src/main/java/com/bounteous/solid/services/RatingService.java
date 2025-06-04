package com.bounteous.solid.services;


import com.bounteous.solid.entity.AppUser;
import com.bounteous.solid.entity.Rating;
import com.bounteous.solid.entity.Restaurant;
import com.bounteous.solid.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * Adds or updates a rating.
     *
     * For a given restaurant and user:
     * - If a rating already exists, the new rating overwrites the existing one.
     * - If not, a new rating is created.
     *
     * The method also ensures that the score is within the acceptable range (1 to 10).
     *
     * @param rating the new rating details
     * @return the saved Rating
     * @throws IllegalArgumentException if the score is not between 1 and 10
     */
    public Rating addOrUpdateRating(Rating rating) {
        // Validate the rating score
        if (rating.getScore() < 1 || rating.getScore() > 10) {
            throw new IllegalArgumentException("Rating score must be between 1 and 10");
        }

        // Check if a rating from this user for this restaurant already exists.
        Rating existing = ratingRepository.findByRestaurantAndUser(rating.getRestaurant(), rating.getUser());
        if (existing != null) {
            // Overwrite the existing rating with new details.
            existing.setScore(rating.getScore());
            existing.setDishName(rating.getDishName());
            existing.setDescription(rating.getDescription());
            return ratingRepository.save(existing);
        } else {
            // No existing rating found; create a new one.
            return ratingRepository.save(rating);
        }
    }
    public List<Rating> listRatings(Restaurant restaurant) {
        return ratingRepository.findByRestaurant(restaurant);
    }

    public static Rating getRating(int score, String dishName, String description, Restaurant restaurant, AppUser user) {
        Rating rating = new Rating();
        rating.setRestaurant(restaurant);
        rating.setUser(user);
        rating.setScore(score);
        rating.setDishName(dishName);
        rating.setDescription(description);
        return rating;
    }
}
