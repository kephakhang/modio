package com.youngplussoft.modio.jpa.template;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.repository.query.Param;

import java.util.List;

public class ImageTemplate extends BaseTemplate {

    public ImageTemplate(){
        super("image") ;
    }

    public Document findById(ObjectId id) {
        return mongoTemplate.findById(id, Document.class, this.collection) ;
    }

    public List<Document> findByUserId(@Param("userId") String userId) {

        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query() ;
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.find(query, Document.class, this.collection) ;
    }

}