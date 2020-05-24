package com.youngplussoft.modio.jpa.template;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.repository.support.PageableExecutionUtils;

import java.util.List;

public class BuyAtStoreTemplate extends BaseTemplate {

    public BuyAtStoreTemplate(){
        super("buyAtStore") ;
    }

    public Page<Document> findByUserId(String userId, Pageable pageable){
        org.springframework.data.mongodb.core.query.Query query = org.springframework.data.mongodb.core.query.Query.query(Criteria.where("userId").is(userId));
        query.with(pageable) ;

        List<Document> list = mongoTemplate.find(query,  Document.class, this.collection) ;
        return PageableExecutionUtils.getPage(list, pageable,
                () -> mongoTemplate.count(query, this.collection));
    }
}