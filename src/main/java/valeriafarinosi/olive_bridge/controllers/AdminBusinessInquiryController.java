package valeriafarinosi.olive_bridge.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.BusinessInquiryResponseDTO;
import valeriafarinosi.olive_bridge.services.BusinessInquiryService;

import java.util.List;

@RestController
@RequestMapping("/admin/business-inquiries")
public class AdminBusinessInquiryController {
    private final BusinessInquiryService businessInquiryService;

    public AdminBusinessInquiryController(
            BusinessInquiryService businessInquiryService
    ) {
        this.businessInquiryService = businessInquiryService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<BusinessInquiryResponseDTO> getAllBusinessInquiries() {
        return businessInquiryService.getAllBusinessInquiries();
    }

}