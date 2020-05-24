package com.youngplussoft.modio.jpa.repository;

import com.youngplussoft.modio.jpa.entity.Store;
import com.youngplussoft.modio.jpa.entity.StoreCategory;
import com.youngplussoft.modio.jpa.entity.StoreFrequent;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.annotation.Resource;
import java.util.List;

@RepositoryRestResource(path = "storeFrequent")
public interface StoreFrequentRepository extends MongoRepository<StoreFrequent, String> {

    public Page<StoreFrequent> findByUserId(String userId, Pageable pageable);
}