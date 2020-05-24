package com.youngplussoft.modio.jpa.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
@Document
public class Product {

    @DBRef
    Goods goods ;

    int count ;

    List<Price> extraOption ;

    Double extraPrice  ;

    Price price ;

    Double itemTotalPrice ;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Price> getExtraOption() {
        return extraOption;
    }

    public void setExtraOption(List<Price> extraOption) {
        this.extraOption = extraOption;
    }

    public Double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Double getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(Double itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }
}
