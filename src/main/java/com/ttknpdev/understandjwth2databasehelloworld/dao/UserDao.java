package com.ttknpdev.understandjwth2databasehelloworld.dao;

import com.ttknpdev.understandjwth2databasehelloworld.entities.User;
import com.ttknpdev.understandjwth2databasehelloworld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
    private final UserRepository repository;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public UserDao(UserRepository repository,
                   @Qualifier("bcryptEncoder") PasswordEncoder bcryptEncoder) {
        this.repository = repository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public User create(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRoles(user.getRoles());
        return repository.save(newUser);
    }

    public User read(String username) {
        return repository.findByUsername(username);
    }
}
