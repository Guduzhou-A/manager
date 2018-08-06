package com.baicells.manager.service;

import com.baicells.manager.exception.UserException;
import com.baicells.manager.model.entity.User;

public interface UserService {
    User getByUsernameAndPass(String username, String password);

    User getById(Integer id);

    void updateUser(int id, String nickName, String oldPass, String newPass) throws UserException;
}
