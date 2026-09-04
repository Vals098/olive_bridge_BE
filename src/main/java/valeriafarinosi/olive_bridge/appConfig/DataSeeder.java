package valeriafarinosi.olive_bridge.appConfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import valeriafarinosi.olive_bridge.entities.*;
import valeriafarinosi.olive_bridge.enums.AccountType;
import valeriafarinosi.olive_bridge.enums.ActiveStatus;
import valeriafarinosi.olive_bridge.repositories.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final TechnicalInformationRepository technicalInformationRepository;
    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;


    public DataSeeder(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder, CategoryRepository categoryRepository, TechnicalInformationRepository technicalInformationRepository, ProductRepository productRepository, ProductVariantRepository productVariantRepository
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
        this.technicalInformationRepository = technicalInformationRepository;
        this.productRepository = productRepository;
        this.productVariantRepository = productVariantRepository;
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

        // Populate categories
        if (categoryRepository.count() == 0) {

            Category extraVirgin = new Category(
                    "Extra Virgin Olive Oil",
                    "High-quality Italian extra virgin olive oil.",
                    ActiveStatus.ACTIVE
            );

            Category organic = new Category(
                    "Organic Olive Oil",
                    "Organic extra virgin olive oil from Italian producers.",
                    ActiveStatus.ACTIVE
            );

            Category flavoured = new Category(
                    "Flavoured Olive Oil",
                    "Extra virgin olive oils flavoured with natural ingredients.",
                    ActiveStatus.ACTIVE
            );

            categoryRepository.saveAll(List.of(extraVirgin, organic, flavoured));
        }

        // Populate technical information
        if (technicalInformationRepository.count() == 0) {

            TechnicalInformation technicalInfo1 = new TechnicalInformation(
                    new BigDecimal("0.25"),
                    new BigDecimal("8.50"),
                    LocalDate.of(2025, 10, 15),
                    LocalDate.of(2027, 10, 15)
            );

            TechnicalInformation technicalInfo2 = new TechnicalInformation(
                    new BigDecimal("0.30"),
                    new BigDecimal("9.20"),
                    LocalDate.of(2025, 11, 5),
                    LocalDate.of(2027, 11, 5)
            );

            TechnicalInformation technicalInfo3 = new TechnicalInformation(
                    new BigDecimal("0.20"),
                    new BigDecimal("7.80"),
                    LocalDate.of(2025, 10, 28),
                    LocalDate.of(2027, 10, 28)
            );

            technicalInformationRepository.saveAll(
                    List.of(technicalInfo1, technicalInfo2, technicalInfo3)
            );
        }

        // Populate products
        if (productRepository.count() == 0) {

            Category extraVirgin = categoryRepository.findAll().get(0);
            Category organic = categoryRepository.findAll().get(1);
            Category flavoured = categoryRepository.findAll().get(2);

            TechnicalInformation technicalInfo1 = technicalInformationRepository.findAll().get(0);
            TechnicalInformation technicalInfo2 = technicalInformationRepository.findAll().get(1);
            TechnicalInformation technicalInfo3 = technicalInformationRepository.findAll().get(2);

            Product product1 = new Product(
                    "Terra di Puglia",
                    "A delicate extra virgin olive oil from Puglia, with a balanced and fruity flavour.",
                    "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5",
                    ActiveStatus.ACTIVE,
                    extraVirgin,
                    technicalInfo1
            );

            Product product2 = new Product(
                    "Verde di Toscana",
                    "Organic extra virgin olive oil from Tuscany, with fresh herbal notes.",
                    "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5",
                    ActiveStatus.ACTIVE,
                    organic,
                    technicalInfo2
            );

            Product product3 = new Product(
                    "Limone Italiano",
                    "Extra virgin olive oil naturally flavoured with Italian lemon.",
                    "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5",
                    ActiveStatus.ACTIVE,
                    flavoured,
                    technicalInfo3
            );

            productRepository.saveAll(List.of(product1, product2, product3));


            // Populate product variants
            if (productVariantRepository.count() == 0) {

                ProductVariant variant1 = new ProductVariant(
                        "250 ml",
                        new BigDecimal("8.50"),
                        null,
                        ActiveStatus.ACTIVE,
                        product1
                );

                ProductVariant variant2 = new ProductVariant(
                        "500 ml",
                        new BigDecimal("14.00"),
                        null,
                        ActiveStatus.ACTIVE,
                        product1
                );

                ProductVariant variant3 = new ProductVariant(
                        "250 ml",
                        new BigDecimal("9.50"),
                        null,
                        ActiveStatus.ACTIVE,
                        product2
                );

                ProductVariant variant4 = new ProductVariant(
                        "500 ml",
                        new BigDecimal("16.00"),
                        null,
                        ActiveStatus.ACTIVE,
                        product2
                );

                ProductVariant variant5 = new ProductVariant(
                        "250 ml",
                        new BigDecimal("10.00"),
                        null,
                        ActiveStatus.ACTIVE,
                        product3
                );

                ProductVariant variant6 = new ProductVariant(
                        "500 ml",
                        new BigDecimal("17.00"),
                        null,
                        ActiveStatus.ACTIVE,
                        product3
                );

                productVariantRepository.saveAll(
                        List.of(
                                variant1,
                                variant2,
                                variant3,
                                variant4,
                                variant5,
                                variant6
                        )
                );
            }
        }


    }
}