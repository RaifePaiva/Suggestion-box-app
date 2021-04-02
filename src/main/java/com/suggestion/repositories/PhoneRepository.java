package com.suggestion.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.suggestion.models.Phone;

@Repository
public interface PhoneRepository extends PagingAndSortingRepository<Phone, Long> {

}
