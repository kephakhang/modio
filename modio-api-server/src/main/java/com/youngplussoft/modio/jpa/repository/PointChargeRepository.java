package com.youngplussoft.modio.jpa.repository;

import com.youngplussoft.modio.jpa.entity.Buy;
import com.youngplussoft.modio.jpa.entity.PointCharge;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "pointCharge")
public interface PointChargeRepository extends MongoRepository<PointCharge, String> {

    public PointCharge findById(ObjectId id) ;
    public PointCharge findByOrderNo(String orderNo) ;
}