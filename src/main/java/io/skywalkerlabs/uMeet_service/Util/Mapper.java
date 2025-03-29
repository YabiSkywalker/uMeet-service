package io.skywalkerlabs.uMeet_service.Util;

import com.amazonaws.services.s3.model.ObjectMetadata;
import io.skywalkerlabs.uMeet_service.DataModels.RequestModels.CreateUserRequest;
import io.skywalkerlabs.uMeet_service.DataModels.RequestModels.UserProfileUpdateModel;
import io.skywalkerlabs.uMeet_service.Entities.Profile.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.sns.endpoints.internal.Value;

import java.util.Objects;
import java.util.UUID;


@Service
public class Mapper {

    public Mapper() {
    }


    /* ------------------ user account request to user entity -----------------  */
    public UserEntity mapUserRequestToUserEntity(CreateUserRequest mapRequest) {
        String userId = UUID.randomUUID().toString();

        UserEntity newAccount = new UserEntity();
        newAccount.setId(userId);
        newAccount.setFirstName(mapRequest.getFirstName());
        newAccount.setLastName(mapRequest.getLastName());
        newAccount.setEmail(newAccount.getEmail());
        return newAccount;
    }

    public ObjectMetadata filenameMapper(MultipartFile picture) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(picture.getContentType());
        metadata.setContentLength(picture.getSize());


        return metadata;
    }

}
