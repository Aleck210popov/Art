package com.example.art.testuset;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    @Override
    public User add(User user) {
        return repository.save(user);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User getById(long id) {
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) throw new RuntimeException("User with ID " + id + " not found");
        return user.get();
    }
}
