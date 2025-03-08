package io.skywalkerlabs.uMeet_service.Util;

import io.skywalkerlabs.uMeet_service.DataModels.RequestModels.CreateUserRequest;
import io.skywalkerlabs.uMeet_service.Entities.UserEntity;

public class Mapper {

    public Mapper() {
    }

    public UserEntity mapUserRequestToUserEntity(CreateUserRequest mapRequest) {
        UserEntity newAccount = new UserEntity();
        newAccount.setFirstName(mapRequest.getFirstName());
        newAccount.setLastName(mapRequest.getLastName());
        newAccount.setEmail(newAccount.getEmail());
        return newAccount;
    }

}
