package com.youngplussoft.modio.jpa.repository;

import java.util.List;

import com.youngplussoft.modio.jpa.entity.*;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.annotation.Resource;

@RepositoryRestResource(path = "store")
public interface StoreRepository extends MongoRepository<Store, String> {


    public Page<Store> findByPositionNear(Point pos, Distance dist, Pageable pageable);
    public Store findById(ObjectId id) ;
}