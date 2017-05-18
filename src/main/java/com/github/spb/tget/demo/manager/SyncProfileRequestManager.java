package com.github.spb.tget.demo.manager;

import com.github.spb.tget.demo.data.UserSyncRequestConverter;
import com.github.spb.tget.demo.model.SyncProfileRequest;
import com.github.spb.tget.demo.repository.InProcessUserRepository;
import com.github.spb.tget.demo.repository.UserRepository;

import java.util.List;
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
                .map(item -> UserSyncRequestConverter.ToSyncProfileRequest(item))
                .collect(Collectors.toList());

    }
}
