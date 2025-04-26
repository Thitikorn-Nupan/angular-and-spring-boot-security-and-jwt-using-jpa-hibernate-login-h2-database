package com.ttknpdev.understandjwth2databasehelloworld.entities.jwt;


/**
    This class is required for storing the username and password we receive from the client.
*/
public class JwtRequest  { // implements Serializable

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "JwtRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
