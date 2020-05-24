package com.youngplussoft.modio.jpa.template;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import javax.annotation.Resource;
import java.util.List;

public class BaseTemplate {
    protected String collection=null ;

    @Resource
    MongoTemplate mongoTemplate ;

    public BaseTemplate(String collection){

        this.collection = collection ;
    }

    public void save(Document doc){

        mongoTemplate.save(doc, collection) ;
    }

    public Query nearQuery(Point pos, Distance dist, Pageable pageable){
        Query query = Query.query(Criteria.where("position").withinSphere(new Circle(pos, dist.getNormalizedValue())));
        query.with(pageable) ;
        return query ;
    }

    public void insert(Document doc){
        mongoTemplate.insert(doc, collection) ;
    }

    public Document findById(ObjectId id){
        return mongoTemplate.findById(id, Document.class, collection) ;
    }

    public void remove(ObjectId id){
        Query query = new Query() ;
        query.addCriteria(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, collection) ;
    }

    public Page<Document> findAll(Pageable pageable){
        Query query = new Query() ;
        query.with(pageable) ;

        List<Document> list = mongoTemplate.findAll(Document.class, collection) ;
        return PageableExecutionUtils.getPage(list, pageable,
                () -> mongoTemplate.count(query, collection));
    }
}
