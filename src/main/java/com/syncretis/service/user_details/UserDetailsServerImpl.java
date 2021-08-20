package com.syncretis.service.user_details;

import com.syncretis.entity.User;
import com.syncretis.exception.UsernameNotFoundException;
import com.syncretis.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("userDetailsServerImpl")
public class UserDetailsServerImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(HttpStatus.NOT_FOUND));
        return SecurityUser.fromUser(user);
    }
}