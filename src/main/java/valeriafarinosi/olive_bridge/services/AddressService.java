package valeriafarinosi.olive_bridge.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.Address;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.exceptions.NotFoundException;
import valeriafarinosi.olive_bridge.payloads.requestDTOs.AddressRequestDTO;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.AddressResponseDTO;
import valeriafarinosi.olive_bridge.repositories.AddressRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    private User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

    public List<AddressResponseDTO> getMyAddresses() {
        User currentUser = getCurrentUser();

        return addressRepository.findByUser(currentUser)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public AddressResponseDTO createAddress(AddressRequestDTO body) {
        User currentUser = getCurrentUser();

        Address address = new Address(
                currentUser,
                body.label(),
                body.recipientName(),
                body.postalCode(),
                body.prefecture(),
                body.city(),
                body.area(),
                body.street(),
                body.building()
        );

        Address savedAddress = addressRepository.save(address);

        return toResponseDTO(savedAddress);
    }

    private AddressResponseDTO toResponseDTO(Address address) {
        return new AddressResponseDTO(
                address.getAddressId(),
                address.getLabel(),
                address.getRecipientName(),
                address.getPostalCode(),
                address.getPrefecture(),
                address.getCity(),
                address.getArea(),
                address.getStreet(),
                address.getBuilding()
        );
    }

    public AddressResponseDTO updateAddress(
            UUID addressId,
            AddressRequestDTO body
    ) {
        User currentUser = getCurrentUser();

        Address address = addressRepository
                .findByAddressIdAndUser(addressId, currentUser)
                .orElseThrow(() ->
                        new NotFoundException("Address not found.")
                );

        address.update(
                body.label(),
                body.recipientName(),
                body.postalCode(),
                body.prefecture(),
                body.city(),
                body.area(),
                body.street(),
                body.building()
        );
        Address updatedAddress = addressRepository.save(address);

        return toResponseDTO(updatedAddress);
    }

    public void deleteAddress(UUID addressId) {
        User currentUser = getCurrentUser();

        Address address = addressRepository
                .findByAddressIdAndUser(addressId, currentUser)
                .orElseThrow(() ->
                        new NotFoundException("Address not found.")
                );

        addressRepository.delete(address);
    }
}