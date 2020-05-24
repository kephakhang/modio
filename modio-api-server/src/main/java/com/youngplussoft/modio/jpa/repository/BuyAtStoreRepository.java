package com.youngplussoft.modio.jpa.repository;

import java.util.List;
import com.youngplussoft.modio.jpa.entity.*;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.annotation.Resource;

@RepositoryRestResource(path = "buyAtStore")
public interface BuyAtStoreRepository extends MongoRepository<BuyAtStore, String> {


    public Page<BuyAtStore> findByUserId(String userId, Pageable pageable) ;
}