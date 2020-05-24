package com.youngplussoft.modio.jpa.template;

import com.youngplussoft.modio.jpa.entity.Store;
import com.youngplussoft.modio.jpa.entity.User;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;

import java.util.List;

public class StoreTemplate extends BaseTemplate {
    protected static final Logger LOGGER = LoggerFactory.getLogger(StoreTemplate.class);

    public StoreTemplate(){
        super("store") ;
    }

    public Page<Document> findByPositionNear(Point pos, Distance dist, Pageable pageable){

        LOGGER.debug("positon : " + pos ) ;
        LOGGER.debug("distance : " + dist + ":" + dist.getNormalizedValue() ) ;

        Query query = this.nearQuery(pos, dist, pageable) ;
        LOGGER.debug("count : " + mongoTemplate.count(query, this.collection) ) ;

        List<Document> list = mongoTemplate.find(query,  Document.class, this.collection) ;
        return PageableExecutionUtils.getPage(list, pageable,
                () -> mongoTemplate.count(query, this.collection));
    }

    public Page<Store> findByName(String lang, String term, Pageable pageable){

        Query query = new Query();
        query.addCriteria(Criteria.where("name." + lang).regex(term.replaceAll("\\s", ".*")));

        List<Store> list = mongoTemplate.find(query,  Store.class, this.collection) ;
        return PageableExecutionUtils.getPage(list, pageable,
                () -> mongoTemplate.count(query, this.collection));
    }
}