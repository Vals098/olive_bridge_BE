package valeriafarinosi.olive_bridge.services;

import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.TechnicalInformation;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.TechnicalInformationRequestDTO;
import valeriafarinosi.olive_bridge.repositories.TechnicalInformationRepository;

import java.util.List;

@Service
public class TechnicalInformationService {

    private final TechnicalInformationRepository technicalInformationRepository;

    public TechnicalInformationService(
            TechnicalInformationRepository technicalInformationRepository
    ) {
        this.technicalInformationRepository = technicalInformationRepository;
    }

    public List<TechnicalInformation> getAllTechnicalInformations() {
        return technicalInformationRepository.findAll();
    }

    public TechnicalInformation createTechnicalInformation(
            TechnicalInformationRequestDTO body
    ) {
        TechnicalInformation technicalInformation =
                new TechnicalInformation(
                        body.acidity(),
                        body.peroxideValue(),
                        body.harvestDate(),
                        body.bestBeforeDate()
                );

        return technicalInformationRepository.save(technicalInformation);
    }
}