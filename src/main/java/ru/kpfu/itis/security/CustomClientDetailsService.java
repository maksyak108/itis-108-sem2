package ru.kpfu.itis.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.repository.shop.ClientRepository;

@AllArgsConstructor
@Service
public class CustomClientDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.getClientByName(username).map(CustomClientDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Client %s not found", username)));
    }
}