package com.solstice.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RestController
@RequestMapping(value = "/image")
public class ImageController {
    @Autowired
    GridFsTemplate gridFsTemplate;

    @RequestMapping(value = "/upload/{username}", method = RequestMethod.GET)
    public void upload(@PathVariable String username)
    {
        String filepath = "/img/profile/icon"+username+".png";
        DBObject metaData = new BasicDBObject();
        metaData.put("user", username);
        String filename = username+".png";
        try {
            InputStream inputStream = new FileInputStream(filepath);
            gridFsTemplate.store(inputStream, filename, "image/png", metaData);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/find/{username}", method = RequestMethod.GET)
    public GridFSDBFile getImage(@PathVariable String username)
    {
        return gridFsTemplate.find(new Query(Criteria.where("metadata.user").is(username))).get(0);
    }

    @RequestMapping(value = "/delete/{username}", method = RequestMethod.DELETE)
    public void deleteImage(@PathVariable String username)
    {
        String id = gridFsTemplate.find(new Query(Criteria.where("metadata.user").is(username))).get(0).getId().toString();
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
    }

    @RequestMapping(value = "/update/{username}", method = RequestMethod.PUT)
    public void updateImage(@PathVariable String username)
    {
        deleteImage(username);
        upload(username);
    }

}
