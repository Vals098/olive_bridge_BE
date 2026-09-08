package valeriafarinosi.olive_bridge.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.olive_bridge.payloads.responseDTOs.FavouriteResponseDTO;
import valeriafarinosi.olive_bridge.services.FavouriteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/favourites")
public class FavouriteController {

    private final FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @GetMapping
    public List<FavouriteResponseDTO> getMyFavourites() {
        return favouriteService.getMyFavourites();
    }

    @PostMapping("/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public FavouriteResponseDTO addFavourite(@PathVariable UUID productId) {
        return favouriteService.addFavourite(productId);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFavourite(@PathVariable UUID productId) {
        favouriteService.removeFavourite(productId);
    }
}