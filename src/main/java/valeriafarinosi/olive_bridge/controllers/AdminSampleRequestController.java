package valeriafarinosi.olive_bridge.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.SampleRequestResponseDTO;
import valeriafarinosi.olive_bridge.services.SampleRequestService;

import java.util.List;

@RestController
@RequestMapping("/admin/sample-requests")
public class AdminSampleRequestController {

    private final SampleRequestService sampleRequestService;

    public AdminSampleRequestController(
            SampleRequestService sampleRequestService
    ) {
        this.sampleRequestService = sampleRequestService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<SampleRequestResponseDTO> getAllSampleRequests() {
        return sampleRequestService.getAllSampleRequests();
    }
}