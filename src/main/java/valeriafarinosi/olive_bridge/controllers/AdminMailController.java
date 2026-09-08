package valeriafarinosi.olive_bridge.controllers;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.BusinessInquiryReplyRequestDTO;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.SampleRequestReplyRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.BusinessInquiryResponseDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.SampleRequestResponseDTO;
import valeriafarinosi.olive_bridge.services.BusinessInquiryService;
import valeriafarinosi.olive_bridge.services.SampleRequestService;

import java.util.UUID;

@RestController
@RequestMapping("/admin/mail")
public class AdminMailController {

    private final SampleRequestService sampleRequestService;
    private final BusinessInquiryService businessInquiryService;

    public AdminMailController(
            SampleRequestService sampleRequestService,
            BusinessInquiryService businessInquiryService
    ) {
        this.sampleRequestService = sampleRequestService;
        this.businessInquiryService = businessInquiryService;
    }

    @PostMapping("/sample-request/{sampleRequestId}")
    @PreAuthorize("hasRole('ADMIN')")
    public SampleRequestResponseDTO replyToSampleRequest(
            @PathVariable UUID sampleRequestId,
            @Valid @RequestBody SampleRequestReplyRequestDTO body
    ) {
        return sampleRequestService.replyToSampleRequest(
                sampleRequestId,
                body
        );
    }

    @PostMapping("/business-inquiry/{businessInquiryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public BusinessInquiryResponseDTO replyToBusinessInquiry(
            @PathVariable UUID businessInquiryId,
            @Valid @RequestBody BusinessInquiryReplyRequestDTO body
    ) {
        return businessInquiryService.replyToInquiry(
                businessInquiryId,
                body
        );
    }
}