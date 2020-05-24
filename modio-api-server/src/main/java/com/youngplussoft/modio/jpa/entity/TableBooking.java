package com.youngplussoft.modio.jpa.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Embeddable
@Document
public class TableBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String storeId;

    private Date data ;

    @Embedded
    private List<Booking> bookings ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
