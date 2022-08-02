package com.mactec.mactax.service;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mactec.mactax.model.Users;
import com.mactec.mactax.repository.UserAuthorityRepository;
import com.mactec.mactax.repository.UserRepository;

/**
 * 
 * @author akshaylap
 *
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(login);
        // String code = WebAuthenticationDetails.getVerificationCode();
        if (user != null) {
            String roles = userAuthorityRepository.findByUser(user).stream().map(ua -> ua.getAuthority().getRole()).collect(Collectors.joining(","));
            return User.withUsername(user.getUsername()).password(user.getPassword()).roles(roles).disabled(!user.isEnabled()).build();
        }
        return null;
    }

}
