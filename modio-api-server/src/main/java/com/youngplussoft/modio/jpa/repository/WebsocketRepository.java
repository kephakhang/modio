package com.youngplussoft.modio.jpa.repository;


import com.youngplussoft.modio.jpa.entity.Websocket;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "websocket")
public interface WebsocketRepository extends MongoRepository<Websocket, String> {

    public Websocket findByDevId(@Param("devId") String devId);
}