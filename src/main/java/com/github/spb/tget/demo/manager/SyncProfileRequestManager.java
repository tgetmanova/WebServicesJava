package com.github.spb.tget.demo.manager;

import com.github.spb.tget.demo.converter.UserSyncRequestConverter;
import com.github.spb.tget.demo.data.UserEntity;
import com.github.spb.tget.demo.model.SyncProfileRequest;
import com.github.spb.tget.demo.repository.InProcessUserRepository;
import com.github.spb.tget.demo.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SyncProfileRequestManager {
    private UserRepository userRepository;

    public SyncProfileRequestManager()
    {
        this.userRepository = new InProcessUserRepository();
    }

    public List<SyncProfileRequest> getSyncProfileRequests()
    {
        return this.userRepository.getUsers()
                .stream()
                .map(item -> UserSyncRequestConverter.toSyncProfileRequest(item))
                .collect(Collectors.toList());
    }

    public SyncProfileRequest getSyncProfileRequestById(UUID id) throws IllegalStateException
    {
        List<UserEntity> users = this.userRepository.getUsers();
        UserEntity targetUser = users
                .stream()
                .filter(item -> item.getUserId().equals(id))
                .findFirst()
                .orElse(null);

        if (targetUser == null)
        {
            throw new IllegalStateException(String.format("Not found %s", id));
        }

        return UserSyncRequestConverter.toSyncProfileRequest(targetUser);
    }
}
