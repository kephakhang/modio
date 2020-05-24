package com.youngplussoft.modio.jpa.repository;

import java.util.List;
import com.youngplussoft.modio.jpa.entity.*;


import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.annotation.Resource;

@RepositoryRestResource(path = "storeCategory")
public interface StoreCategoryRepository extends MongoRepository<StoreCategory, String> {


    public StoreCategory findById(ObjectId id) ;
}