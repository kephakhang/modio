package com.youngplussoft.modio.jpa.repository;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.youngplussoft.modio.jpa.entity.Position;
import com.youngplussoft.modio.jpa.entity.Store;
import com.youngplussoft.modio.jpa.entity.StoreBookmark;
import com.youngplussoft.modio.jpa.entity.StoreCategory;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.annotation.Resource;
import java.util.List;

@RepositoryRestResource(path = "storeBookmark")
public interface StoreBookmarkRepository  extends MongoRepository<StoreBookmark, String> {


    public Page<StoreBookmark> findByUserId(String userId, Pageable pageable);
}