package com.youngplussoft.modio.jpa.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embeddable;

@Embeddable
@Document
public class BankBook {
    String name ;
    String no ;
    String owner ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


}
