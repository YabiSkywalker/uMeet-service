package io.skywalkerlabs.uMeet_service.Services;


import io.skywalkerlabs.uMeet_service.DataModels.RequestModels.UserProfileUpdateModel;
import io.skywalkerlabs.uMeet_service.Entities.Profile.UserEntity;
import io.skywalkerlabs.uMeet_service.Repositories.UserRepository;
import io.skywalkerlabs.uMeet_service.Util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    /* ----------------------------------------------------------  */
    /* ------------------ MAPPER CLASS REQUIRED -----------------  */
    /* ----------------------------------------------------------  */

    @Autowired
    private UserRepository userRepository;


    public UserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /* ----------------------------------------------------------  */

    public ResponseEntity<String> updateFirstName(String id, UserProfileUpdateModel userProfileUpdateModel) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User ID not found"));

        if(userProfileUpdateModel.getFirstName() != null) {
            user.setFirstName(userProfileUpdateModel.getFirstName());
        }
        if(userProfileUpdateModel.getLastName() != null) {
            user.setLastName(userProfileUpdateModel.getLastName());
        }

        userRepository.save(user);
        return new ResponseEntity<>("Updated successfully", HttpStatus.ACCEPTED);
    }

}
