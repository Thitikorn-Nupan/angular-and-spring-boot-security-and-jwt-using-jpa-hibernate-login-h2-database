package com.ttknpdev.understandjwth2databasehelloworld.configuration.jwt;

import com.ttknpdev.understandjwth2databasehelloworld.log.Logging;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logging logging;

    public JwtAuthenticationEntryPoint() {
        this.logging = new Logging(this.getClass());
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        /**
            request - that resulted in an AuthenticationException
            response - so that the user agent can begin authentication
            authException - that caused the invocation
        */
        logging.logBack.warn("\n***commence() override method works (rejects every unauthenticated request)\n***Maybe, you are not invalid role");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("Message-Failed","unauthorized in this secure API");
    }
}
