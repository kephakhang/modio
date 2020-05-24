package com.youngplussoft.modio.jpa.repository;

import com.youngplussoft.modio.jpa.entity.Store;
import com.youngplussoft.modio.jpa.entity.TableBooking;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;

@RepositoryRestResource(path = "tableBooking")
public interface TableBookingRepository extends MongoRepository<Store, String> {


    public TableBooking findByStoreIdAndDate(String storeId, Date date);
}