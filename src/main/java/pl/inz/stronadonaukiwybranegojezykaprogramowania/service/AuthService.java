package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.RegisterRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.request.UpdateUserRequest;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.enums.Title;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.domain.UserDomain;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.RoleRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.security.JwtTokenProvider;

import java.sql.Timestamp;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryAdapter userRepositoryAdapter;
    private final RoleRepositoryAdapter roleRepositoryAdapter;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserRepositoryAdapter userRepositoryAdapter, RoleRepositoryAdapter roleRepositoryAdapter) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.userRepositoryAdapter = userRepositoryAdapter;
        this.roleRepositoryAdapter = roleRepositoryAdapter;
    }

    public String login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return jwtTokenProvider.generateToken(authentication);
    }


    public UserDetails loadUserByUsername(String username) {
        return userDetailsService.loadUserByUsername(username);
    }

    public void register(RegisterRequest registerRequest) {
        UserDomain user = new UserDomain();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setSurname(registerRequest.getSurname());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setAge(registerRequest.getAge());
        user.setRole(roleRepositoryAdapter.findById(2L).get());
        user.setTitle(Title.BEGINNER);
        user.setLevel(1);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userRepositoryAdapter.save(user);
    }

    public UserDomain updateUser(Long id, UpdateUserRequest updateUserRequest) {
        UserDomain user = userRepositoryAdapter.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(updateUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        user.setEmail(updateUserRequest.getEmail());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setSurname(updateUserRequest.getSurname());
        user.setPhoneNumber(updateUserRequest.getPhoneNumber());
        user.setAge(updateUserRequest.getAge());
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userRepositoryAdapter.save(user);
        return user;
    }

    public void deleteUser(Long id) {
        userRepositoryAdapter.deleteById(id);
    }

    public UserDomain getUserById(Long id) {
        return userRepositoryAdapter.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
