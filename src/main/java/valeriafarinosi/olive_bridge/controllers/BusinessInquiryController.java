package valeriafarinosi.olive_bridge.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.BusinessInquiryRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.BusinessInquiryResponseDTO;
import valeriafarinosi.olive_bridge.services.BusinessInquiryService;

import java.util.List;

@RestController
@RequestMapping("/users/business-inquiries")
public class BusinessInquiryController {

    private final BusinessInquiryService businessInquiryService;

    public BusinessInquiryController(
            BusinessInquiryService businessInquiryService
    ) {
        this.businessInquiryService = businessInquiryService;
    }

    @GetMapping
    public List<BusinessInquiryResponseDTO> getMyInquiries() {
        return businessInquiryService.getMyInquiries();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessInquiryResponseDTO createInquiry(
            @Valid @RequestBody BusinessInquiryRequestDTO body
    ) {
        return businessInquiryService.createInquiry(body);
    }
}