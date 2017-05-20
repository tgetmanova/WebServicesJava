package com.github.spb.tget.demo.converter;

import com.github.spb.tget.demo.data.UserEntity;
import com.github.spb.tget.demo.model.SyncProfileRequest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class UserSyncRequestConverter {

    public static SyncProfileRequest toSyncProfileRequest(UserEntity userEntity) {
        if (userEntity == null) {
            throw new IllegalArgumentException("userEntity cannot be null");
        }

        return new SyncProfileRequest(userEntity.getUserId(), userEntity.getRequestId(),
                userEntity.getAdvertisingOptIn(), LocalDateTime.ofInstant(userEntity.getDateModified(),
                ZoneId.systemDefault()), userEntity.getCountryIsoCode(), userEntity.getLocale());
    }

    public static UserEntity toUserEntity(SyncProfileRequest syncProfileRequest) {
        if (syncProfileRequest == null) {
            throw new IllegalArgumentException("syncProfileRequest cannot be null");
        }

        return new UserEntity(syncProfileRequest.getUserId(), syncProfileRequest.getRequestId(),
                syncProfileRequest.getAdvertisingOptIn(), syncProfileRequest.getDateModified().toInstant(ZoneOffset.UTC),
                syncProfileRequest.getCountryIsoCode(), syncProfileRequest.getLocale());
    }
}
