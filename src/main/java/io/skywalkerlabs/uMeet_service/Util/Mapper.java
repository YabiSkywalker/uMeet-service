package io.skywalkerlabs.uMeet_service.Util;

import io.skywalkerlabs.uMeet_service.DataModels.RequestModels.CreateUserRequest;
import io.skywalkerlabs.uMeet_service.Entities.Profile.UserEntity;
import java.util.UUID;

public class Mapper {

    public Mapper() {
    }

    public UserEntity mapUserRequestToUserEntity(CreateUserRequest mapRequest) {
        String userId = UUID.randomUUID().toString();

        UserEntity newAccount = new UserEntity();
        newAccount.setId(userId);
        newAccount.setFirstName(mapRequest.getFirstName());
        newAccount.setLastName(mapRequest.getLastName());
        newAccount.setEmail(newAccount.getEmail());
        return newAccount;
    }

}
