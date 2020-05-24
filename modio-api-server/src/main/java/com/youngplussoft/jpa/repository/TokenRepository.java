package com.youngplussoft.jpa.repository;

import com.youngplussoft.jpa.entity.*;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "token")
public interface TokenRepository extends MongoRepository<Token, String> {
    Token findByClientAndClientSecret(@Param("client") String client, @Param("secret") String secret) ;
    Token findByToken(@Param("token") String token) ;

}