package com.bounteous.solid.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "restaurants")
public class Restaurant {
    @Id
    private String id;
    private String name;
    private List<Rating> ratings = new ArrayList<>();

    public Restaurant() { }

    public Restaurant(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Rating> getRatings() {
        return ratings;
    }
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    /**
     * Declarative and functional approach to compute the average rating using Java Streams.
     */
    public double getAverageRatingDeclarative() {
        return ratings.isEmpty() ? 0.0 : ratings.stream().mapToDouble(Rating::getScore).average().orElse(0.0);
    }
    // Calculate average rating
    public double getAverageRating() {
        if(ratings.isEmpty()) return 0.0;
        double sum = ratings.stream().mapToDouble(Rating::getScore).sum();
        return sum / ratings.size();
    }
    //Functional way
    public double getAverageRatingFunctional() {
        return ratings.stream()
                .collect(Collectors.averagingDouble(Rating::getScore));
    }
}
