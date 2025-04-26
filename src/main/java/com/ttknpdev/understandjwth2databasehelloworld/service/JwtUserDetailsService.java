package com.ttknpdev.understandjwth2databasehelloworld.service;


import com.ttknpdev.understandjwth2databasehelloworld.dao.UserDao;
import com.ttknpdev.understandjwth2databasehelloworld.entities.User;
import com.ttknpdev.understandjwth2databasehelloworld.log.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
/**
 The @Service I don't need this annotation because I called this class on my configuration class
 (Meaning I set any things for in this class But I will build it / convert it to beans I did it on my configuration class)
*/
public class JwtUserDetailsService implements UserDetailsService {
    private final Logging logging;
    private UserDao dao;
    public JwtUserDetailsService() {
        logging = new Logging(this.getClass());
    }
    @Autowired // I need to use SDI. because i don't need injects on config class
    public void setUserDao(UserDao userDao) {
        this.dao = userDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List <SimpleGrantedAuthority> roles = null; // for storing the roles from table users
        User user = dao.read(username); // search by username
        if (user == null) {
            logging.logBack.warn("User is not found by {}", username);
            throw new UsernameNotFoundException("User is not found by "+ username);
        }
        roles = List.of(new SimpleGrantedAuthority(user.getRoles())); // convert string as format RULE_ADMIN,RULE_USER to ADMIN,USER
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), //  username
                user.getPassword(), // password
                roles  // roles
        );
    }

}
