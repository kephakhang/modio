package com.youngplussoft.modio.jpa.template;


import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.Param;

public class WebsocketTemplate extends BaseTemplate {

    public WebsocketTemplate(){
        super("websocket") ;
    }

    public Document findByDevId(@Param("devId") String devId){
        Query query = new Query() ;
        query.addCriteria(Criteria.where("devId").is(devId));
        return mongoTemplate.findOne(query, Document.class, this.collection) ;
    }
}