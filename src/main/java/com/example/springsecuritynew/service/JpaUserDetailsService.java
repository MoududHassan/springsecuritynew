package com.example.springsecuritynew.service;

import com.example.springsecuritynew.entity.User;
import com.example.springsecuritynew.repository.UserRepository;
import com.example.springsecuritynew.security.SecurityUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUserName(username);
        UserDetails userDetails =  user.map(SecurityUser::new).orElseThrow(()-> new UsernameNotFoundException("User not found."));
        log.info("inside"+userDetails.getUsername()+":"+userDetails.getPassword());
        return userDetails;
    }
}
