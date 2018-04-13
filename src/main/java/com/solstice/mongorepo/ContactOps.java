package com.solstice.mongorepo;

import com.solstice.model.Contact;

import java.util.Date;
import java.util.Map;

public interface ContactOps {
    void updateContact(Map<String, String> map, String username);
    void updateDate(Date date, String username);
}
