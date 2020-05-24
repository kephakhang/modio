package com.youngplussoft.modio.jpa.template;


import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.Param;

public class PointChargeTemplate extends BaseTemplate {

    public PointChargeTemplate(){
        super("pointCharge") ;
    }

    public Document findByOrderNo(@Param("orderNo") String orderNo){
        Query query = new Query() ;
        query.addCriteria(Criteria.where("orderNo").is(orderNo));
        return mongoTemplate.findOne(query, Document.class, this.collection) ;
    }
}