package az.samir.restaurant_reservation.controller;

import az.samir.restaurant_reservation.entity.AppUser;
import az.samir.restaurant_reservation.entity.Role;
import az.samir.restaurant_reservation.repository.RoleRepository;
import az.samir.restaurant_reservation.repository.UserRepository;
import az.samir.restaurant_reservation.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // ✅ 1) REGISTER
    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        // Yeni user yaradılır
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // BCrypt ilə encode

        // ROLE_USER təyin olunur
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));

        user.getRoles().add(role);

        userRepository.save(user);

        return "User registered successfully";
    }

    // ✅ 2) LOGIN → JWT qaytarır
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {

        String username = body.get("username");
        String password = body.get("password");

        // Login yoxlanır
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        // Token yaradılır
        String token = jwtService.generateToken(username);

        AppUser user = userRepository.findByUsername(username).get();
        String role = user.getRoles().iterator().next().getName();

        return Map.of(
                "token", token,
                "role", role
        );
    }
}
