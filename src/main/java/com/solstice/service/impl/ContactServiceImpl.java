package com.solstice.service.impl;

import com.solstice.model.Contact;
import com.solstice.mongorepo.ContactRepository;
import com.solstice.service.api.ContactService;
import com.solstice.util.CalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public void createContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public Contact findContact(String username) {
        return contactRepository.findContactByUsername(username);
    }

    @Override
    public Contact getContactByPhoneOrEmail(String input) {
        if(input.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com"))
        {
            return contactRepository.findContactByEmail(input);
        } else {
            return contactRepository.findContactByPhone(input);
        }

    }

    @Override
    public List<Contact> getByStateOrCity(String input) {
        if(input.length()==2)
            return contactRepository.findAllByState(input.toUpperCase());
        else
            return contactRepository.findAllByCity(input);
    }

    @Override
    public void updateContact(Map<String, String> map, String username) {
        if(map.containsKey("birthdate"))
        {
            Date date = CalUtil.convert(map.get("birthdate"));
            contactRepository.updateDate(date, username);
            map.remove("birthdate");
        }
        if(map.size()>0)
        {
            contactRepository.updateContact(map, username);
        }
    }

    @Override
    public void delete(String username) {
        Contact contact = contactRepository.findContactByUsername(username);
        contactRepository.delete(contact.getId());
    }
}
