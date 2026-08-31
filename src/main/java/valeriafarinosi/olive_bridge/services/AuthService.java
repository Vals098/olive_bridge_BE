package valeriafarinosi.olive_bridge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.exceptions.UnauthorizedException;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.LoginRequestDTO;
import valeriafarinosi.olive_bridge.repositories.UserRepository;
import valeriafarinosi.olive_bridge.security.JWTTools;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateAndGenerateToken(LoginRequestDTO body) {

        User user = userRepository.findByEmail(body.email())
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid credentials")
                );

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        return jwtTools.generateToken(user);
    }
}