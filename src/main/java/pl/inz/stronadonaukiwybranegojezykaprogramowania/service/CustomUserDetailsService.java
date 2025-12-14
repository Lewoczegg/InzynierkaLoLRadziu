package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.inz.stronadonaukiwybranegojezykaprogramowania.adapter.UserRepositoryAdapter;

@Service
//@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepositoryAdapter userRepositoryAdapter;

    public CustomUserDetailsService(UserRepositoryAdapter userRepositoryAdapter) {
        this.userRepositoryAdapter = userRepositoryAdapter;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositoryAdapter.findByUsername(username);
    }
}