package valeriafarinosi.olive_bridge.services;

import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.exceptions.NotFoundException;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.UserResponseDTO;
import valeriafarinosi.olive_bridge.repositories.UserRepository;

import java.util.List;
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

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().getName().equals("BUYER"))
                .map(user -> new UserResponseDTO(
                        user.getUserId(),
                        user.getName(),
                        user.getSurname(),
                        user.getEmail(),
                        user.getRole().getName(),
                        user.getAccountType(),
                        user.getStatus()
                ))
                .toList();
    }
}
