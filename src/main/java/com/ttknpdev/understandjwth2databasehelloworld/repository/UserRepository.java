package com.ttknpdev.understandjwth2databasehelloworld.repository;

import com.ttknpdev.understandjwth2databasehelloworld.entities.User;
import org.springframework.data.repository.CrudRepository;

// use passed @Service So it do not need to set it with @Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
