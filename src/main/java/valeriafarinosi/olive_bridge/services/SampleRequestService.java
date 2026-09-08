package valeriafarinosi.olive_bridge.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.Product;
import valeriafarinosi.olive_bridge.entities.SampleRequest;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.enums.AccountType;
import valeriafarinosi.olive_bridge.enums.SampleRequestStatus;
import valeriafarinosi.olive_bridge.exceptions.BadRequestException;
import valeriafarinosi.olive_bridge.exceptions.NotFoundException;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.SampleRequestReplyRequestDTO;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.SampleRequestRequestDTO;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.SampleRequestStatusRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.SampleRequestResponseDTO;
import valeriafarinosi.olive_bridge.repositories.ProductRepository;
import valeriafarinosi.olive_bridge.repositories.SampleRequestRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SampleRequestService {

    private final SampleRequestRepository sampleRequestRepository;
    private final ProductRepository productRepository;
    private final MailgunService mailgunService;

    public SampleRequestService(
            SampleRequestRepository sampleRequestRepository,
            ProductRepository productRepository,
            MailgunService mailgunService
    ) {
        this.sampleRequestRepository = sampleRequestRepository;
        this.productRepository = productRepository;
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

    public List<SampleRequestResponseDTO> getMySampleRequests() {
        User currentUser = getCurrentUser();

        return sampleRequestRepository.findByUser(currentUser)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public List<SampleRequestResponseDTO> getAllSampleRequests() {
        return sampleRequestRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public SampleRequestResponseDTO createSampleRequest(
            SampleRequestRequestDTO body
    ) {
        User currentUser = getCurrentUser();

        checkBusinessAccount(currentUser);

        Product product = productRepository.findById(body.productId())
                .orElseThrow(() ->
                        new NotFoundException("Product not found.")
                );

        SampleRequest sampleRequest = new SampleRequest(
                currentUser,
                product,
                body.message(),
                SampleRequestStatus.PENDING,
                LocalDateTime.now(),
                body.recipientName(),
                body.postalCode(),
                body.prefecture(),
                body.city(),
                body.area(),
                body.street(),
                body.building()
        );

        SampleRequest savedSampleRequest =
                sampleRequestRepository.save(sampleRequest);

        return toResponseDTO(savedSampleRequest);
    }

    public void replyToSampleRequest(
            UUID sampleRequestId,
            SampleRequestReplyRequestDTO body
    ) {

        SampleRequest sampleRequest =
                sampleRequestRepository.findById(sampleRequestId)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "Sample request not found."
                                )
                        );

        User user = sampleRequest.getUser();

        mailgunService.sendSampleRequestReply(
                user.getEmail(),
                body.message()
        );
    }

    private SampleRequestResponseDTO toResponseDTO(
            SampleRequest sampleRequest
    ) {
        return new SampleRequestResponseDTO(
                sampleRequest.getSampleRequestId(),
                sampleRequest.getProduct().getProductId(),
                sampleRequest.getMessage(),
                sampleRequest.getStatus(),
                sampleRequest.getCreatedAt(),
                sampleRequest.getRecipientName(),
                sampleRequest.getPostalCode(),
                sampleRequest.getPrefecture(),
                sampleRequest.getCity(),
                sampleRequest.getArea(),
                sampleRequest.getStreet(),
                sampleRequest.getBuilding()
        );
    }

    public SampleRequestResponseDTO updateStatus(
            UUID sampleRequestId,
            SampleRequestStatusRequestDTO body
    ) {

        SampleRequest sampleRequest =
                sampleRequestRepository.findById(sampleRequestId)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "Sample request not found."
                                )
                        );

        SampleRequestStatus currentStatus =
                sampleRequest.getStatus();

        SampleRequestStatus newStatus =
                body.status();

        boolean validTransition =
                (currentStatus == SampleRequestStatus.PENDING
                        && (newStatus == SampleRequestStatus.APPROVED
                        || newStatus == SampleRequestStatus.REJECTED))
                        ||
                        (currentStatus == SampleRequestStatus.APPROVED
                                && newStatus == SampleRequestStatus.SHIPPED)
                        ||
                        (currentStatus == SampleRequestStatus.SHIPPED
                                && newStatus == SampleRequestStatus.COMPLETED);

        if (!validTransition) {
            throw new BadRequestException(
                    "Invalid status transition."
            );
        }

        sampleRequest.setStatus(newStatus);

        SampleRequest updatedSampleRequest =
                sampleRequestRepository.save(sampleRequest);

        return toResponseDTO(updatedSampleRequest);
    }
}