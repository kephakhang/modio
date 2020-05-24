package com.youngplussoft.modio.jpa.template;

import org.bson.Document;
import org.bson.types.ObjectId;

public class StoreCategoryTemplate extends BaseTemplate {

    public StoreCategoryTemplate(){
        super("storeCategory") ;
    }

    public Document findById(ObjectId id) {

        return mongoTemplate.findById(id, Document.class, this.collection) ;
    }
}