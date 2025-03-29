package io.skywalkerlabs.uMeet_service.Controllers.Profile;


import io.skywalkerlabs.uMeet_service.DataModels.RequestModels.UserProfileUpdateModel;
import io.skywalkerlabs.uMeet_service.Repositories.UserRepository;
import io.skywalkerlabs.uMeet_service.Services.AmazonWebServices.SimpleStorageService;
import io.skywalkerlabs.uMeet_service.Services.UserProfileService;
import io.skywalkerlabs.uMeet_service.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping({"/api/users/{id}"})
public class UserProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    
    @Autowired
    private UserProfileService userProfileService;

    public UserProfileController(SimpleStorageService simpleStorageService) {
        this.simpleStorageService = simpleStorageService;
    }

    @Autowired
    private SimpleStorageService simpleStorageService;


    @Operation(summary = "Update first name")
    @PatchMapping("/update")
    public ResponseEntity<String> updateFirstName(@PathVariable String id, @RequestBody UserProfileUpdateModel userProfileUpdateModel) {
        return userProfileService.updateFirstName(id, userProfileUpdateModel);
    }
    @Operation(summary = "Upload profile pictures to s3")
    @PutMapping(value = "/profile/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadProfilePictures(@PathVariable String id, @RequestPart("picture") MultipartFile picture) throws IOException {
        return simpleStorageService.uploadProfilePicture(id, picture);
    }

    @Operation(summary = "Upload private pictures to s3")
    @PutMapping(value = "/private/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadPrivatePictures(@PathVariable String id, @RequestPart("picture") MultipartFile picture) throws IOException {
        return simpleStorageService.uploadPrivatePicture(id, picture);
    }

    @Operation(summary = "Delete pictures from the user id's profile directory. Pass id, and file name of picture.")
    @DeleteMapping(value = "/profile/Delete")
    public ResponseEntity<String> deleteProfilePicture(String id, String filename) {
        return simpleStorageService.deleteProfilePicture(id, filename);
    }

    @Operation(summary = "Delete pictures from the user id's private directory. Pass id, and file name of picture.")
    @DeleteMapping(value = "/private/Delete")
    public ResponseEntity<String> deletePrivatePicture(String id, String filename) {
        return simpleStorageService.deletePrivatePicture(id, filename);
    }

}
