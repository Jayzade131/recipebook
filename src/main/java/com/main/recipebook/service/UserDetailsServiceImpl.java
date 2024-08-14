package com.main.recipebook.service;

import com.main.recipebook.constant.ErrorCodeEnum;
import com.main.recipebook.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username)  {
        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException( ErrorCodeEnum.USER_NOT_FOUND.getErrorMessage()));
    }
}
