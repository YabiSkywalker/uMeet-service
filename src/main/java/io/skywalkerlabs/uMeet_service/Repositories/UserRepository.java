package io.skywalkerlabs.uMeet_service.Repositories;

import io.skywalkerlabs.uMeet_service.Entities.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
}
