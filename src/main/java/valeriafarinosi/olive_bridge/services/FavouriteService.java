package valeriafarinosi.olive_bridge.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import valeriafarinosi.olive_bridge.entities.Favourite;
import valeriafarinosi.olive_bridge.entities.Product;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.exceptions.BadRequestException;
import valeriafarinosi.olive_bridge.exceptions.NotFoundException;
import valeriafarinosi.olive_bridge.repositories.FavouriteRepository;
import valeriafarinosi.olive_bridge.repositories.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final ProductRepository productRepository;

    public FavouriteService(
            FavouriteRepository favouriteRepository,
            ProductRepository productRepository
    ) {
        this.favouriteRepository = favouriteRepository;
        this.productRepository = productRepository;
    }

    private User getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return (User) authentication.getPrincipal();
    }

    public List<Favourite> getMyFavourites() {
        User currentUser = getCurrentUser();

        return favouriteRepository.findByUser(currentUser);
    }

    public Favourite addFavourite(UUID productId) {
        User currentUser = getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new NotFoundException("Product not found.")
                );

        if (favouriteRepository
                .findByUserAndProduct(currentUser, product)
                .isPresent()) {

            throw new BadRequestException(
                    "Product is already in favourites."
            );
        }

        Favourite favourite = new Favourite(
                currentUser,
                product,
                LocalDateTime.now()
        );

        return favouriteRepository.save(favourite);
    }

    public void removeFavourite(UUID productId) {
        User currentUser = getCurrentUser();

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new NotFoundException("Product not found.")
                );

        Favourite favourite = favouriteRepository
                .findByUserAndProduct(currentUser, product)
                .orElseThrow(() ->
                        new NotFoundException("Favourite not found.")
                );

        favouriteRepository.delete(favourite);
    }
}