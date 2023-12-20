package com.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.exception.ContactNotFoundException;
import com.assignment.model.Contact;
import com.assignment.repository.ContactRepository;



@RestController
@CrossOrigin(origins = "**")
public class ContactController {
	
	
	@Autowired
    private ContactRepository contactRepository;

    @PostMapping("contact/user")
    Contact newUser(@RequestBody Contact newUser) {
        return contactRepository.save(newUser);
    }

    @GetMapping("contact/users")
    List<Contact> getAllUsers() {
        return contactRepository.findAll();
    }

    @GetMapping("contact/user/{id}")
    Contact getUserById(@PathVariable Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
    }

    @PutMapping("contact/user/{id}")
    Contact updateUser(@RequestBody Contact newUser, @PathVariable Long id) {
        return contactRepository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    user.setPhone(newUser.getPhone());
                    user.setDescription(newUser.getDescription());
                    return contactRepository.save(user);
                }).orElseThrow(() -> new ContactNotFoundException(id));
    }

    @DeleteMapping("contact/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!contactRepository.existsById(id)){
            throw new ContactNotFoundException(id);
        }
        contactRepository.deleteById(id);
        return  "User with id "+id+" has been deleted success.";
    }


}
