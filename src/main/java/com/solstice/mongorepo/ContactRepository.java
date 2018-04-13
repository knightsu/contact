package com.solstice.mongorepo;

import com.solstice.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactRepository extends MongoRepository<Contact, String>, ContactOps{
    List<Contact> findAllByState(String input);
    List<Contact> findAllByCity(String city);
    Contact findContactByUsername(String username);
    Contact findContactByEmail(String input);
    Contact findContactByPhone(String input);
}
