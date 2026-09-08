package valeriafarinosi.olive_bridge.controllers;

import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.SampleRequestStatusRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.SampleRequestResponseDTO;
import valeriafarinosi.olive_bridge.services.SampleRequestService;

import java.util.List;
import java.util.UUID;

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

    @PatchMapping("/{sampleRequestId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public SampleRequestResponseDTO updateStatus(
            @PathVariable UUID sampleRequestId,
            @Valid @RequestBody SampleRequestStatusRequestDTO body
    ) {
        return sampleRequestService.updateStatus(
                sampleRequestId,
                body
        );
    }
}