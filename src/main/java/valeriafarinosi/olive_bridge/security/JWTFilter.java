package valeriafarinosi.olive_bridge.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import valeriafarinosi.olive_bridge.entities.User;
import valeriafarinosi.olive_bridge.exceptions.UnauthorizedException;
import valeriafarinosi.olive_bridge.services.UserService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new UnauthorizedException(
                        "Please include the token in the Authorization header using the Bearer format."
                );
            }

            String accessToken = authHeader.substring(7);

            // 1. Validate the token
            jwtTools.verifyToken(accessToken);

            // 2. Extract the user ID and search the database
            String id = jwtTools.extractIdFromToken(accessToken);
            User currentUser = userService.findById(UUID.fromString(id));

            // 3. Map the user's role to Spring Security's GrantedAuthority
            List<SimpleGrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority(currentUser.getRole().getName())
            );

            // 4. Create the authentication object
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            currentUser,
                            null,
                            authorities
                    );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            resolver.resolveException(request, response, null, ex);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        AntPathMatcher matcher = new AntPathMatcher();

        return matcher.match("/auth/**", path)
                || matcher.match("/products/**", path)
                || matcher.match("/orders/checkout", path);
    }
}
