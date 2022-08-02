package com.mactec.mactax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mactec.mactax.model.Users;

/**
 * 
 * @author akshayp
 *
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);

}
