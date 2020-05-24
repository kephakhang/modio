package com.youngplussoft.modio.jpa.template;

import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public class GoodsTemplate extends BaseTemplate {

    public GoodsTemplate(){
        super("goods") ;
    }

    public List<Document> findByStoreId(@Param("storeId") String storeId) {
        Query query = new Query() ;
        query.addCriteria(Criteria.where("storeId").is(storeId));
        return mongoTemplate.find(query, Document.class, this.collection) ;
    }

}