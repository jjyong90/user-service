package com.staxter.userservice.config.security;

import com.staxter.userservice.model.User;
import com.staxter.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return userRepository.findByUserName(username)
        .map(this::buildUserDetails)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }

  private UserDetails buildUserDetails(User user) {
    return org.springframework.security.core.userdetails.User
        .withUsername(user.getUserName())
        .password(user.getHashedPassword())
        .roles("USER")
        .build();
  }

}
