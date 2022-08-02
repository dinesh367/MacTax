package com.mactec.mactax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mactec.mactax.model.UserAuthority;
import com.mactec.mactax.model.Users;

/**
 * 
 * @author akshayp
 *
 */
@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Integer> {

    List<UserAuthority> findByUser(Users user);

}
