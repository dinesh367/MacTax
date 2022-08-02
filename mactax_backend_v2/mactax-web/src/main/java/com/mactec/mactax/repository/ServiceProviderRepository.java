package com.mactec.mactax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mactec.mactax.model.ServiceProvider;

/**
 * 
 * @author akshayp
 *
 */
@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Integer> {

}
