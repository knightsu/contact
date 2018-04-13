package com.solstice.mongorepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Date;
import java.util.Map;

public class ContactRepositoryImpl implements ContactOps{


    @Autowired
    MongoOperations mongoOperations;

    @Override
    public void updateContact(Map<String, String> map, String username) {
        Criteria where = Criteria.where("username").is(username);
        Query query = Query.query(where);
        Update update = new Update();
        map.keySet().forEach((p) -> update.set(p, map.get(p)));
        mongoOperations.updateMulti(query, update, "solstice");
    }

    @Override
    public void updateDate(Date date, String username) {
        Criteria where = Criteria.where("username").is(username);
        Query query = Query.query(where);
        Update update = new Update();
        update.set("birthDate", date);
        mongoOperations.updateMulti(query, update, "solstice");
    }


}
