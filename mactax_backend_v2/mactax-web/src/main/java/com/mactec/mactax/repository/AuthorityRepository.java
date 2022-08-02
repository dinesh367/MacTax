package com.mactec.mactax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mactec.mactax.model.Authority;

/**
 * 
 * @author akshayp
 *
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Authority findByRole(String role);

}
