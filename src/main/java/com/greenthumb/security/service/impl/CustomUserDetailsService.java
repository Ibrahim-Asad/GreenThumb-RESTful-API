package com.greenthumb.security.service.impl;

import com.greenthumb.security.model.entity.UserEntity;
import com.greenthumb.security.model.mapper.UserEntityMapper;
import com.greenthumb.security.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserEntityMapper userEntityMapper;


    @Autowired
    public CustomUserDetailsService(UserRepo userRepo){
        this.userRepo=userRepo;
        this.userEntityMapper = UserEntityMapper.INSTANCE;
    }
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return userEntityMapper.toUserDetails(user);
    }

}

