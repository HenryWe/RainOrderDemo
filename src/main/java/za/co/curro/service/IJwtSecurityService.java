package za.co.curro.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtSecurityService {

    Boolean validateToken(String token, UserDetails userDetails);
    String generateToken(UserDetails userDetails);
    String extractUsername(String token);
}
