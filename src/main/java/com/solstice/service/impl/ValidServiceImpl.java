package com.solstice.service.impl;

import com.solstice.model.Contact;
import com.solstice.mongorepo.ContactRepository;
import com.solstice.service.api.ValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidServiceImpl implements ValidService {

    @Autowired
    ContactRepository contactRepository;


    @Override
    public boolean isValid(String type, String value) {
        if(type.toLowerCase().equals("username")) {
            Contact contact = contactRepository.findContactByUsername(value);
            if(contact==null)
                return true;
        }
        else if(type.toLowerCase().equals("email")) {
            Contact contact = contactRepository.findContactByEmail(value);
            if(contact==null)
                return true;
        } else {
            Contact contact = contactRepository.findContactByPhone(value);
            if(contact==null)
                return true;
        }
        return false;
    }
}
