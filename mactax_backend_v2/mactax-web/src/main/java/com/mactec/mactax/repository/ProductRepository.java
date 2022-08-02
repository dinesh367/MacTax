package com.mactec.mactax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mactec.mactax.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByDomain(String domainName);

}
