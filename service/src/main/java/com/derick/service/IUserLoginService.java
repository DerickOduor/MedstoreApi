package com.derick.service;

import com.derick.domain.User;
import javassist.NotFoundException;

public interface IUserLoginService {
    public User getUser(int id) throws NotFoundException;
    public User getUserByPhone(String phone) throws NotFoundException;
    public User getUserByEmail(String email) throws NotFoundException;
}
