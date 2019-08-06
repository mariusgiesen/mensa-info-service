package org.ict.mensainfoservice.service;

import org.ict.mensainfoservice.entity.CustomUser;
import org.ict.mensainfoservice.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomUserRepository customUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUser user = customUserRepository.findByUsername(username);

        if(user == null) throw new UsernameNotFoundException(username);
        return user;
    }
}
