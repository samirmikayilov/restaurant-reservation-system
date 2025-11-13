package az.samir.restaurant_reservation.security;

import az.samir.restaurant_reservation.service.SecurityUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final SecurityUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1️⃣ Header-dən Authorization götür
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String username = null;
        String token = null;

        // 2️⃣ Token yoxdursa → növbəti filtre keç
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3️⃣ Token-i kəs
        token = authHeader.substring(7);  // "Bearer " çıxdı

        try {
            // 4️⃣ Token-dən username çıxart
            username = jwtService.extractUsername(token);
        } catch (Exception e) {
            // Token pozulubsa → 401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 5️⃣ Username var və SecurityContext boşdursa
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // DB-dən user-i tap
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 6️⃣ Token keçərlidirmi?
            if (jwtService.isTokenValid(token)) {

                // 7️⃣ Security sisteminə user-i yerləşdiririk
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 8️⃣ SecurityContext-ə user-i qoy
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 9️⃣ Filtr zəncirinə davam et
        filterChain.doFilter(request, response);
    }
}
