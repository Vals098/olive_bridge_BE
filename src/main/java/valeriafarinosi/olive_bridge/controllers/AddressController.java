package valeriafarinosi.olive_bridge.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.AddressRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.AddressResponseDTO;
import valeriafarinosi.olive_bridge.services.AddressService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressResponseDTO> getMyAddresses() {
        return addressService.getMyAddresses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponseDTO createAddress(
            @Valid @RequestBody AddressRequestDTO body
    ) {
        return addressService.createAddress(body);
    }

    @PutMapping("/{addressId}")
    public AddressResponseDTO updateAddress(
            @PathVariable UUID addressId,
            @Valid @RequestBody AddressRequestDTO body
    ) {
        return addressService.updateAddress(addressId, body);
    }

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(
            @PathVariable UUID addressId
    ) {
        addressService.deleteAddress(addressId);
    }
}