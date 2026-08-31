package valeriafarinosi.olive_bridge.appConfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import valeriafarinosi.olive_bridge.entities.Role;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.enums.AccountType;
import valeriafarinosi.olive_bridge.enums.ActiveStatus;
import valeriafarinosi.olive_bridge.repositories.RoleRepository;
import valeriafarinosi.olive_bridge.repositories.UserRepository;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // Populate roles
        if (roleRepository.count() == 0) {

            Role admin = new Role("ADMIN");
            Role buyer = new Role("BUYER");

            roleRepository.saveAll(List.of(admin, buyer));
        }

        // Populate test users
        if (userRepository.count() == 0) {

            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() ->
                            new RuntimeException("ADMIN role not found"));

            Role buyerRole = roleRepository.findByName("BUYER")
                    .orElseThrow(() ->
                            new RuntimeException("BUYER role not found"));

            User admin = new User(
                    "Admin",
                    "OliveBridge",
                    "admin@olivebridge.com",
                    passwordEncoder.encode("Password123!"),
                    adminRole,
                    null,
                    ActiveStatus.ACTIVE
            );

            User buyer = new User(
                    "Test",
                    "Buyer",
                    "buyer@olivebridge.com",
                    passwordEncoder.encode("Password123!"),
                    buyerRole,
                    AccountType.INDIVIDUAL,
                    ActiveStatus.ACTIVE
            );

            userRepository.saveAll(List.of(admin, buyer));
        }
    }
}