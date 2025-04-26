package com.ttknpdev.understandjwth2databasehelloworld.entities.jwt;


/**
   (Just showing TOKEN like java POJO )
   This is class is required for containing JWT on response that returned to the user.
*/
public class JwtResponse {
    private String JWT;

    public JwtResponse(String JWT) {
        this.JWT = JWT;
    }

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "JWT='" + JWT + '\'' +
                '}';
    }
}
