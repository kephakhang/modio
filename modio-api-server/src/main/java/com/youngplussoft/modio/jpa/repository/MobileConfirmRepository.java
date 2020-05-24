package com.youngplussoft.modio.jpa.repository;

import com.youngplussoft.modio.jpa.entity.MobileConfirm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.annotation.Resource;

@RepositoryRestResource(path = "mobileConfirm")
public interface MobileConfirmRepository extends MongoRepository<MobileConfirm, String> {

    @Query("{ '_id' : ?1 }")
    public MobileConfirm findByOne(String id);
}