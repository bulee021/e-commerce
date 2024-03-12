package com.xsis.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xsis.ecommerce.entities.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

}
