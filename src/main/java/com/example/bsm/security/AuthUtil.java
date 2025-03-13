package com.example.bsm.security;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.User;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AuthUtil {

    private  final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public String getCurrentUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getCurrentUser() throws Exception{

        return userRepository.findByEmail(this.getCurrentUserName())
                 .orElseThrow(()-> new UsernameNotFoundException("Failed to authenticate"));

    }
    public Admin getCurrentAdmin() throws Exception{
        return adminRepository.findByUser_Email(this.getCurrentUserName())
                .orElseThrow(()-> new UsernameNotFoundException("Failed to authenticate"));
    }
}
