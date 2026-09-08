package valeriafarinosi.olive_bridge.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.BusinessInquiry;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.enums.AccountType;
import valeriafarinosi.olive_bridge.enums.BusinessInquiryStatus;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.BusinessInquiryReplyRequestDTO;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.BusinessInquiryRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.BusinessInquiryResponseDTO;
import valeriafarinosi.olive_bridge.repositories.BusinessInquiryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BusinessInquiryService {

    private final BusinessInquiryRepository businessInquiryRepository;
    private final MailgunService mailgunService;

    public BusinessInquiryService(
            BusinessInquiryRepository businessInquiryRepository,
            MailgunService mailgunService
    ) {
        this.businessInquiryRepository = businessInquiryRepository;
        this.mailgunService = mailgunService;
    }

    private User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

    private void checkBusinessAccount(User user) {
        if (user.getAccountType() != AccountType.BUSINESS) {
            throw new BadRequestException(
                    "This feature is reserved for business accounts."
            );
        }
    }

    public List<BusinessInquiryResponseDTO> getMyInquiries() {
        User currentUser = getCurrentUser();

        return businessInquiryRepository.findByUser(currentUser)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<BusinessInquiryResponseDTO> getAllBusinessInquiries() {
        return businessInquiryRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public BusinessInquiryResponseDTO createInquiry(
            BusinessInquiryRequestDTO body
    ) {
        User currentUser = getCurrentUser();

        checkBusinessAccount(currentUser);

        BusinessInquiry inquiry = new BusinessInquiry(
                currentUser,
                body.subject(),
                body.message(),
                BusinessInquiryStatus.PENDING,
                LocalDateTime.now()
        );

        BusinessInquiry savedInquiry =
                businessInquiryRepository.save(inquiry);

        return toResponseDTO(savedInquiry);
    }

    public BusinessInquiryResponseDTO replyToInquiry(
            UUID businessInquiryId,
            BusinessInquiryReplyRequestDTO body
    ) {

        BusinessInquiry inquiry =
                businessInquiryRepository.findById(businessInquiryId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Business inquiry not found."
                                )
                        );

        User user = inquiry.getUser();

        mailgunService.sendBusinessInquiryReply(
                user.getEmail(),
                body.message()
        );

        inquiry.setStatus(BusinessInquiryStatus.IN_PROGRESS);

        BusinessInquiry updatedInquiry =
                businessInquiryRepository.save(inquiry);

        return toResponseDTO(updatedInquiry);
    }

    private BusinessInquiryResponseDTO toResponseDTO(
            BusinessInquiry inquiry
    ) {
        return new BusinessInquiryResponseDTO(
                inquiry.getBusinessInquiryId(),
                inquiry.getSubject(),
                inquiry.getMessage(),
                inquiry.getStatus(),
                inquiry.getCreatedAt()
        );
    }
}