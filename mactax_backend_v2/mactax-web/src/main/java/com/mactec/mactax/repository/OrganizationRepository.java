package com.mactec.mactax.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mactec.mactax.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    Organization findByName(String name);

    Optional<Organization> findById(Integer id);

}
