package com.scalerkart.ecommerce.services.authentication.security.userprinciple;

import com.scalerkart.ecommerce.services.authentication.exception.wrapper.EmailOrUsernameNotFoundException;
import com.scalerkart.ecommerce.services.authentication.model.entity.User;
import com.scalerkart.ecommerce.services.authentication.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EmailOrUsernameNotFoundException("Email or Username does not exist, please try again: " + username));

        return UserPrinciple.build(user);
    }

    @Transactional
    public UserDetails loadUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailOrUsernameNotFoundException("Email or Username does not exist, please try again: " + email));

        return UserPrinciple.build(user);
    }

    @Transactional
    public UserDetails loadUserByPhone(String phone) {
        User user = userRepository.findByEmail(phone)
                .orElseThrow(() -> new EmailOrUsernameNotFoundException("User not found, phone and password: " + phone));

        return UserPrinciple.build(user);
    }
}
