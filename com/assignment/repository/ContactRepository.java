package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.model.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long> {

}
