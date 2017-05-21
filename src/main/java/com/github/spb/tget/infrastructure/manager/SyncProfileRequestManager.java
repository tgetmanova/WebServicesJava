package com.github.spb.tget.infrastructure.manager;

import com.github.spb.tget.SparkBasedApp;
import com.github.spb.tget.infrastructure.converter.UserSyncRequestConverter;
import com.github.spb.tget.infrastructure.data.UserEntity;
import com.github.spb.tget.infrastructure.model.SyncProfileRequest;
import com.github.spb.tget.infrastructure.repository.UserRepository;
import com.github.spb.tget.infrastructure.repository.UserRepositoryFactory;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

public class SyncProfileRequestManager {

    private UserRepository userRepository;

    private UserRepositoryFactory userRepositoryFactory;

    public SyncProfileRequestManager() throws IOException {

        Properties props = new Properties();

        try (InputStream reader = new FileInputStream(
                new File(SyncProfileRequestManager.class.getClassLoader().getResource("app.properties").getPath()))) {
            props.load(reader);
        }

        this.userRepositoryFactory = new UserRepositoryFactory();
        this.userRepository = this.userRepositoryFactory.GetUserRepository(props.getProperty("repositoryType"));
    }

    public List<SyncProfileRequest> getSyncProfileRequests() {
        return this.userRepository.getUsers()
                .stream()
                .map(item -> UserSyncRequestConverter.toSyncProfileRequest(item))
                .collect(Collectors.toList());
    }

    public SyncProfileRequest getSyncProfileRequestById(UUID id) throws IllegalStateException {
        List<UserEntity> users = this.userRepository.getUsers();
        UserEntity targetUser = users
                .stream()
                .filter(item -> item.getUserId().equals(id))
                .findFirst()
                .orElse(null);

        if (targetUser == null) {
            throw new IllegalStateException(String.format("Not found %s", id));
        }

        return UserSyncRequestConverter.toSyncProfileRequest(targetUser);
    }

    public SyncProfileRequest createSyncProfileRequest(SyncProfileRequest syncProfileRequest) {
        SyncProfileRequest syncProfileRequestToCreate = new SyncProfileRequest(syncProfileRequest.getUserId(),
                UUID.randomUUID(), syncProfileRequest.getAdvertisingOptIn(),
                LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()),
                syncProfileRequest.getCountryIsoCode(), syncProfileRequest.getLocale());

        this.userRepository.addUser(UserSyncRequestConverter.toUserEntity(syncProfileRequestToCreate));

        return syncProfileRequestToCreate;
    }

    public void UpdateSyncProfileRequest(SyncProfileRequest syncProfileRequest)
    {
        SyncProfileRequest syncProfileRequestToUpdate = new SyncProfileRequest(syncProfileRequest.getUserId(),
                UUID.randomUUID(), syncProfileRequest.getAdvertisingOptIn(),
                LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()),
                syncProfileRequest.getCountryIsoCode(), syncProfileRequest.getLocale());

        this.userRepository.updateUser(UserSyncRequestConverter.toUserEntity(syncProfileRequestToUpdate));
    }
}
