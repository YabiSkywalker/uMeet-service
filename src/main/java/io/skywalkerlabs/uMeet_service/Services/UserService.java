//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.skywalkerlabs.uMeet_service.Services;

import io.skywalkerlabs.uMeet_service.DataModels.RequestModels.CreateUserRequest;
import io.skywalkerlabs.uMeet_service.Entities.Profile.UserEntity;
import io.skywalkerlabs.uMeet_service.Repositories.UserRepository;
import io.skywalkerlabs.uMeet_service.Util.Mapper;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Async
    public ResponseEntity<String> createAccount(CreateUserRequest userCreateRequest) {
        Mapper mapTicket = new Mapper();
        if (this.userRepository.findByEmail(userCreateRequest.getEmail()).isEmpty()) {
            this.userRepository.save(mapTicket.mapUserRequestToUserEntity(userCreateRequest));
            return new ResponseEntity<>("Account created successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("This email is already in use.", HttpStatus.BAD_REQUEST);
        }
    }

    @Async
    public ResponseEntity<String> deleteAccount(String id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found."));

        if (Objects.equals(user.getId(), id)) {
            userRepository.delete(user);
            return new ResponseEntity<>("Account deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Deletion failed, check logs.", HttpStatus.NO_CONTENT);
        }
    }
}
