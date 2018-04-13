package com.solstice.controller;


import com.solstice.model.Contact;
import com.solstice.service.api.ContactService;
import com.solstice.service.api.ValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class ContactController {

    @Autowired
    ValidService validService;

    @Autowired
    ContactService contactService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Contact createContact(@RequestBody Contact contact)
    {
        contactService.createContact(contact);
        return contact;
    }

    @RequestMapping(value = "/fetch/{username}", method = RequestMethod.GET)
    public Contact getContact(@PathVariable String username)
    {
        return contactService.findContact(username);
    }

    @RequestMapping(value = "/update/{username}", method = RequestMethod.PUT)
    public Contact updateContact(@RequestParam Map<String, String> map, @PathVariable String username)
    {
        contactService.updateContact(map, username);
        return contactService.findContact(username);
    }

    @RequestMapping(value = "/delete/{username}", method = RequestMethod.DELETE)
    public void deleteContact(@PathVariable String username)
    {
        contactService.delete(username);
    }

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public Contact getContactEmailPhone(@RequestParam String value)
    {
        System.out.println(value);
        return contactService.getContactByPhoneOrEmail(value);
    }

    @RequestMapping(value = "/fetchall", method = RequestMethod.GET)
    public List<Contact> getAllByStateOrCity(@RequestParam String value)
    {
        return contactService.getByStateOrCity(value);
    }

    @RequestMapping(value = "/valid", method = RequestMethod.GET)
    public boolean isValid(@RequestParam(value = "type") String type, @RequestParam(value = "value") String value)
    {
        return validService.isValid(type, value);
    }
}
