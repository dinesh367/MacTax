package com.mactec.mactax.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mactec.mactax.model.Organization;
import com.mactec.mactax.model.Users;
import com.mactec.mactax.model.enums.Role;
import com.mactec.mactax.repository.UserRepository;
import com.mactec.mactax.service.exception.AuthenticationException;
import com.mactec.mactax.service.exception.ResourceNotFoundException;

/**
 * 
 * @author akshayp
 *
 */
@Component
public class AdminUtil {

    @Autowired
    private UserRepository usersRepository;

    /**
     * get logged in {@link UserDetails}
     * 
     * @return
     */
    public UserDetails getUser() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof UserDetails) {
            return user;
        }
        throw new AuthenticationException("user not logged In");
    }

    /**
     * get {@link Users} by username after login
     * 
     * @return
     */
    public Users getCurrenUser() {
        Users admin = null;
        UserDetails user = getUser();
        if (user != null) {
            admin = usersRepository.findByUsername(user.getUsername());
        }
        if (admin == null) {
            throw new ResourceNotFoundException("not records found with user name:" + user.getUsername());
        }
        return admin;
    }

    /**
     * get logged in user organization
     * 
     * @return
     */
    public Organization getCurrentOrganization() {
        Users admin = null;
        UserDetails user = getUser();
        if (user != null) {
            admin = usersRepository.findByUsername(user.getUsername());
        }
        return admin.getOrganization();
    }

    public boolean isTaxPayer() {
        UserDetails user = getUser();
        if (user != null) {
            if (user.getAuthorities().contains(new SimpleGrantedAuthority(Role.TAX_PAYER.getId()))) {
                return true;
            }
        }
        return false;
    }

    public boolean isTaxConsutant() {
        UserDetails user = getUser();
        if (user != null) {
            if (user.getAuthorities().contains(new SimpleGrantedAuthority(Role.TAX_CONSULTANT.getId()))) {
                return true;
            }
        }
        return false;
    }

    public boolean isAdmin() {
        UserDetails user = getUser();
        if (user != null) {
            if (user.getAuthorities().contains(new SimpleGrantedAuthority(Role.ADMIN.getId()))) {
                return true;
            }
        }
        return false;
    }

    public boolean isSuperAdmin() {
        UserDetails user = getUser();
        if (user != null) {
            if (user.getAuthorities().contains(new SimpleGrantedAuthority(Role.SUPER_ADMIN.getId()))) {
                return true;
            }
        }
        return false;
    }

}
