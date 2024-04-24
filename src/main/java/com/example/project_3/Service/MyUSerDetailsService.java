package com.example.project_3.Service;

import com.example.project_3.API.ApiException;
import com.example.project_3.Model.User;
import com.example.project_3.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUSerDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
       User user =authRepository.findUserByUsername(username);
       if (user==null) throw new ApiException("wrong username or password");
       return user;
    }
}
