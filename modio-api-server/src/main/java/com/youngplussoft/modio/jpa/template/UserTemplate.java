package com.youngplussoft.modio.jpa.template;


import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.Param;

public class UserTemplate extends BaseTemplate {

    public UserTemplate(){
        super("user") ;
    }

    public Document findByMobile(@Param("mobile") String mobile){
        Query query = new Query() ;
        query.addCriteria(Criteria.where("mobile").is(mobile));
        return mongoTemplate.findOne(query, Document.class, this.collection) ;
    }

    public Document findByEmail(@Param("email") String email){
        Query query = new Query() ;
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, Document.class, this.collection) ;
    }

    public Document findByEmailAndEmailKey(@Param("email") String email, @Param("emailKey") String key){
        Query query = new Query() ;
        query.addCriteria(
                new Criteria().andOperator(
                        Criteria.where("email").is(email),
                        Criteria.where("emailKey").is(key)
                )
        );
        return mongoTemplate.findOne(query, Document.class, this.collection) ;
    }

    public Document findByEmailAndMobileKey(@Param("email") String email, @Param("mobileKey") String key){
        Query query = new Query() ;
        query.addCriteria(
                new Criteria().andOperator(
                        Criteria.where("email").is(email),
                        Criteria.where("mobileKey").is(key)
                )
        );
        return mongoTemplate.findOne(query, Document.class, this.collection) ;
    }

    public Document findByEmailAndAccessToken(@Param("email") String email, @Param("accessToken") String accessToken){
        Query query = new Query() ;
        query.addCriteria(
                new Criteria().andOperator(
                        Criteria.where("email").is(email),
                        Criteria.where("accessToken").is(accessToken)
                )
        );
        return mongoTemplate.findOne(query, Document.class, this.collection) ;
    }

    public Document findByAccessToken(@Param("accessToken") String accessToken){
        Query query = new Query() ;
        query.addCriteria(
                        Criteria.where("accessToken").is(accessToken)
        );
        return mongoTemplate.findOne(query, Document.class, this.collection) ;
    }
}