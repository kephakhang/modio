package com.youngplussoft.modio.jpa.repository;

import java.util.List;
import com.youngplussoft.modio.jpa.entity.*;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.annotation.Resource;

@RepositoryRestResource(path = "image")
public interface ImageRepository extends MongoRepository<Image, String> {

    public Document findById(ObjectId id) ;

    public List<Document> findByUserId(@Param("userId") String userId) ;

}