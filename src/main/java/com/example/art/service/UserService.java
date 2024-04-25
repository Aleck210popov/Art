package com.example.art.service;

import com.example.art.domain.User;

import java.util.List;

public interface UserService {
    User add(User user);
    List<User> getAll();
    User getById(long id);
}
