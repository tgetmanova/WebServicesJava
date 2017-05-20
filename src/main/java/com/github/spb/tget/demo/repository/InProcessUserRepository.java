package com.github.spb.tget.demo.repository;

import com.github.spb.tget.demo.data.UserEntity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InProcessUserRepository implements UserRepository {

    private static List<UserEntity> users = new ArrayList<UserEntity>();

    public InProcessUserRepository() {
        users.add(new UserEntity(UUID.fromString("8c46ada1-ac9c-4c0e-b1f6-265fa30454c0"), UUID.randomUUID(),
                true, Instant.now().minusSeconds(344000), "RU", "RU"));
        users.add(new UserEntity(UUID.fromString("14f82c78-390f-4a90-bd99-dbd24d9c968d"), UUID.randomUUID(),
                false, Instant.now().minus(2, ChronoUnit.DAYS), "HU", "HU"));
        users.add(new UserEntity(UUID.fromString("0580769c-e4b0-4f98-8ca9-eb598333ec06"), UUID.randomUUID(),
                null, Instant.now().minus(1, ChronoUnit.HOURS), "ES", "ES-ES"));
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void addUser(UserEntity user) {
        users.add(user);
    }

    public void deleteUser(UUID userId) {
        UserEntity user = users.stream()
                .filter(item -> item.getUserId().equals(userId))
                .findFirst()
                .orElse(null);

        if (user == null) {
            throw new IllegalStateException(String.format("Cannot find user wit ID: %s", userId));
        }

        users.remove(user);
    }

    public void updateUser(UserEntity userEntity) {
        UserEntity user = users.stream()
                .filter(item -> item.getUserId().equals(userEntity.getUserId()))
                .findFirst()
                .orElse(null);

        if (user == null) {
            throw new IllegalStateException(String.format("Cannot find user wit ID: %s", userEntity.getUserId()));
        }

        users.set(users.indexOf(user), userEntity);
    }
}
