package valeriafarinosi.olive_bridge.appConfig;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import valeriafarinosi.olive_bridge.entities.*;
import valeriafarinosi.olive_bridge.enums.*;
import valeriafarinosi.olive_bridge.repositories.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final SampleRequestRepository sampleRequestRepository;
    private final BusinessInquiryRepository businessInquiryRepository;


    public DataSeeder(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            CategoryRepository categoryRepository,
            TechnicalInformationRepository technicalInformationRepository,
            ProductRepository productRepository,
            ProductVariantRepository productVariantRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            SampleRequestRepository sampleRequestRepository,
            BusinessInquiryRepository businessInquiryRepository
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
        this.technicalInformationRepository = technicalInformationRepository;
        this.productRepository = productRepository;
        this.productVariantRepository = productVariantRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.sampleRequestRepository = sampleRequestRepository;
        this.businessInquiryRepository = businessInquiryRepository;
    }


    @Override
    public void run(String... args) {

        // ============================================================
        // ROLES
        // ============================================================

        if (roleRepository.count() == 0) {

            Role admin = new Role("ADMIN");
            Role buyer = new Role("BUYER");

            roleRepository.saveAll(
                    List.of(admin, buyer)
            );
        }


        // ============================================================
        // INITIAL USERS
        // ============================================================

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
                    null,
                    null,
                    ActiveStatus.ACTIVE
            );


            User buyer = new User(
                    "Test",
                    "User",
                    "test@olivebridge.com",
                    passwordEncoder.encode("Password123!"),
                    buyerRole,
                    AccountType.INDIVIDUAL,
                    null,
                    null,
                    ActiveStatus.ACTIVE
            );


            userRepository.saveAll(
                    List.of(admin, buyer)
            );
        }


        // ============================================================
        // CATEGORIES
        // ============================================================

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

            categoryRepository.saveAll(
                    List.of(
                            extraVirgin,
                            organic,
                            flavoured
                    )
            );
        }


        // ============================================================
        // TECHNICAL INFORMATION
        // ============================================================

        if (technicalInformationRepository.count() == 0) {

            TechnicalInformation technicalInfo1 =
                    new TechnicalInformation(
                            new BigDecimal("0.25"),
                            new BigDecimal("8.50"),
                            LocalDate.of(2025, 10, 15),
                            LocalDate.of(2027, 10, 15)
                    );

            TechnicalInformation technicalInfo2 =
                    new TechnicalInformation(
                            new BigDecimal("0.30"),
                            new BigDecimal("9.20"),
                            LocalDate.of(2025, 11, 5),
                            LocalDate.of(2027, 11, 5)
                    );

            TechnicalInformation technicalInfo3 =
                    new TechnicalInformation(
                            new BigDecimal("0.20"),
                            new BigDecimal("7.80"),
                            LocalDate.of(2025, 10, 28),
                            LocalDate.of(2027, 10, 28)
                    );

            technicalInformationRepository.saveAll(
                    List.of(
                            technicalInfo1,
                            technicalInfo2,
                            technicalInfo3
                    )
            );
        }


        // ============================================================
        // PRODUCTS
        // ============================================================

        if (productRepository.count() == 0) {

            Category extraVirgin =
                    categoryRepository.findAll().get(0);

            Category organic =
                    categoryRepository.findAll().get(1);

            Category flavoured =
                    categoryRepository.findAll().get(2);


            TechnicalInformation technicalInfo1 =
                    technicalInformationRepository.findAll().get(0);

            TechnicalInformation technicalInfo2 =
                    technicalInformationRepository.findAll().get(1);

            TechnicalInformation technicalInfo3 =
                    technicalInformationRepository.findAll().get(2);


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


            productRepository.saveAll(
                    List.of(
                            product1,
                            product2,
                            product3
                    )
            );


            // PRODUCT VARIANTS

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


        // ============================================================
        // DEMO DATA
        // ============================================================

        createDemoData();
    }


    // ================================================================
    // DEMO DATA CREATION
    // ================================================================

    private void createDemoData() {

        Role buyerRole = roleRepository.findByName("BUYER")
                .orElseThrow(() ->
                        new RuntimeException("BUYER role not found"));


        // ------------------------------------------------------------
        // DEMO USERS
        // ------------------------------------------------------------

        List<User> demoUsers = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {

            String email =
                    "demo.customer" + i + "@olivebridge.test";

            User user = userRepository
                    .findByEmail(email)
                    .orElse(null);


            if (user == null) {

                boolean business = i > 6;

                user = new User(
                        "Demo",
                        "Customer" + i,
                        email,
                        passwordEncoder.encode(
                                "Password123!"
                        ),
                        buyerRole,
                        business
                                ? AccountType.BUSINESS
                                : AccountType.INDIVIDUAL,
                        business
                                ? "Demo Business " + i
                                : null,
                        business
                                ? "DEMO-TAX-" + i
                                : null,
                        ActiveStatus.ACTIVE
                );

                user = userRepository.save(user);
            }

            demoUsers.add(user);
        }


        // ------------------------------------------------------------
        // DEMO PRODUCTS
        // ------------------------------------------------------------

        createDemoProducts();


        List<Product> products =
                productRepository.findAll();

        List<ProductVariant> variants =
                productVariantRepository.findAll();


        if (products.size() < 15) {
            throw new RuntimeException(
                    "Not enough products for demo data"
            );
        }

        if (variants.size() < 20) {
            throw new RuntimeException(
                    "Not enough product variants for demo data"
            );
        }


        // ------------------------------------------------------------
        // DEMO ORDERS
        // ------------------------------------------------------------

        createDemoOrders(
                demoUsers,
                variants
        );


        // ------------------------------------------------------------
        // DEMO SAMPLE REQUESTS
        // ------------------------------------------------------------

        createDemoSampleRequests(
                demoUsers,
                products
        );


        // ------------------------------------------------------------
        // DEMO BUSINESS INQUIRIES
        // ------------------------------------------------------------

        createDemoBusinessInquiries(
                demoUsers
        );
    }


    // ================================================================
    // DEMO PRODUCTS
    // ================================================================

    private void createDemoProducts() {

        if (productRepository.count() >= 15) {
            return;
        }


        List<Category> categories =
                categoryRepository.findAll();

        List<TechnicalInformation> technicalInfos =
                technicalInformationRepository.findAll();


        if (categories.size() < 3 ||
                technicalInfos.size() < 3) {

            throw new RuntimeException(
                    "Categories or technical information missing"
            );
        }


        String[][] productData = {

                {
                        "Oro di Sicilia",
                        "A rich and aromatic extra virgin olive oil from Sicily."
                },

                {
                        "Cuore di Umbria",
                        "Elegant Umbrian extra virgin olive oil with a delicate finish."
                },

                {
                        "Verde di Liguria",
                        "Light and fragrant extra virgin olive oil from Liguria."
                },

                {
                        "Terre di Calabria",
                        "Full-bodied extra virgin olive oil with fresh green notes."
                },

                {
                        "Frantoio Toscano",
                        "Traditional Tuscan extra virgin olive oil with a robust character."
                },

                {
                        "Olio delle Colline",
                        "Smooth Italian extra virgin olive oil with fruity aromas."
                },

                {
                        "Primavera Italiana",
                        "Fresh and balanced extra virgin olive oil for everyday cooking."
                },

                {
                        "Riserva del Sud",
                        "Premium extra virgin olive oil with a rich Mediterranean profile."
                },

                {
                        "Luce di Puglia",
                        "Fruity extra virgin olive oil produced in the heart of Puglia."
                },

                {
                        "Essenza Verde",
                        "Organic Italian olive oil with fresh herbal aromas."
                },

                {
                        "Agrumato al Limone",
                        "Extra virgin olive oil naturally flavoured with Italian lemon."
                },

                {
                        "Agrumato al Peperoncino",
                        "Extra virgin olive oil naturally flavoured with chilli pepper."
                }
        };


        List<Product> newProducts =
                new ArrayList<>();


        int currentCount =
                (int) productRepository.count();


        for (
                int i = 0;
                i < productData.length &&
                        currentCount + newProducts.size() < 15;
                i++
        ) {

            Category category;

            if (i >= 10) {
                category = categories.get(2);
            } else if (i % 3 == 0) {
                category = categories.get(1);
            } else {
                category = categories.get(0);
            }


            TechnicalInformation technicalInfo =
                    technicalInfos.get(
                            i % technicalInfos.size()
                    );


            Product product = new Product(
                    productData[i][0],
                    productData[i][1],
                    "https://images.unsplash.com/photo-1474979266404-7eaacbcd87c5",
                    ActiveStatus.ACTIVE,
                    category,
                    technicalInfo
            );

            newProducts.add(product);
        }


        productRepository.saveAll(newProducts);


        // Create variants for newly created products

        List<ProductVariant> newVariants =
                new ArrayList<>();


        for (Product product : newProducts) {

            newVariants.add(
                    new ProductVariant(
                            "250 ml",
                            new BigDecimal("9.50"),
                            null,
                            ActiveStatus.ACTIVE,
                            product
                    )
            );

            newVariants.add(
                    new ProductVariant(
                            "500 ml",
                            new BigDecimal("15.50"),
                            null,
                            ActiveStatus.ACTIVE,
                            product
                    )
            );
        }


        productVariantRepository.saveAll(
                newVariants
        );
    }


    // ================================================================
    // DEMO ORDERS
    // ================================================================

    private void createDemoOrders(
            List<User> users,
            List<ProductVariant> variants
    ) {

        for (int i = 0; i < 10; i++) {

            User user = users.get(i);


            boolean alreadyExists =
                    orderRepository
                            .findByUserOrderByOrderDateDesc(user)
                            .stream()
                            .anyMatch(order ->
                                    order.getCustomerEmail()
                                            .equals(user.getEmail())
                            );


            if (alreadyExists) {
                continue;
            }


            ProductVariant variant =
                    variants.get(i % variants.size());


            int quantity =
                    (i % 3) + 1;


            BigDecimal total =
                    variant.getPrice()
                            .multiply(
                                    BigDecimal.valueOf(quantity)
                            );


            OrderStatus orderStatus;

            switch (i) {
                case 0:
                    orderStatus = OrderStatus.PENDING;
                    break;

                case 1:
                case 2:
                    orderStatus = OrderStatus.CONFIRMED;
                    break;

                case 3:
                case 4:
                    orderStatus = OrderStatus.SHIPPED;
                    break;

                case 5:
                case 6:
                case 7:
                    orderStatus = OrderStatus.DELIVERED;
                    break;

                case 8:
                    orderStatus = OrderStatus.CANCELLED;
                    break;

                default:
                    orderStatus = OrderStatus.DELIVERED;
            }


            PaymentStatus paymentStatus =
                    orderStatus == OrderStatus.CANCELLED
                            ? PaymentStatus.REFUNDED
                            : PaymentStatus.PAID;


            PaymentMethod paymentMethod;

            switch (i % 3) {
                case 0:
                    paymentMethod =
                            PaymentMethod.CREDIT_CARD;
                    break;

                case 1:
                    paymentMethod =
                            PaymentMethod.PAYPAL;
                    break;

                default:
                    paymentMethod =
                            PaymentMethod.BANK_TRANSFER;
            }


            LocalDateTime orderDate =
                    LocalDateTime.now()
                            .minusDays(
                                    2L + (i * 3L)
                            );


            String recipient =
                    user.getName() +
                            " " +
                            user.getSurname();


            Order order = new Order(
                    user,
                    user.getEmail(),
                    orderDate,
                    total,
                    orderStatus,
                    paymentMethod,
                    paymentStatus,

                    // SHIPPING
                    recipient,
                    "100-" + String.format(
                            "%04d",
                            i + 1
                    ),
                    "Tokyo",
                    "Tokyo",
                    "Shibuya",
                    "1-" + (i + 1) + "-10",
                    "OliveBridge Building",

                    // BILLING
                    "100-" + String.format(
                            "%04d",
                            i + 1
                    ),
                    "Tokyo",
                    "Tokyo",
                    "Shibuya",
                    "1-" + (i + 1) + "-10",
                    "OliveBridge Building"
            );


            order = orderRepository.save(order);


            OrderItem orderItem =
                    new OrderItem(
                            quantity,
                            variant.getPrice(),
                            order,
                            variant
                    );


            orderItemRepository.save(orderItem);
        }
    }


    // ================================================================
    // DEMO SAMPLE REQUESTS
    // ================================================================

    private void createDemoSampleRequests(
            List<User> users,
            List<Product> products
    ) {

        List<User> businessUsers =
                users.stream()
                        .filter(user ->
                                user.getAccountType()
                                        == AccountType.BUSINESS
                        )
                        .toList();


        SampleRequestStatus[] statuses = {
                SampleRequestStatus.PENDING,
                SampleRequestStatus.APPROVED,
                SampleRequestStatus.REJECTED,
                SampleRequestStatus.SHIPPED,
                SampleRequestStatus.COMPLETED
        };


        String[] messages = {
                "We would like to receive a sample for evaluation.",
                "Could you please send us a sample for our purchasing team?",
                "We are interested in evaluating this product for our catalogue.",
                "We would like to test this olive oil before placing a larger order.",
                "Please send us a sample for our restaurant selection.",
                "We are considering this product for the Japanese market.",
                "Could you provide a sample for our internal tasting?",
                "We would like to evaluate this product for a potential partnership.",
                "Please send us a sample and product information.",
                "Our purchasing team would like to review this olive oil."
        };


        for (int i = 0; i < 10; i++) {

            User user =
                    businessUsers.get(
                            i % businessUsers.size()
                    );


            Product product =
                    products.get(
                            i % products.size()
                    );


            boolean alreadyExists =
                    sampleRequestRepository
                            .findByUser(user)
                            .stream()
                            .anyMatch(request ->
                                    request.getProduct()
                                            .getProductId()
                                            .equals(
                                                    product.getProductId()
                                            )
                            );


            if (alreadyExists) {
                continue;
            }


            SampleRequest request =
                    new SampleRequest(
                            user,
                            product,
                            messages[i],
                            statuses[i % statuses.length],
                            LocalDateTime.now()
                                    .minusDays(
                                            1L + i
                                    ),

                            user.getName() +
                                    " " +
                                    user.getSurname(),

                            "150-" +
                                    String.format(
                                            "%04d",
                                            i + 1
                                    ),

                            "Tokyo",
                            "Tokyo",
                            "Shibuya",
                            "1-" + (i + 1) + "-10",
                            "OliveBridge Building"
                    );


            sampleRequestRepository.save(
                    request
            );
        }
    }


    // ================================================================
    // DEMO BUSINESS INQUIRIES
    // ================================================================

    private void createDemoBusinessInquiries(
            List<User> users
    ) {

        List<User> businessUsers =
                users.stream()
                        .filter(user ->
                                user.getAccountType()
                                        == AccountType.BUSINESS
                        )
                        .toList();


        BusinessInquiryStatus[] statuses = {
                BusinessInquiryStatus.PENDING,
                BusinessInquiryStatus.IN_PROGRESS,
                BusinessInquiryStatus.RESOLVED,
                BusinessInquiryStatus.CLOSED
        };


        String[] subjects = {
                "Wholesale partnership",
                "Product catalogue request",
                "Distribution opportunities",
                "Pricing for bulk orders",
                "Restaurant supply inquiry",
                "Japanese market distribution",
                "Long-term purchasing agreement",
                "Product documentation",
                "Private label opportunity",
                "Import and logistics inquiry"
        };


        String[] messages = {
                "We are interested in discussing a potential wholesale partnership.",
                "Could you send us your current product catalogue?",
                "We would like to discuss distribution opportunities in Japan.",
                "Please provide information about pricing for larger quantities.",
                "We are looking for a supplier for our restaurant group.",
                "We are interested in distributing Italian olive oil in Japan.",
                "Could we discuss a long-term purchasing agreement?",
                "Please provide the available product documentation.",
                "We would like to discuss a possible private label project.",
                "Could you provide information about import and logistics?"
        };


        for (int i = 0; i < 10; i++) {

            User user =
                    businessUsers.get(
                            i % businessUsers.size()
                    );

            String subject = subjects[i];

            boolean alreadyExists =
                    businessInquiryRepository
                            .findByUser(user)
                            .stream()
                            .anyMatch(inquiry ->
                                    inquiry.getSubject()
                                            .equals(subject)
                            );

            if (alreadyExists) {
                continue;
            }

            BusinessInquiry inquiry =
                    new BusinessInquiry(
                            user,
                            subject,
                            messages[i],
                            statuses[
                                    i % statuses.length
                                    ],
                            LocalDateTime.now()
                                    .minusDays(
                                            1L + i
                                    )
                    );

            businessInquiryRepository.save(
                    inquiry
            );
        }
    }
}