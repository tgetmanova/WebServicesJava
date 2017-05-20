package com.github.spb.tget.infrastructure.repository;

public class UserRepositoryFactory {

    public UserRepository GetUserRepository(String repositoryType){
        switch (repositoryType){
            case "InProcess":
                return new InProcessUserRepository();
            default:
                throw new IllegalArgumentException(String.format("Invalid value for repository type: %s", repositoryType));
        }
    }
}
