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
@RequestMapping({"/Users"})
public class UserController {


    @Autowired
    private UserService userService;

    public UserController(SimpleStorageService simpleStorageService) {
        this.simpleStorageService = simpleStorageService;
    }

    @Autowired
    private SimpleStorageService simpleStorageService;
    @Autowired
    private UserRepository userRepository;

    public UserController() {
    }

    @Operation(summary = "Create new profile")
    @PostMapping("/Create")
    public ResponseEntity<String> createAccount(@RequestBody CreateUserRequest newUser) {
        return this.userService.createAccount(newUser);
    }

    @Operation(summary = "Upload profile pictures")
    @PutMapping(value = "/{userId}/profileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadProfilePictures(@PathVariable String userId, @RequestPart("picture") MultipartFile picture) throws IOException {
        return simpleStorageService.uploadProfilePictures(userId, picture);
    }

    @Operation(summary = "Upload private pictures")
    @PutMapping(value = "/{userId}/privateUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadPrivatePictures(@PathVariable String userId, @RequestPart("picture") MultipartFile picture) throws IOException {
        return simpleStorageService.uploadPrivatePictures(userId, picture);
    }
}
