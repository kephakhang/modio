package com.youngplussoft.modio.jpa.template;

import org.bson.Document;
import org.bson.types.ObjectId;

public class GoodsCategoryTemplate extends BaseTemplate {

    public GoodsCategoryTemplate(){
        super("goodsCategory") ;
    }

    public Document findById(ObjectId id) {

        return mongoTemplate.findById(id, Document.class, this.collection) ;

    }
}