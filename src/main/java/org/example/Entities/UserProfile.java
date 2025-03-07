package org.example.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_profile")
public class UserProfile {

    @Id
    private String id;
    private String DisplayName;
    private String AboutMeDescription;
    private double Age;
    private double Height;
    private double Weight;
    private double Position;
    private String Ethnicity;
    private String LookingFor;
    private String Gender;
}
