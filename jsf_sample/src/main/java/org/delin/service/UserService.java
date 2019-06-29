package org.delin.service;

import org.delin.entities.UserEntity;

public interface UserService {
    boolean check(UserEntity user);

    boolean addUser(UserEntity user);

    int getLoginCount();
}
