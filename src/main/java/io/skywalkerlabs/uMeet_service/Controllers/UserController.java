//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.skywalkerlabs.uMeet_service.Controllers;

import io.skywalkerlabs.uMeet_service.DataModels.RequestModels.CreateUserRequest;
import io.skywalkerlabs.uMeet_service.Repositories.UserRepository;

import io.skywalkerlabs.uMeet_service.Services.AmazonWebServices.UploadToS3;
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

    public UserController(UploadToS3 uploadToS3) {
        this.uploadToS3 = uploadToS3;
    }

    @Autowired
    private UploadToS3 uploadToS3;
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
    @PutMapping(value = "/{userId}/Upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadProfilePictures(@PathVariable String userId, @RequestPart("picture") MultipartFile picture) throws IOException {
        return uploadToS3.uploadPictures(userId, picture);
    }
}
