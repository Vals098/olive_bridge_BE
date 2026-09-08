package valeriafarinosi.olive_bridge.services;

import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.enums.AccountType;
import valeriafarinosi.olive_bridge.exceptions.BadRequestException;
import valeriafarinosi.olive_bridge.exceptions.NotFoundException;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.UpdateUserRequestDTO;
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
                        user.getBusinessName(),
                        user.getBusinessTaxId(),
                        user.getStatus()
                ))
                .toList();
    }

    public User updateCurrentUser(UUID userId, UpdateUserRequestDTO payload) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new NotFoundException("User not found")
                );

        // Check if the email is already used by another user
        if (userRepository.findByEmailAndUserIdNot(
                payload.email(),
                userId
        ).isPresent()) {
            throw new BadRequestException("Email already registered.");
        }

        // Business account validation
        if (user.getAccountType() == AccountType.BUSINESS) {

            if (payload.businessName() == null ||
                    payload.businessName().isBlank()) {
                throw new BadRequestException("Business name is required.");
            }

            if (payload.businessTaxId() == null ||
                    payload.businessTaxId().isBlank()) {
                throw new BadRequestException("Business tax ID is required.");
            }

            // Check if the tax ID belongs to another user
            if (userRepository.findByBusinessTaxIdAndUserIdNot(
                    payload.businessTaxId(),
                    userId
            ).isPresent()) {
                throw new BadRequestException(
                        "Business tax ID already registered."
                );
            }
        }

        user.updateProfile(
                payload.name(),
                payload.surname(),
                payload.email(),
                payload.businessName(),
                payload.businessTaxId()
        );

        return userRepository.save(user);
    }
}
