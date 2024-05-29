package com.example.genaiservice.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Component;

import com.example.genaiservice.apis.user.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) throws Exception {
    	
    	log.info("Inside run method of database seeder");
    	
        // Create Users collection with indexes
        if (!mongoTemplate.collectionExists(UserEntity.class)) {
        	
            mongoTemplate.createCollection(UserEntity.class);
            mongoTemplate.indexOps(UserEntity.class).ensureIndex(new Index().on("user_id", org.springframework.data.domain.Sort.Direction.ASC).unique());
            log.info("Collection users created with index on userId.");
        }

        // Create Items collection with indexes
//        if (!mongoTemplate.collectionExists(Item.class)) {
//            mongoTemplate.createCollection(Item.class);
//            mongoTemplate.indexOps(Item.class).ensureIndex(new Index().on("itemId", org.springframework.data.domain.Sort.Direction.ASC).unique());
//            System.out.println("Collection 'items' created with index on 'itemId'.");
//        }

        // Create Orders collection with indexes
//        if (!mongoTemplate.collectionExists(Order.class)) {
//            mongoTemplate.createCollection(Order.class);
//            mongoTemplate.indexOps(Order.class).ensureIndex(new Index().on("orderId", org.springframework.data.domain.Sort.Direction.ASC).unique());
//            mongoTemplate.indexOps(Order.class).ensureIndex(new Index().on("userId", org.springframework.data.domain.Sort.Direction.ASC));
//            System.out.println("Collection 'orders' created with indexes on 'orderId' and 'userId'.");
//        }
    }
}
