package valeriafarinosi.olive_bridge.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.olive_bridge.entities.TechnicalInformation;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.TechnicalInformationRequestDTO;
import valeriafarinosi.olive_bridge.services.TechnicalInformationService;

@RestController
@RequestMapping("/admin/technical-information")
public class AdminTechnicalInformationController {

    private final TechnicalInformationService technicalInformationService;

    public AdminTechnicalInformationController(
            TechnicalInformationService technicalInformationService
    ) {
        this.technicalInformationService = technicalInformationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public TechnicalInformation createTechnicalInformation(
            @Valid @RequestBody TechnicalInformationRequestDTO body
    ) {
        return technicalInformationService.createTechnicalInformation(body);
    }
}