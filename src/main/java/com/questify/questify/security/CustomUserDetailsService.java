package com.questify.questify.security;

import com.questify.questify.domain.user.User;
import com.questify.questify.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }
    List<GrantedAuthority> authorityList = Collections.singletonList(
        new SimpleGrantedAuthority("ROLE_" + user.get().getUserType().name())
    );
    return org.springframework.security.core.userdetails.User
        .withUsername(user.get().getUsername())
        .password("")
        .authorities(authorityList)
        .build();
  }
}
