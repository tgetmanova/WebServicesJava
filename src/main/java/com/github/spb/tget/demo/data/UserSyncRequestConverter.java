package com.github.spb.tget.demo.data;

import com.github.spb.tget.demo.model.SyncProfileRequest;

public class UserSyncRequestConverter {

    public static SyncProfileRequest ToSyncProfileRequest(UserEntity userEntity)
    {
        if (userEntity == null)
        {
            throw new IllegalArgumentException("userEntity cannot be null");
        }

        return new SyncProfileRequest(userEntity.getUserId(), userEntity.getRequestId(), userEntity.getAdvertisingOptIn(),
                userEntity.getDateModified(), userEntity.getCountryIsoCode(), userEntity.getLocale());
    }
}
