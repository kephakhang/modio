package com.youngplussoft.modio.jpa.template;

import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MobileConfirmTemplate extends BaseTemplate {

    public MobileConfirmTemplate(){
        super("mobileConfirm") ;
    }

    public Document findByOne(String id){

        Query query = new Query() ;
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Document.class, this.collection) ;
    }
}