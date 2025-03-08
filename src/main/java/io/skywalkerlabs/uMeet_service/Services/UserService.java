//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> createAccount(UserCreateRequest userCreateRequest) {
        Mapper mapTicket = new Mapper();
        if (this.userRepository.findByEmail(userCreateRequest.getEmail()) == null) {
            this.userRepository.save(mapTicket.mapUserRequestToUserEntity(userCreateRequest));
            return new ResponseEntity("Account created successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity("This email is already in use.", HttpStatus.BAD_REQUEST);
        }
    }
}
