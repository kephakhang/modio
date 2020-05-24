package com.youngplussoft.modio.jpa.template;

import com.youngplussoft.modio.jpa.entity.Store;
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

import java.util.Date;
import java.util.List;

public class TableBookingTemplate extends BaseTemplate {
    protected static final Logger LOGGER = LoggerFactory.getLogger(TableBookingTemplate.class);

    public TableBookingTemplate(){
        super("tableBooking") ;
    }

    public Document findByStoreIdAndDate(String storeId, Date date){

        LOGGER.debug("storeId : " + storeId ) ;
        LOGGER.debug("date : " + date.toString()) ;

        Query query = new Query();
        query.addCriteria(Criteria.where("storeId").is(storeId).andOperator(Criteria.where("date").is(date)));

        Document doc = mongoTemplate.findOne(query,  Document.class, this.collection) ;
        return doc ;
    }
}