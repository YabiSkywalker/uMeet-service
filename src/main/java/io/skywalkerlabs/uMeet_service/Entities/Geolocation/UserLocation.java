package io.skywalkerlabs.uMeet_service.Entities.Geolocation;

import com.mongodb.client.model.geojson.CoordinateReferenceSystem;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document
public class UserLocation {

    @Id
    private String id;

    @Field("location")
    private Point location;

    private String city;
    private String country;
    private Instant lastUpdated;

    public UserLocation(String id, CoordinateReferenceSystem latitude, Position longitude, String city, String country) {
        this.id = id;
        this.location = new Point(latitude, longitude);
        this.city = city;
        this.country = country;
        this.lastUpdated = Instant.now();
    }
}
