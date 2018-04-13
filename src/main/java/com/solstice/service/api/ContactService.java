package com.solstice.service.api;

import com.solstice.model.Contact;

import java.util.List;
import java.util.Map;

public interface ContactService {
    void createContact(Contact contact);
    Contact findContact(String username);
    Contact getContactByPhoneOrEmail(String input);
    List<Contact> getByStateOrCity(String input);
    void updateContact(Map<String, String> map, String username);
    void delete(String username);
}
