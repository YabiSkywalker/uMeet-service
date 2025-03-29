package io.skywalkerlabs.uMeet_service.Repositories;

import io.skywalkerlabs.uMeet_service.Entities.Profile.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);


    @Query(value = "{ '_id' : ?0 }", fields = "{ 'firstName' : ?1 }")
    void updateFirstName(String userId, String displayName);
}
