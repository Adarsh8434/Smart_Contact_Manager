package com.scm.services.implementation;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repository.ContactRepo;
import com.scm.services.ContactService;


@Service
public class ContactServiceImp implements ContactService{
   @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {

        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);

    }

    

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id " + id));
    }

    // @Override
    // public void delete(String id) {
    //     var contact = contactRepo.findById(id)
    //             .orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id " + id));
    //     contactRepo.delete(contact);

    // }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);

    }

    @Override
    public Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return contactRepo.findByUser(user, pageable);

    }



    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


}
