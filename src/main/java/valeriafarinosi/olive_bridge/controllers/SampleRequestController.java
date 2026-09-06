package valeriafarinosi.olive_bridge.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.SampleRequestRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.SampleRequestResponseDTO;
import valeriafarinosi.olive_bridge.services.SampleRequestService;

import java.util.List;

@RestController
@RequestMapping("/users/sample-requests")
public class SampleRequestController {

    private final SampleRequestService sampleRequestService;

    public SampleRequestController(
            SampleRequestService sampleRequestService
    ) {
        this.sampleRequestService = sampleRequestService;
    }

    @GetMapping
    public List<SampleRequestResponseDTO> getMySampleRequests() {
        return sampleRequestService.getMySampleRequests();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SampleRequestResponseDTO createSampleRequest(
            @Valid @RequestBody SampleRequestRequestDTO body
    ) {
        return sampleRequestService.createSampleRequest(body);
    }
}