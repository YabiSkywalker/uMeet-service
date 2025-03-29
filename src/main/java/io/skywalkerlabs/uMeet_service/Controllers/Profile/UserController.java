//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.skywalkerlabs.uMeet_service.Controllers.Profile;

import io.skywalkerlabs.uMeet_service.DataModels.RequestModels.CreateUserRequest;
import io.skywalkerlabs.uMeet_service.Repositories.UserRepository;

import io.skywalkerlabs.uMeet_service.Services.AmazonWebServices.SimpleStorageService;
import io.skywalkerlabs.uMeet_service.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping({"/api/auth"})
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public UserController() {
    }

    @Operation(summary = "Create new profile")
    @PostMapping("/Create")
    public ResponseEntity<String> createAccount(@RequestBody CreateUserRequest newUser) {
        return this.userService.createAccount(newUser);
    }

    @Operation(summary = "Schedule profile for deletion.")
    @DeleteMapping("/{id}/Delete")
    public ResponseEntity<String> deleteAccount(@PathVariable String id) {
        return userService.deleteAccount(id);
    }

}
