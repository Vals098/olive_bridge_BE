package valeriafarinosi.olive_bridge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.Role;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.enums.ActiveStatus;
import valeriafarinosi.olive_bridge.exceptions.BadRequestException;
import valeriafarinosi.olive_bridge.exceptions.UnauthorizedException;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.LoginRequestDTO;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.RegisterRequestDTO;
import valeriafarinosi.olive_bridge.repositories.RoleRepository;
import valeriafarinosi.olive_bridge.repositories.UserRepository;
import valeriafarinosi.olive_bridge.security.JWTTools;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTools jwtTools;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

    public User register(RegisterRequestDTO body) {

        if (userRepository.findByEmail(body.email()).isPresent()) {
            throw new BadRequestException("Email already registered");
        }

        Role buyerRole = roleRepository.findByName("BUYER")
                .orElseThrow(() ->
                        new RuntimeException("BUYER role not found")
                );

        User newUser = new User(
                body.name(),
                body.surname(),
                body.email(),
                passwordEncoder.encode(body.password()),
                buyerRole,
                body.accountType(),
                ActiveStatus.ACTIVE
        );

        return userRepository.save(newUser);
    }
}