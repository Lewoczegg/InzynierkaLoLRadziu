package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.LoginRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.RegisterRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.UpdateUserRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.JwtResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.model.User;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.repository.UserRepository;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.AuthService;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserService userService;
    public AuthController(AuthService authService, UserRepository userRepository, UserService userService) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        User updatedUser = authService.updateUser(id, updateUserRequest);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        authService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = authService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user-info")
    public ResponseEntity<Map<String, Object>> getUserInfo() {
        try {
            Map<String, Object> userInfo = userService.getUserInfo();
            return ResponseEntity.ok(userInfo);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }

}