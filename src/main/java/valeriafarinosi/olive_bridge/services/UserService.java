package valeriafarinosi.olive_bridge.services;

import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.exceptions.NotFoundException;
import valeriafarinosi.olive_bridge.repositories.UserRepository;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("User not found")
                );
    }
}
