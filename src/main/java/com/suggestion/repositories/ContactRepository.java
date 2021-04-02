package com.suggestion.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.suggestion.models.Contact;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

}
