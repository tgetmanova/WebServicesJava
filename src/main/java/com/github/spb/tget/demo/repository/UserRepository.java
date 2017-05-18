package com.github.spb.tget.demo.repository;

import com.github.spb.tget.demo.data.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserRepository {

    List<UserEntity> getUsers();

    void addUser(UserEntity user);

    void deleteUser(UUID userId);

    void updateUser(UserEntity userEntity);
}
