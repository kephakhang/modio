package com.youngplussoft.modio.jpa.repository;


import com.youngplussoft.modio.jpa.entity.*;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.annotation.Resource;

@RepositoryRestResource(path = "user")
public interface UserRepository extends MongoRepository<User, String> {

    public User findById(ObjectId id);

    public User findByMobile(@Param("mobile") String mobile);

    public User findByEmail(@Param("email") String email);

    public User findByEmailAndEmailKey(@Param("email") String email, @Param("emailKey") String key);

    public User findByEmailAndMobileKey(@Param("email") String email, @Param("mobileKey") String key);

    public User findByEmailAndAccessToken(@Param("email") String email, @Param("accessToken") String accessToken);

    public User findByAccessToken(@Param("accessToken") String accessToken);
}