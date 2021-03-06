package com.user.manager.services;

import com.user.manager.mapper.DtoEntity;
import com.user.manager.model.User;

import java.util.List;

public interface UserService {

    List<DtoEntity> allUsers();
    void saveUser(User user);
    void deleteUser(User user);
    User getUser(String email);
    void addAdminRole(User user);

}
