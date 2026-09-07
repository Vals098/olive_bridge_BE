package valeriafarinosi.olive_bridge.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import valeriafarinosi.olive_bridge.entities.TechnicalInformation;
import valeriafarinosi.olive_bridge.services.TechnicalInformationService;

import java.util.List;

@RestController
@RequestMapping("/technical-information")
public class TechnicalInformationController {

    private final TechnicalInformationService technicalInformationService;

    public TechnicalInformationController(
            TechnicalInformationService technicalInformationService
    ) {
        this.technicalInformationService = technicalInformationService;
    }

    @GetMapping
    public List<TechnicalInformation> getAllTechnicalInformations() {
        return technicalInformationService.getAllTechnicalInformations();
    }
}